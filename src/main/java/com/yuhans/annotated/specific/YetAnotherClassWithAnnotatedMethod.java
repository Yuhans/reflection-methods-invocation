package com.yuhans.annotated.specific;

import com.yuhans.annotation.MiniTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Simple class with methods annotated by {@link MiniTest}.
 * You can specify 'com.yuhans.annotated.specific' argument on program running
 * and only methods of this class will be invoked.
 */
public class YetAnotherClassWithAnnotatedMethod {

    private static final Logger LOG = LoggerFactory.getLogger(YetAnotherClassWithAnnotatedMethod.class);

    @MiniTest
    public void normalSpecificTest() {
        LOG.info("normalSpecificTest is in progress");
    }

    @MiniTest(expected = ArithmeticException.class)
    public void exceptionSpecificTest() {
        LOG.info("exceptionSpecificTest is in progress");
        throw new ArithmeticException("For testing purpose");
    }

    @MiniTest(expected = ArithmeticException.class)
    public void unexpectedExceptionSpecificTest() {
        LOG.info("unexpectedExceptionSpecificTest is in progress");
        throw new NullPointerException("For testing purpose");
    }

    @MiniTest
    public static void staticSpecificTest() {
        LOG.info("staticSpecificTest is in progress");
    }

    @MiniTest
    private void privateSpecificTest() {
        LOG.info("privateSpecificTest is in progress");
    }

    @MiniTest
    public int returningSpecificTest() {
        LOG.info("returningSpecificTest is in progress");
        return 1;
    }

    @MiniTest
    public void paramsSpecificTest(Object param) {
        LOG.info("paramsSpecificTest is in progress");
    }
}
