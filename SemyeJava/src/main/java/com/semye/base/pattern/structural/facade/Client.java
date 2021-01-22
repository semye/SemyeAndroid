package com.semye.base.pattern.structural.facade;

/**
 * Created by yesheng on 2018/10/20.
 * 外观模式
 */
public class Client {


    public static void main(String[] args) {
        ComputerFacade computerFacade = new ComputerFacade();
        computerFacade.start();
    }

}
