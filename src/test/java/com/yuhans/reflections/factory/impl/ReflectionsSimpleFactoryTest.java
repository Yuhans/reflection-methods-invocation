package com.yuhans.reflections.factory.impl;

import com.yuhans.reflections.factory.ReflectionsFactory;
import org.junit.Test;
import org.reflections.Reflections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class ReflectionsSimpleFactoryTest {

    private ReflectionsFactory reflectionsFactory = new ReflectionsSimpleFactory();

    @Test(expected = NullPointerException.class)
    public void testNullPackageName() {
        Reflections reflections = reflectionsFactory.newInstance(null);
        assertNotNull(reflections);
        assertEquals(0, reflections.getConfiguration().getUrls().size());
    }

    @Test
    public void testNotEmptyPackageName() {
        Reflections reflections = reflectionsFactory.newInstance("com.yuhans");
        assertNotNull(reflections);
        assertNotEquals(0, reflections.getConfiguration().getUrls().size());
    }

    @Test
    public void testNonexistentPackageName() {
        Reflections reflections = reflectionsFactory.newInstance("com.nonexistent");
        assertNotNull(reflections);
        assertEquals(0, reflections.getConfiguration().getUrls().size());
    }
}