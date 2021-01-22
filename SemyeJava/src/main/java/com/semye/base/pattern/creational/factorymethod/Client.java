package com.semye.base.pattern.creational.factorymethod;

/**
 * Created by yesheng on 2018/10/19.
 */
public class Client {

    public static void main(String[] args) {
        Factory factory = new ProductFactory();
        Product product = factory.createProduct();
        System.out.println(product.toString());
    }
}
