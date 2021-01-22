package com.semye.base.pattern.creational.builder;

/**
 * Created by yesheng on 2016/12/30.
 */
public class ChickenBurger extends Burger {

    @Override
    public float price() {
        return 50.5f;
    }

    @Override
    public String name() {
        return "Chicken Burger";
    }
}
