package com.semye.base.pattern.creational.abstractfactory;

/**
 * Created by yesheng on 2018/10/19.
 */
public class Client {


    public static void main(String[] args) {
        //抽象工厂的使用方法

        AbstractFactory abstractFactory1 = new ConcreteFactory1();
        AbstractProductA abstractProductA1 = abstractFactory1.createProductA();
        AbstractProductB abstractProductB1 = abstractFactory1.createProductB();

        AbstractFactory abstractFactory2 = new ConcreteFactory2();
        AbstractProductA abstractProductA2 = abstractFactory2.createProductA();
        AbstractProductB abstractProductB2 = abstractFactory2.createProductB();


    }
}
