package com.github.kirvasilchenko.t1.java.pro.homework1;

import com.github.kirvasilchenko.t1.java.pro.homework1.annotation.BeforeSuite;

public class TestSuiteFailOnBefore {

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("BeforeSuite");
    }

    @BeforeSuite
    public void beforeSuite2() {
        System.out.println("BeforeSuite");
    }

}
