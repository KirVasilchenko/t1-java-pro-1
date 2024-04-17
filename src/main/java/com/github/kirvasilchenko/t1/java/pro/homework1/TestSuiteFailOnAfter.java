package com.github.kirvasilchenko.t1.java.pro.homework1;

import com.github.kirvasilchenko.t1.java.pro.homework1.annotation.AfterSuite;

public class TestSuiteFailOnAfter {

    @AfterSuite
    public void AfterSuite() {
        System.out.println("AfterSuite");
    }

    @AfterSuite
    public void AfterSuite2() {
        System.out.println("AfterSuite");
    }
    
}
