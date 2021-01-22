package com.semye.base.env;

import java.util.function.BiConsumer;

public class Main {

    public static void main(String[] args) {
        //读取环境变量
        System.getenv().forEach(new BiConsumer<String, String>() {
            @Override
            public void accept(String key, String value) {
                System.out.println(key + ":" + value);
            }
        });
        System.out.println("==================================================");
        //读取系统属性
        System.getProperties().forEach(new BiConsumer<Object, Object>() {
            @Override
            public void accept(Object s, Object s2) {
                System.out.println(s + ":" + s2);
            }
        });
    }
}
