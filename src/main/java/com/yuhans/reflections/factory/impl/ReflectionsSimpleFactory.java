package com.yuhans.reflections.factory.impl;

import com.yuhans.reflections.factory.ReflectionsFactory;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;

import java.util.Objects;

/**
 * Simple implementation of {@link ReflectionsFactory}.
 */
public class ReflectionsSimpleFactory implements ReflectionsFactory {

    @Override
    public Reflections newInstance(final String packageName) {
        Objects.requireNonNull(packageName, "Provided package name must not be null");
        return new Reflections(packageName, new MethodAnnotationsScanner());
    }
}
