package com.yuhans.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for labeling methods that should be invoked as test methods.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MiniTest {

    /**
     * Shows expected Throwable which indicates that test method succeed
     *
     * @return class of Throwable which indicates that test method succeed
     */
    Class<? extends Throwable> expected() default Default.class;

    class Default extends Throwable {

        private static final long serialVersionUID = -5185121224862300115L;

        private Default() {
        }
    }
}
