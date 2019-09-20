package com.yuhans.finder.impl;

import com.yuhans.finder.MethodFinder;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Simple implementation of {@link MethodFinder}.
 */
public class MethodSimpleFinder implements MethodFinder {

    /**
     * Checks if method is a proper test method.
     * It's created unmodifiable just for meeting given requirements and simplicity.
     */
    private final Predicate<Method> isProperTestMethod = m -> isPublic(m) && isVoid(m) && isEmptyParams(m);

    /**
     * Customized reflections object for searching for methods
     */
    private final Reflections reflections;

    public MethodSimpleFinder(Reflections reflections) {
        this.reflections = reflections;
    }

    @Override
    public Collection<Method> findAllMethodsWith(final Class<? extends Annotation> annotation) {
        Objects.requireNonNull(annotation, "Provided annotation must not be null");
        return reflections.getMethodsAnnotatedWith(annotation)
                .stream()
                .filter(isProperTestMethod)
                .collect(Collectors.toSet());
    }

    private boolean isEmptyParams(final Method method) {
        return method.getParameterCount() == 0;
    }

    private boolean isVoid(final Method method) {
        return method.getReturnType() == void.class;
    }

    private boolean isPublic(final Method method) {
        return Modifier.isPublic(method.getModifiers());
    }
}
