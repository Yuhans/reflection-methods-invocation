package com.yuhans.runner.impl;

import com.yuhans.annotation.MiniTest;
import com.yuhans.runner.MethodRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Simple implementation of {@link MethodRunner}. For simplicity it works only with {@link MiniTest}.
 */
public class MethodSimpleRunner implements MethodRunner {

    private static final Logger LOG = LoggerFactory.getLogger(MethodSimpleRunner.class);
    /**
     * Stores instances of classes which are needed for methods invoking.
     * It's filling during work of {@link MethodSimpleRunner#runAllMethods(Collection, Class)}
     * and it's cleared afterwards.
     */
    private final Map<Class<?>, Object> classToInstance;

    public MethodSimpleRunner() {
        this.classToInstance = new HashMap<>();
    }

    @Override
    public int runAllMethods(final Collection<Method> methods, final Class<? extends Annotation> annotation) {
        Objects.requireNonNull(methods, "Provided methods collection must not be null");
        Objects.requireNonNull(annotation, "Provided annotation must not be null");
        if (annotation != MiniTest.class) {
            LOG.info("{} can't run methods annotated with {}", this.getClass().getSimpleName(), annotation);
            return 0;
        }

        int successfulInvocations = 0;
        for (Method method : methods) {
            final MiniTest declaredAnnotation = ((MiniTest) method.getAnnotation(annotation));
            final Class<? extends Throwable> expected = declaredAnnotation.expected();
            if (invokeMethod(method, expected)) {
                successfulInvocations++;
            }
        }
        classToInstance.clear();
        return successfulInvocations;
    }

    private boolean invokeMethod(Method method, Class<? extends Throwable> expected) {
        boolean success = false;
        try {
            method.invoke(getInstanceForMethod(method));
            LOG.info("Method {} succeed", method);
            success = true;
        } catch (Exception e) {
            if (isExpected(e, expected)) {
                LOG.info("Method {} succeed", method);
                success = true;
            } else {
                LOG.error("Exception occurred while invoking method {}, exception = ", method, e);
            }
        }

        return success;
    }

    private Object getInstanceForMethod(final Method method) {
        if (isStatic(method)) {
            return null;
        }

        final Class<?> declaringClass = method.getDeclaringClass();
        return classToInstance.computeIfAbsent(declaringClass, this::getClassInstance);
    }

    private boolean isStatic(final Method method) {
        return Modifier.isStatic(method.getModifiers());
    }

    private Object getClassInstance(final Class<?> clazz) {
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            // for simplicity we just log exception and throw RuntimeException with given cause
            LOG.error("Error occurred while class instantiating, e = ", e);
            throw new RuntimeException(e);
        }
    }

    private boolean isExpected(final Exception e, final Class<? extends Throwable> expected) {
        //We need to cast exception to InvocationTargetException
        //because of some specific code from earlier versions of java.
        //InvocationTargetException contains cause in "target" field and null in cause,
        //so if we don't cast we will get null from getCause() method.
        return isMethodInvocationException(e) && ((InvocationTargetException) e).getCause().getClass() == expected;
    }

    private boolean isMethodInvocationException(final Exception e) {
        return e.getClass() == InvocationTargetException.class;
    }
}
