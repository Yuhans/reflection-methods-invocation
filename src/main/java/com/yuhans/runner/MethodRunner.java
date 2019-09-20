package com.yuhans.runner;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Collection;

/**
 * Runner which invokes given methods.
 * <p>
 * Implementations can vary in additional operations around the invocations.
 */
public interface MethodRunner {

    /**
     * Invokes all given methods. Provides a class all methods are annotated with.
     *
     * @param methods    - collection of methods should be invoked
     * @param annotation - class all methods are annotated with
     * @return amount of successful methods invocations
     */
    int runAllMethods(Collection<Method> methods, Class<? extends Annotation> annotation);
}
