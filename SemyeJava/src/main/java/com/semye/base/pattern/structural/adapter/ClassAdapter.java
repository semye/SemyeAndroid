package com.semye.base.pattern.structural.adapter;

/**
 * Created by yesheng on 2018/10/22.
 * 类适配器
 */
public class ClassAdapter extends Adaptee implements Target {
    @Override
    public void methodB() {
        method();
    }
}
