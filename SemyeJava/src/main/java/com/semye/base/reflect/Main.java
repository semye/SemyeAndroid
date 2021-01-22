package com.semye.base.reflect;


import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;

/**
 * 反射机制
 * 1.反射API的使用
 * 2.动态代理
 * 带有Declared修饰的方法可以反射到私有的方法，
 * 没有Declared修饰的只能用来反射公有的方法。
 * 反射使用的类
 * Constructor 构造方法
 * Annotation 注解
 * Field 属性
 * Method 方法
 * Parameter 参数
 * Type 类型
 * <p>
 * WildcardType 通配符类型
 */
public class Main {


    public static void main(String[] args) {
        try {
            Class<?> clazz = Class.forName("com.semye.base.target.TargetA");
            isAnnotationPresent(clazz);
            Constructor<?> constructor = clazz.getDeclaredConstructor();
            System.out.println("设置构造方法可访问");
            AccessibleObject.setAccessible(new AccessibleObject[]{constructor}, true);
            constructor.setAccessible(true);
            Object instance = constructor.newInstance();
            System.out.println(instance.toString());
            getAnnotations(clazz);
            getMethods(clazz);
            getDeclaredMethods(clazz);
            getFields(clazz);
            getDeclaredFields(clazz);
            getInterface(clazz);
            getDeclaredClasses(clazz);
            getAnnotatedInterfaces(clazz);
            getAnnotatedSuperclass(clazz);
            getGenericInterfaces(clazz);
            getParameters(clazz);
            proxy();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private static void test1() {
        String a = "hello world";
        Object aa = Proxy.newProxyInstance(a.getClass().getClassLoader(), a.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return null;
            }
        });
        System.out.println(aa);

        System.out.println(a.length());
    }

    /**
     * 动态代理的使用
     */
    private static void proxy() throws ClassNotFoundException {
        Class<?> clazz = Class.forName("com.semye.base.target.TargetA");
        Object a = Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return null;
            }
        });
    }

    private static void isAnnotationPresent(Class<?> clazz) {
        System.out.println("判断反射的类是否有指定的注解");
        boolean annotationPresent = clazz.isAnnotationPresent(SafeVarargs.class);
        System.out.println(annotationPresent);
    }

    private static void getParameters(Class<?> clazz) {
        System.out.println("获取反射类的方法参数");
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            Parameter[] parameters = method.getParameters();
            for (Parameter parameter : parameters) {
                System.out.println(parameter);
            }
        }
    }

    private static void getGenericInterfaces(Class<?> clazz) {
        System.out.println("获取反射类的接口泛型");
        Type[] genericInterfaces = clazz.getGenericInterfaces();
        for (Type genericInterface : genericInterfaces) {
            System.out.println(genericInterface);
        }
    }

    private static void getAnnotatedSuperclass(Class<?> clazz) {
        System.out.println("获取带注解的父类");
        AnnotatedType annotatedSuperclass = clazz.getAnnotatedSuperclass();
        System.out.println(annotatedSuperclass);
    }

    private static void getAnnotatedInterfaces(Class<?> clazz) {
        System.out.println("获取反射类的带注解的接口");
        AnnotatedType[] annotatedInterfaces = clazz.getAnnotatedInterfaces();
        for (AnnotatedType annotatedInterface : annotatedInterfaces) {
            System.out.println(annotatedInterface);
        }
    }

    private static void getDeclaredClasses(Class<?> clazz) {
        System.out.println("获取反射类的内部类");
        Class<?>[] declaredClasses = clazz.getDeclaredClasses();
        for (Class<?> declaredClass : declaredClasses) {
            System.out.println(declaredClass);
        }
    }

    private static void getInterface(Class<?> clazz) {
        System.out.println("获取反射类的接口");
        Class<?>[] interfaces = clazz.getInterfaces();
        for (Class<?> anInterface : interfaces) {
            System.out.println(anInterface);
        }
    }

    private static void getFields(Class<?> clazz) {
        System.out.println("获取反射类的公开属性");
        Field[] fields = clazz.getFields();
        for (Field field : fields) {
            System.out.println(field);
        }
    }

    private static void getDeclaredFields(Class<?> clazz) {
        System.out.println("获取反射类的所有属性");
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field);
        }
    }

    private static void getMethods(Class<?> clazz) {
        System.out.println("获取反射类的公开方法");
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            System.out.println(method);
        }
    }

    private static void getDeclaredMethods(Class<?> clazz) {
        System.out.println("获取反射类的所有方法");
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method);
        }
    }

    private static void getAnnotations(Class<?> clazz) {
        System.out.println("获取反射类的所有注解");
        Annotation[] declaredAnnotations = clazz.getDeclaredAnnotations();
        for (Annotation declaredAnnotation : declaredAnnotations) {
            System.out.println(declaredAnnotation);
        }
    }

}
