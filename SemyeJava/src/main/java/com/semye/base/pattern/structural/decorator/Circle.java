package com.semye.base.pattern.structural.decorator;

/**
 * Created by yesheng on 2016/12/30.
 * 圆形
 */
public class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Shape: Circle");
    }
}
