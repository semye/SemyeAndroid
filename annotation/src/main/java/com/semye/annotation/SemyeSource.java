package com.semye.annotation;

import java.lang.annotation.*;


@Documented
@Target({ElementType.TYPE,
        ElementType.FIELD,
        ElementType.METHOD,
        ElementType.PARAMETER
})
@Retention(RetentionPolicy.SOURCE)
public @interface SemyeSource {
    String name() default "semye";

    int value() default 0;
}
