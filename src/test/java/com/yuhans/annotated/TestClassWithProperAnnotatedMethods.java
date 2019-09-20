package com.yuhans.annotated;

import com.yuhans.annotation.MiniTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestClassWithProperAnnotatedMethods {

    private static final Logger LOG = LoggerFactory.getLogger(TestClassWithProperAnnotatedMethods.class);

    /**
     * Reference to nothing, just for testing purposes
     */
    private Object object;

    @MiniTest
    public void publicMethod() {
        LOG.info("publicMethod is in progress");
    }

    @MiniTest
    public static void publicStaticMethod() {
        LOG.info("publicStaticMethod is in progress");
    }


    @MiniTest(expected = NullPointerException.class)
    public void exceptionMethod() {
        LOG.info("exceptionMethod is in progress");
        object.getClass();
    }
}
