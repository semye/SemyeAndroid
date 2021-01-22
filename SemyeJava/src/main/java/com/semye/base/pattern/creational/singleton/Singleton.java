package com.semye.base.pattern.creational.singleton;

/**
 * Created by yesheng on 2018/10/22.
 * 单例模式 饿汉式 实现
 */
public class Singleton {

    private static final Singleton INSTANCE = new Singleton();


    private Singleton() {
    }

    public static Singleton getInstance() {
        return INSTANCE;
    }

}
