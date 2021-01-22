package com.semye.base.pattern.creational.factorymethod;

/**
 * Created by yesheng on 2018/10/19.
 */
public class ProductFactory extends Factory {


    @Override
    public Product createProduct() {
        return new Product1();
    }
}
