package com.semye.base.pattern.structural.adapter;

/**
 * Created by yesheng on 2018/10/22.
 * 对象适配器
 */
public class ObjectAdapter implements Target {

    private Adaptee adaptee;

    public ObjectAdapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    public ObjectAdapter() {
        adaptee = new Adaptee();
    }

    @Override
    public void methodB() {
        if (adaptee != null) {
            adaptee.method();
        }

    }
}
