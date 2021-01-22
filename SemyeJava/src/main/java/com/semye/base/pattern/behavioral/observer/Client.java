package com.semye.base.pattern.behavioral.observer;

/**
 * Created by yesheng on 2016/12/30.
 * 观察者模式
 */
public class Client {

    public static void main(String[] args) {
        Subject subject = new Subject();

        new HexaObserver(subject);
        new OctalObserver(subject);
        new BinaryObserver(subject);

        System.out.println("First state change: 15");
        subject.setState(15);
        System.out.println("Second state change: 10");
        subject.setState(10);
    }
}
