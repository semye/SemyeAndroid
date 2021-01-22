package com.semye.base.pattern.structural.decorator;

/**
 * Created by yesheng on 2016/12/30.
 * 装饰器模式
 */
public class Client {


    public static void main(String[] args) {

        Shape circle = new Circle();
        System.out.println("Circle with normal border");
        circle.draw();

        Shape redCircle = new RedShapeDecorator(new Circle());
        System.out.println("Circle of red border");
        redCircle.draw();

        Shape redRectangle = new RedShapeDecorator(new Rectangle());
        System.out.println("Rectangle of red border");
        redRectangle.draw();
    }

}
