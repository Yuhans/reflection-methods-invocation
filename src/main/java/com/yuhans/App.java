package com.yuhans;

import com.yuhans.annotation.MiniTest;
import com.yuhans.facade.MethodInvocationFacade;
import com.yuhans.finder.impl.MethodSimpleFinder;
import com.yuhans.reflections.factory.ReflectionsFactory;
import com.yuhans.reflections.factory.impl.ReflectionsSimpleFactory;
import com.yuhans.runner.impl.MethodSimpleRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main class for running application. You can provide name of the specific package as the first argument (for example
 * com.yuhans.test) to search methods in it or just run the program without any arguments to search methods in all
 * packages.
 */
public class App {
    
    private static final Logger LOG = LoggerFactory.getLogger(App.class);
    
    /**
     * Name of root package. Used by default if no packageName for searching is provided.
     */
    private static final String ROOT_PACKAGE = App.class.getPackageName();

    public static void main(String[] args) {
        final ReflectionsFactory reflectionsFactory = new ReflectionsSimpleFactory();

        final MethodInvocationFacade methodInvocationFacade = new MethodInvocationFacade(
                new MethodSimpleFinder(reflectionsFactory.newInstance(getSearchedPackageName(args))),
                new MethodSimpleRunner());

        final Class<MiniTest> miniTestAnnotation = MiniTest.class;
        final int successfulInvocations = methodInvocationFacade.invokeAllMethodsWith(miniTestAnnotation);
        LOG.info("App completed with {} invocations of methods annotated by {}", successfulInvocations, miniTestAnnotation);
    }

    private static String getSearchedPackageName(String[] args)
    {
        return args.length == 0 ? ROOT_PACKAGE : args[0];
    }
}
