package com.github.kirvasilchenko.t1.java.pro;

import com.github.kirvasilchenko.t1.java.pro.annotation.AfterSuite;

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
