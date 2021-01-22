package com.semye.base.pattern.structural.proxy;

/**
 * Created by yesheng on 2018/10/20.
 */
public class Proxy implements Subject {

    private Subject subject;

    public Proxy(Subject subject) {
        this.subject = subject;
    }

    @Override
    public void login() {
        subject.login();
    }
}
