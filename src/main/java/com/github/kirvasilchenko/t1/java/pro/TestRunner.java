package com.github.kirvasilchenko.t1.java.pro;

import com.github.kirvasilchenko.t1.java.pro.annotation.AfterSuite;
import com.github.kirvasilchenko.t1.java.pro.annotation.BeforeSuite;
import com.github.kirvasilchenko.t1.java.pro.annotation.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TestRunner {
    private TestRunner() {
    }

    /**
     * Get all methods annotated with @Test or @BeforeSuite or @AfterSuite.
     *
     * @param c class to scan
     * @see com.github.kirvasilchenko.t1.java.pro.annotation.Test
     * @see com.github.kirvasilchenko.t1.java.pro.annotation.BeforeSuite
     * @see com.github.kirvasilchenko.t1.java.pro.annotation.AfterSuite
     */
    static void runTests(Class<?> c) {
        try {
            Object target = c.getConstructor().newInstance();
            Method[] methods = c.getDeclaredMethods();

            List<Method> beforeSuiteMethods = Arrays.stream(methods)
                    .filter(method -> method.isAnnotationPresent(BeforeSuite.class)).collect(Collectors.toList());
            if (beforeSuiteMethods.size() > 1) {
                throw new RuntimeException("BeforeSuite annotation should occur 0 or 1 time.");
            }

            List<Method> afterSuiteMethods = Arrays.stream(methods)
                    .filter(method -> method.isAnnotationPresent(AfterSuite.class)).collect(Collectors.toList());
            if (afterSuiteMethods.size() > 1) {
                throw new RuntimeException("AfterSuite annotation should occur 0 or 1 time.");
            }

            if (beforeSuiteMethods.size() == 1) {
                try {
                    beforeSuiteMethods.get(0).invoke(target);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }

            List<Method> testMethods = Arrays.stream(methods)
                    .filter(method -> method.isAnnotationPresent(Test.class))
                    .sorted(Comparator.comparingInt(method -> method.getAnnotation(Test.class).priority()))
                    .collect(Collectors.toList());
            testMethods.forEach(method -> {
                try {
                    method.invoke(target);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            });

            if (afterSuiteMethods.size() == 1) {
                try {
                    afterSuiteMethods.get(0).invoke(target);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
