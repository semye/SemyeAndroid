package com.semye.annotation

@MustBeDocumented
@Target(
    AnnotationTarget.ANNOTATION_CLASS,
    AnnotationTarget.CLASS,
    AnnotationTarget.FIELD,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER,
    AnnotationTarget.VALUE_PARAMETER
)
@kotlin.annotation.Retention(AnnotationRetention.BINARY)
annotation class SemyeClass(val name: String = "semye", val value: Int = 0)