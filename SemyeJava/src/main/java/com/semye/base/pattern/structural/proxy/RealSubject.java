package com.semye.base.pattern.structural.proxy;

/**
 * Created by yesheng on 2018/10/20.
 */
public class RealSubject implements Subject {

    @Override
    public void login() {
        System.out.println("real login");
    }
}
