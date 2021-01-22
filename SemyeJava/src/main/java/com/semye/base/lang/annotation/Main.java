//package com.semye.base.lang.annotation;
//
//
//import com.semye.annotation.SemyeRuntime;
//import com.semye.annotation.SemyeSource;
//
///**
// * 注解的使用
// * 1.编译检查
// * 2.在反射中使用Annotation 反射API中有许多关于注解的接口
// * 3.根据Annotation生成帮助文档
// * <p>
// * RetentionPolicy 注解保留策略
// * Source 源码阶段
// * Class  编译阶段
// * Runtime 运行时   反射注解必须使用runtime
// * <p>
// * 可参考 https://www.jianshu.com/p/acbb293722bc
// *
// * @see javax.annotation.processing.AbstractProcessor 自定义注解处理器
// */
//public class Main {
//
//    public static void main(String[] args) {
//        if (Foo.class.isAnnotationPresent(SemyeRuntime.class)) {
//            SemyeRuntime semye = Foo.class.getAnnotation(SemyeRuntime.class);
//            if (semye != null) {
//                System.out.println(semye.name());
//                System.out.println(semye.value());
//            }
//        }
//
//        click(9);
//    }
//
//    public static void click(@SemyeSource int type) {
//        System.out.println(type);
//    }
//}
