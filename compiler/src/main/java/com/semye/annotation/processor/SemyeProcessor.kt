package com.semye.annotation.processor

import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.Filer
import javax.annotation.processing.ProcessingEnvironment
import javax.annotation.processing.RoundEnvironment
import javax.annotation.processing.SupportedAnnotationTypes
import javax.annotation.processing.SupportedSourceVersion
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement
import javax.lang.model.util.Elements
import javax.lang.model.util.Types
import javax.tools.Diagnostic

/**
 * 自定义注解处理器
 */
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes(
    "com.semye.annotation.SemyeClass",
    "com.semye.annotation.SemyeSource",
    "com.semye.annotation.SemyeRuntime"
)
class SemyeProcessor : AbstractProcessor() {
    private var elements: Elements? = null
    private var filer: Filer? = null
    private var types: Types? = null
    @Synchronized
    override fun init(processingEnv: ProcessingEnvironment) {
        super.init(processingEnv)
        elements = processingEnv.elementUtils
        filer = processingEnv.filer
        types = processingEnv.typeUtils
    }

    override fun process(annotations: Set<TypeElement>, roundEnv: RoundEnvironment): Boolean {
        if (annotations.size == 0) return false
        val messager = processingEnv.messager
        messager.printMessage(Diagnostic.Kind.NOTE, "hello=============$annotations")
        messager.printMessage(Diagnostic.Kind.NOTE, "=========")
        messager.printMessage(Diagnostic.Kind.NOTE, "hello=============$roundEnv")
        messager.printMessage(Diagnostic.Kind.NOTE, "<<=============>>")
        for (typeElement in annotations) {
            for (element in roundEnv.rootElements) {
                //messager.printMessage(Diagnostic.Kind.NOTE, "hello=============" + element.toString());
                val elements1 = elements!!.getAllMembers(typeElement)
                //messager.printMessage(Diagnostic.Kind.NOTE, "hello=============" + elements1.toString());
                val list = elements!!.getAllAnnotationMirrors(element)

                //messager.printMessage(Diagnostic.Kind.NOTE, "hello=============" + list.toString());
                //messager.printMessage(Diagnostic.Kind.NOTE, "hello=============" + elements1.toString());

                //// 在元素上调用接口获取注解值
                //ButtonType annoValue = element.getAnnotation(ButtonType.class);
                //messager.printMessage(Diagnostic.Kind.NOTE, "====>" + annoValue.toString());
            }
        }
        return true
    }
}