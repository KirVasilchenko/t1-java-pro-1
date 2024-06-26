package com.github.kirvasilchenko.t1.java.pro.homework1;

import com.github.kirvasilchenko.t1.java.pro.homework1.annotation.AfterSuite;
import com.github.kirvasilchenko.t1.java.pro.homework1.annotation.BeforeSuite;
import com.github.kirvasilchenko.t1.java.pro.homework1.annotation.Test;

public class BasicTestSuite {

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("BeforeSuite");
    }

    @Test(priority = 2)
    public void test2() {
        System.out.println("Test 2");
    }

    @Test(priority = 1)
    public void test() {
        System.out.println("Test 1");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("AfterSuite");
    }


}
