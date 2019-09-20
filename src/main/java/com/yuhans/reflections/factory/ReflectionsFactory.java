package com.yuhans.reflections.factory;

import org.reflections.Reflections;

/**
 * Factory for creating instances of {@link Reflections}
 */
public interface ReflectionsFactory {

    /**
     * Creates new instance of {@link Reflections}
     *
     * @param packageName - package name where methods should be searched for
     * @return new instance of {@link Reflections}
     */
    Reflections newInstance(String packageName);
}
