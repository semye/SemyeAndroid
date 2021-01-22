package com.semye.base.pattern.structural.decorator;

/**
 * Created by yesheng on 2016/12/30.
 * 抽象装饰类
 */
public abstract class ShapeDecorator implements Shape {

    protected Shape decoratedShape;

    public ShapeDecorator(Shape decoratedShape) {
        this.decoratedShape = decoratedShape;
    }

    @Override
    public void draw() {
        decoratedShape.draw();
    }
}
