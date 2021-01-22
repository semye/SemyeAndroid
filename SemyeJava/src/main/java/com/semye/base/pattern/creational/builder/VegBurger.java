package com.semye.base.pattern.creational.builder;

/**
 * Created by yesheng on 2016/12/30.
 */
public class VegBurger extends Burger {

    @Override
    public float price() {
        return 25.0f;
    }

    @Override
    public String name() {
        return "Veg Burger";
    }
}
