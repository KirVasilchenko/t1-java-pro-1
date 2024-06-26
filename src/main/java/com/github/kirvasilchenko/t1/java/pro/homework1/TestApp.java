package com.github.kirvasilchenko.t1.java.pro.homework1;

public class TestApp {

    public static void main(String[] args) {
        TestRunner.runTests(BasicTestSuite.class);

        try {
            TestRunner.runTests(TestSuiteFailOnBefore.class);
        } catch (Exception e) {
            System.out.println("Failed on BeforeSuite");
            //e.printStackTrace();
        }

        try {
            TestRunner.runTests(TestSuiteFailOnAfter.class);
        } catch (Exception e) {
            System.out.println("Failed on AfterSuite");
            //e.printStackTrace();
        }

        TestRunner.runTests(AdvancedTestSuite.class);
    }

}
