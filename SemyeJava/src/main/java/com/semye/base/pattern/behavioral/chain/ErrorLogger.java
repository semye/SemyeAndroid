package com.semye.base.pattern.behavioral.chain;

/**
 * Created by yesheng on 2016/12/30.
 */
public class ErrorLogger extends AbstractLogger {

    public ErrorLogger(int level){
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("Error Console::Logger: " + message);
    }
}
