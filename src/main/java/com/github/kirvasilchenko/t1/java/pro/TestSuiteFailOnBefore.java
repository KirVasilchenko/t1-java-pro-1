package com.github.kirvasilchenko.t1.java.pro;

import com.github.kirvasilchenko.t1.java.pro.annotation.AfterSuite;
import com.github.kirvasilchenko.t1.java.pro.annotation.BeforeSuite;
import com.github.kirvasilchenko.t1.java.pro.annotation.Test;

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
