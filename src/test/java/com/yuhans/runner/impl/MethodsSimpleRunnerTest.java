package com.yuhans.runner.impl;

import com.yuhans.annotated.TestClassWithProperAnnotatedMethods;
import com.yuhans.annotated.TestClassWithNotAnnotatedMethods;
import com.yuhans.annotation.MiniTest;
import com.yuhans.runner.MethodRunner;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.Set;

import static org.junit.Assert.assertEquals;


public class MethodsSimpleRunnerTest {

    private MethodRunner methodRunner = new MethodSimpleRunner();

    private TestClassWithNotAnnotatedMethods testClassWithNotAnnotatedMethods = new TestClassWithNotAnnotatedMethods();

    private TestClassWithProperAnnotatedMethods classWithAnnotatedMethods = new TestClassWithProperAnnotatedMethods();

    @Test(expected = NullPointerException.class)
    public void testRunMethodsWithoutAnnotation() {
        Set<Method> declaredMethods = Set.of(testClassWithNotAnnotatedMethods.getClass().getDeclaredMethods());
        methodRunner.runAllMethods(declaredMethods, MiniTest.class);
    }

    @Test
    public void testRunMethodsWithAnnotation() {
        Set<Method> declaredMethods = Set.of(classWithAnnotatedMethods.getClass().getDeclaredMethods());
        int succeedMethods = methodRunner.runAllMethods(declaredMethods, MiniTest.class);
        assertEquals(3, succeedMethods);
    }
}