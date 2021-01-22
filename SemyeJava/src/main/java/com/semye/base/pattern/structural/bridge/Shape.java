package com.semye.base.pattern.structural.bridge;

/**
 * Created by yesheng on 2018/10/20.
 */
public abstract class Shape {

    protected Color color;

    public Shape(Color color) {
        this.color = color;
    }

    public abstract void draw();
}
