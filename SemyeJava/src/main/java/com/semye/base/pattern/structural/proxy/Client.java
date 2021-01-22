package com.semye.base.pattern.structural.proxy;

/**
 * Created by yesheng on 2018/10/20.
 * 代理模式
 */
public class Client {

    public static void main(String[] args) {
        Proxy proxy = new Proxy(new RealSubject());
        proxy.login();
    }
}
