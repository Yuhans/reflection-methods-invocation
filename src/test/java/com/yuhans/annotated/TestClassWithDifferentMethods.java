package com.yuhans.annotated;

import com.yuhans.annotation.MiniTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestClassWithDifferentMethods {

    private static final Logger LOG = LoggerFactory.getLogger(TestClassWithDifferentMethods.class);

    @MiniTest
    public void normalTest() {
        LOG.info("normalTest is in progress");
    }

    @MiniTest(expected = UnsupportedOperationException.class)
    public void exceptionTest() {
        LOG.info("exceptionTest is in progress");
        throw new UnsupportedOperationException("For testing purpose");
    }

    @MiniTest(expected = UnsupportedOperationException.class)
    public void unexpectedExceptionTest() {
        LOG.info("unexpectedExceptionTest is in progress");
        throw new NullPointerException("For testing purpose");
    }

    @MiniTest
    public static void staticTest() {
        LOG.info("staticTest is in progress");
    }

    @MiniTest
    private void privateTest() {
        LOG.info("privateTest is in progress");
    }

    @MiniTest
    public int returningTest() {
        LOG.info("returningTest is in progress");
        return 1;
    }

    @MiniTest
    public void paramsTest(Object param) {
        LOG.info("paramsTest is in progress");
    }
}
