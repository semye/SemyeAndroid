package com.semye.base.pattern.creational.singleton;

/**
 * Created by yesheng on 2018/10/22.
 */
public class Client {

    public static void main(String[] args) {
        Singleton singleton = Singleton.getInstance();
        Singleton singleton1 = Singleton.getInstance();
        System.out.println(singleton.toString());
        System.out.println(singleton1.toString());

        LazySingleton lazySingleton = LazySingleton.getInstance();
        LazySingleton lazySingleton1 = LazySingleton.getInstance();
        System.out.println(lazySingleton.toString());
        System.out.println(lazySingleton1.toString());

    }
}
