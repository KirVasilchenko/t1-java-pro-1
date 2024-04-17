package com.github.kirvasilchenko.t1.java.pro.homework1;

import com.github.kirvasilchenko.t1.java.pro.homework1.annotation.AfterSuite;
import com.github.kirvasilchenko.t1.java.pro.homework1.annotation.AfterTest;
import com.github.kirvasilchenko.t1.java.pro.homework1.annotation.BeforeSuite;
import com.github.kirvasilchenko.t1.java.pro.homework1.annotation.BeforeTest;
import com.github.kirvasilchenko.t1.java.pro.homework1.annotation.CsvSource;
import com.github.kirvasilchenko.t1.java.pro.homework1.annotation.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestRunner {
    private TestRunner() {
    }

    /**
     * Get all methods annotated with @Test or @BeforeSuite or @AfterSuite.
     *
     * @param c class to scan
     * @see com.github.kirvasilchenko.t1.java.pro.homework1.annotation.Test
     * @see com.github.kirvasilchenko.t1.java.pro.homework1.annotation.BeforeSuite
     * @see com.github.kirvasilchenko.t1.java.pro.homework1.annotation.AfterSuite
     */
    static void runTests(Class<?> c) {
        try {
            System.out.println("Running " + c.getName());

            Object target = c.getConstructor().newInstance();
            Method[] methods = c.getDeclaredMethods();

            List<Method> beforeSuiteMethods = getAnnotatedMethods(c, BeforeSuite.class);
            if (beforeSuiteMethods.size() > 1) {
                throw new RuntimeException("BeforeSuite annotation should occur 0 or 1 time.");
            }

            List<Method> afterSuiteMethods = getAnnotatedMethods(c, AfterSuite.class);
            if (afterSuiteMethods.size() > 1) {
                throw new RuntimeException("AfterSuite annotation should occur 0 or 1 time.");
            }

            if (beforeSuiteMethods.size() == 1) {
                invokeMethod(target, beforeSuiteMethods.get(0));
            }

            List<Method> beforeTestMethods = getAnnotatedMethods(c, BeforeTest.class);
            List<Method> afterTestMethods = getAnnotatedMethods(c, AfterTest.class);

            List<Method> testMethods = getAnnotatedMethods(c, Test.class, Comparator.comparingInt(method -> method.getAnnotation(Test.class).priority()));
            testMethods.forEach(method -> {
                beforeTestMethods.forEach(beforeTestMethod -> invokeMethod(target, beforeTestMethod));
                invokeMethod(target, method);
                afterTestMethods.forEach(afterTestMethod -> invokeMethod(target, afterTestMethod));
            });

            if (afterSuiteMethods.size() == 1) {
                invokeMethod(target, afterSuiteMethods.get(0));
            }
        } catch (IllegalAccessException | InvocationTargetException | InstantiationException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private static void invokeMethod(Object target, Method method) {
        try {
            if (method.isAnnotationPresent(CsvSource.class)) {
                String[] values = method.getAnnotation(CsvSource.class).value()
                        .replaceAll("\\s+", "")
                        .split(",");
                Parameter[] parameters = method.getParameters();

                if (values.length != parameters.length) {
                    throw new RuntimeException("CsvSource annotation should contain same number of values as parameters.");
                }

                Object[] args = new Object[parameters.length];
                for (int i = 0; i < parameters.length; i++) {
                    args[i] = parameters[i].getType().cast(values[i]);
                }

                method.invoke(target, args);
            } else {
                method.invoke(target);
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<Method> getAnnotatedMethods(Class<?> c, Class<? extends Annotation> annotation) {
        return getAnnotatedMethods(c, annotation, null);
    }

    private static List<Method> getAnnotatedMethods(Class<?> c, Class<? extends Annotation> annotation, Comparator<Method> comparator) {
        Stream<Method> methodStream = Arrays.stream(c.getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(annotation));
        if (comparator != null) {
            methodStream = methodStream.sorted(comparator);
        }
        return methodStream
                .collect(Collectors.toList());
    }
}
