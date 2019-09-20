package com.yuhans.annotated;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestClassWithNotAnnotatedMethods {

    private static final Logger LOG = LoggerFactory.getLogger(TestClassWithNotAnnotatedMethods.class);

    public void publicMethod() {
        LOG.info("publicMethod is in progress");
    }

    public static void publicStaticMethod() {
        LOG.info("publicStaticMethod is in progress");
    }

    private void privateMethod() {
        LOG.info("privateMethod is in progress");
    }

    public int returningMethod() {
        LOG.info("returningMethod is in progress");
        return 1;
    }

    public void paramsMethod(Object param) {
        LOG.info("paramsMethod is in progress");
    }
}
