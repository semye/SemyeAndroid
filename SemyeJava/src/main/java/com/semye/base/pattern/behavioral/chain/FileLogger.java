package com.semye.base.pattern.behavioral.chain;

/**
 * Created by yesheng on 2016/12/30.
 */
public class FileLogger extends AbstractLogger {

    public FileLogger(int level){
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("File::Logger: " + message);
    }
}
