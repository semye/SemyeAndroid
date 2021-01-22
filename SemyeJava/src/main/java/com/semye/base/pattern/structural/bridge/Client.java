package com.semye.base.pattern.structural.bridge;

/**
 * Created by yesheng on 2018/10/20.
 */
public class Client {

    public static void main(String[] args) {


        Shape shape = new Circle(new Black());
        Shape shape1 = new Rectangle(new White());
        Shape shape2 = new Square(new Blue());

        shape.draw();


    }
}
