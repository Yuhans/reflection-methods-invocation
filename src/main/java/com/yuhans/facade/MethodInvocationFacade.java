package com.yuhans.facade;

import com.yuhans.finder.MethodFinder;
import com.yuhans.runner.MethodRunner;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Objects;

/**
 * Simple facade for finding and running all methods with given annotation.
 */
public class MethodInvocationFacade {

    private static final Logger LOG = LoggerFactory.getLogger(MethodInvocationFacade.class);

    private final MethodFinder methodFinder;

    private final MethodRunner methodRunner;

    public MethodInvocationFacade(MethodFinder methodFinder, MethodRunner methodRunner) {
        this.methodFinder = methodFinder;
        this.methodRunner = methodRunner;
    }

    /**
     * Find all public void methods with zero params and with given annotation.
     * Invokes them and returns amount of successful invocations.
     *
     * @param annotation - class all methods are annotated by
     * @return amount of successful invocations
     */
    public int invokeAllMethodsWith(final Class<? extends Annotation> annotation) {
        Objects.requireNonNull(annotation, "Provided annotation must not be null");
        final Collection<Method> methods = methodFinder.findAllMethodsWith(annotation);
        if (CollectionUtils.isEmpty(methods)) {
            LOG.info("Can't find any method with {}", annotation);
            return 0;
        }

        LOG.info("Found {} methods with {} annotation", methods.size(), annotation);
        return methodRunner.runAllMethods(methods, annotation);
    }
}
