package com.semye.base.target;

public class FooWrapper {

    private Foo mFoo;

    public FooWrapper(Foo foo) {
        this.mFoo = foo;
    }

    public void setFoo(Foo foo) {
        this.mFoo = foo;
    }

    public Foo getFoo() {
        return mFoo;
    }

    public void println(Foo foo) {
        System.out.println(foo.toString());
    }

}
