package com.semye.base.pattern.structural.adapter;


/**
 * Created by yesheng on 2018/10/22.
 */
public class Client {

    public static void main(String[] args) {
        //类适配器的使用
        Target target1 = new ClassAdapter();
        target1.methodB();

        //对象适配器的使用
        Target target2 = new ObjectAdapter();
        target2.methodB();
    }
}
