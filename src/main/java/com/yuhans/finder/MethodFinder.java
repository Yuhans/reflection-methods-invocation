package com.yuhans.finder;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Collection;

/**
 * Finder of collection of {@link Method}.
 * <p>
 * Implementations can vary in additional options and places where to search for methods.
 */
public interface MethodFinder {

    /**
     * Finds all methods which are annotated with given class
     *
     * @param annotation - class all methods are annotated by
     * @return collection of methods which are annotated with given class
     */
    Collection<Method> findAllMethodsWith(Class<? extends Annotation> annotation);
}
