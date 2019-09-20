package com.yuhans.facade;

import com.yuhans.annotation.MiniTest;
import com.yuhans.finder.MethodFinder;
import com.yuhans.runner.MethodRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MethodInvocationFacadeTest {

    @Mock
    private MethodFinder methodFinder;

    @Mock
    private MethodRunner methodRunner;

    @Mock
    private Set<Method> declaredMethods;

    private MethodInvocationFacade methodInvocationFacade;

    //we can take any number, just for testing purposes
    private int successfulInvocations = 5;

    @Before
    public void setUp() {
        when(methodFinder.findAllMethodsWith(any(Class.class)))
                .thenReturn(declaredMethods);
        when(methodRunner.runAllMethods(anyCollection(), any(Class.class))).thenReturn(successfulInvocations);
        this.methodInvocationFacade = new MethodInvocationFacade(methodFinder, methodRunner);
    }

    @Test
    public void testNoMethodsFound() {
        when(methodFinder.findAllMethodsWith(any(Class.class)))
                .thenReturn(Collections.emptyList());
        int facadeMethodsInvocations = methodInvocationFacade.invokeAllMethodsWith(MiniTest.class);
        assertEquals(0, facadeMethodsInvocations);
    }

    @Test
    public void testMethodsFoundAndInvoked() {
        int facadeMethodsInvocations = methodInvocationFacade.invokeAllMethodsWith(MiniTest.class);
        assertEquals(successfulInvocations, facadeMethodsInvocations);
    }
}