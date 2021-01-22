package com.semye.base.pattern.behavioral.strategy;

/**
 * Created by yesheng on 2016/12/30.
 */
public class Context {

    private Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public int executeStrategy(int num1, int num2) {
        return strategy.doOperation(num1, num2);
    }
}
