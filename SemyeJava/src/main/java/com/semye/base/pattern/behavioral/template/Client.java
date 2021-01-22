package com.semye.base.pattern.behavioral.template;

/**
 * Created by yesheng on 2018/10/20.
 * 模板方法
 */
public class Client {

    public static void main(String[] args) {
        Template template = new AppA();
        template.createApp();

        Template templateb = new AppB();
        templateb.createApp();
    }

}
