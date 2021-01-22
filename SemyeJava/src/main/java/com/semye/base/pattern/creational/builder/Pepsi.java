package com.semye.base.pattern.creational.builder;

/**
 * Created by yesheng on 2016/12/30.
 */
public class Pepsi extends ColdDrink {

    @Override
    public float price() {
        return 35.0f;
    }

    @Override
    public String name() {
        return "Pepsi";
    }
}
