package com.semye.annotation.processor;

import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;

/**
 * 自定义注解处理器
 */
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes({"com.semye.annotation.SemyeClass",
        "com.semye.annotation.SemyeSource",
        "com.semye.annotation.SemyeRuntime"})
public class SemyeProcessor extends AbstractProcessor {
    private Elements elements;
    private Filer filer;
    private Types types;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        elements = processingEnv.getElementUtils();
        filer = processingEnv.getFiler();
        types = processingEnv.getTypeUtils();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (annotations.size() == 0) return false;
        Messager messager = processingEnv.getMessager();
        messager.printMessage(Diagnostic.Kind.NOTE, "hello=============" + annotations.toString());
        messager.printMessage(Diagnostic.Kind.NOTE, "=========");
        messager.printMessage(Diagnostic.Kind.NOTE, "hello=============" + roundEnv.toString());
        messager.printMessage(Diagnostic.Kind.NOTE, "<<=============>>");
        for (TypeElement typeElement : annotations) {
            for (Element element : roundEnv.getRootElements()) {
                //messager.printMessage(Diagnostic.Kind.NOTE, "hello=============" + element.toString());
                List<? extends Element> elements1 = elements.getAllMembers(typeElement);
                //messager.printMessage(Diagnostic.Kind.NOTE, "hello=============" + elements1.toString());
                List<? extends AnnotationMirror> list = elements.getAllAnnotationMirrors(element);

                //messager.printMessage(Diagnostic.Kind.NOTE, "hello=============" + list.toString());
                //messager.printMessage(Diagnostic.Kind.NOTE, "hello=============" + elements1.toString());

                //// 在元素上调用接口获取注解值
                //ButtonType annoValue = element.getAnnotation(ButtonType.class);
                //messager.printMessage(Diagnostic.Kind.NOTE, "====>" + annoValue.toString());
            }
        }


        return true;
    }
}
