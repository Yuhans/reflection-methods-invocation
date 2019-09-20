package com.yuhans.finder.impl;

import com.yuhans.annotated.TestClassWithDifferentMethods;
import com.yuhans.annotation.MiniTest;
import com.yuhans.finder.MethodFinder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.reflections.Reflections;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MethodsSimpleFinderTest {

    @Mock
    private Reflections reflections;

    private TestClassWithDifferentMethods testClass = new TestClassWithDifferentMethods();


    private MethodFinder methodFinder;

    @Before
    public void setUp() {
        Method[] declaredMethods = testClass.getClass().getDeclaredMethods();

        when(reflections.getMethodsAnnotatedWith(MiniTest.class)).thenReturn(Set.of(declaredMethods));

        this.methodFinder = new MethodSimpleFinder(reflections);
    }

    @Test
    public void testFindAllProperMethods() {
        Collection<Method> methods = methodFinder.findAllMethodsWith(MiniTest.class);
        assertEquals(4, methods.size());
    }
}