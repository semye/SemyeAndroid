package com.semye.base.pattern.behavioral.observer;

/**
 * Created by yesheng on 2016/12/30.
 */
public abstract class Observer {

    protected Subject subject;
    public abstract void update();
}
