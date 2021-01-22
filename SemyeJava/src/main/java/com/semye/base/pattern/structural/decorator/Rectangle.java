package com.semye.base.pattern.structural.decorator;

/**
 * Created by yesheng on 2016/12/30.
 * 矩形
 */
public class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Shape: Rectangle");
    }
}
