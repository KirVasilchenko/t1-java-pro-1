package com.github.kirvasilchenko.t1.java.pro;

import com.github.kirvasilchenko.t1.java.pro.annotation.AfterSuite;
import com.github.kirvasilchenko.t1.java.pro.annotation.AfterTest;
import com.github.kirvasilchenko.t1.java.pro.annotation.BeforeSuite;
import com.github.kirvasilchenko.t1.java.pro.annotation.BeforeTest;
import com.github.kirvasilchenko.t1.java.pro.annotation.CsvSource;
import com.github.kirvasilchenko.t1.java.pro.annotation.Test;

public class AdvancedTestSuite {

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("BeforeSuite");
    }

    @Test(priority = 2)
    public void test2() {
        System.out.println("Test 2");
    }

    @Test(priority = 3)
    public void test3() {
        System.out.println("Test 3");
    }

    @Test(priority = 1)
    public void test() {
        System.out.println("Test 1");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("AfterSuite");
    }

    @Test(priority = 2)
    public void test21() {
        System.out.println("Test 2, another");
    }

    @BeforeTest
    public void beforeEach() {
        System.out.println("beforeTest");
    }

    @AfterTest
    public void afterEach() {
        System.out.println("afterTest1");
    }

    @AfterTest
    public void afterEach2() {
        System.out.println("afterTest2");
    }

    @Test(priority = 4)
    @CsvSource(value = "foo, bar")
    public void test4(String foo, String bar) {
        System.out.println("Test 4: foo=" + foo);
        System.out.println("Test 4: bar=" + bar);
    }

}
