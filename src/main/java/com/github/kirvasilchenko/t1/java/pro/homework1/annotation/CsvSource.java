package com.github.kirvasilchenko.t1.java.pro.homework1.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for test. Set it in methods with params that should get values from csv string.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.METHOD})
public @interface CsvSource {

    String value();

}
