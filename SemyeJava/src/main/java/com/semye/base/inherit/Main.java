package com.semye.base.inherit;

/**
 * 父类与子类的探究
 * <p>
 * 结论:
 * static会在类加载时执行,只会执行一次
 * static代码块的执行优先级大于父类和子类的构造方法的执行优先级
 * 父类和子类中都存在静态代码块时,父类的静态代码块优先执行
 * 父类的成员变量的构造方法执行优先级大于父类的构造方法的执行优先级
 * 父类的构造方法执行优先级大于子类的构造方法执行优先级
 * <p>
 * 成员变量>构造方法
 */
public class Main {

    public static void main(String[] args) {
        Child child = new Child();
        child.function();
        child.function1();
        System.out.println(child.toString());
        System.out.println(Parent.sFoo);
        System.out.println(Child.sFoo);
    }

    /**
     * 测试泛型
     * ？通配符
     * 通常用T表示泛型
     * List<? extends Parent>与List<T extends Parent>的区别
     */
    public static void testGenerics() {

    }
}
