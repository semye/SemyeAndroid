package com.semye.base.pattern.behavioral.chain;

/**
 * Created by yesheng on 2016/12/30.
 */
public class ConsoleLogger extends AbstractLogger {

    public ConsoleLogger(int level){
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("Standard Console::Logger: " + message);
    }
}
