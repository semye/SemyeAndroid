package com.semye.base.target;

import java.lang.annotation.*;

/**
 * 这个注解是用来反射的
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface TargetAnnotation {

    String name();
}
