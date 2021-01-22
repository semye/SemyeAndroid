package com.semye.base.pattern.creational.builder;

/**
 * Created by yesheng on 2016/12/30.
 * 汉堡
 */
public abstract class Burger implements Item {

    @Override
    public Packing packing() {
        return new Wrapper();
    }

    @Override
    public abstract float price();
}
