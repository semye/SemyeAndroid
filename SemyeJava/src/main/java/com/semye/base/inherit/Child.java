package com.semye.base.inherit;

public class Child extends Parent {

    static {
        System.out.println("子类静态块");
    }

    public static final Foo sFoo = new Foo("child static foo");

    public final Foo mFoo = new Foo("child foo");

    public Child() {
        super();
        System.out.println("子类的构造方法");
        System.out.println(mFoo.toString());
    }

    @Override
    public void function() {
        super.function();
        System.out.println("子类执行function");
    }

    @Override
    public void function1() {
        System.out.println("子类执行function1");
    }
}
