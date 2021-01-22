package com.semye.base.pattern.creational.builder;

/**
 * Created by yesheng on 2016/12/30.
 * 冷饮
 */
public abstract class ColdDrink implements Item {

    @Override
    public Packing packing() {
        return new Bottle();
    }

    @Override
    public abstract float price();
}