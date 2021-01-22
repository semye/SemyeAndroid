package com.semye.base.lang;


import com.semye.base.target.Foo;
import com.semye.base.target.FooWrapper;

/**
 * 内存地址
 */
public class Main {

    public static void main(String[] args) {
        Foo foo = new Foo();
        System.out.println(foo.toString());
        FooWrapper wrapper = new FooWrapper(foo);
        System.out.println(wrapper.toString());
        System.out.println(wrapper.getFoo().toString());
    }
}
