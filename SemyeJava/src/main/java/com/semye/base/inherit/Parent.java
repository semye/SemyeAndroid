package com.semye.base.inherit;

public class Parent {

    static {
        System.out.println("父类静态块");
    }

    public static final Foo sFoo = new Foo("parent static foo");

    public final Foo mFoo = new Foo("parent foo");

    public Parent() {
        System.out.println("父类的构造方法");
        System.out.println(mFoo.toString());
    }

    public void function() {
        System.out.println("父类执行function");
    }

    public void function1() {
        System.out.println("父类执行function1");
    }

}
