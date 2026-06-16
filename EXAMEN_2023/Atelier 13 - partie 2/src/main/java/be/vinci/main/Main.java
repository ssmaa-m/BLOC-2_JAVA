package be.vinci.main;

import be.vinci.domain.Factory;
import be.vinci.runner.TestClassRunner;

public class Main {

    public static void main(String[] args) {
        Factory factory = new Factory();
        TestClassRunner testClassRunner = new TestClassRunner(factory);
        testClassRunner.runTests();
    }

}