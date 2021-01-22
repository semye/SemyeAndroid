package com.semye.base.pattern.behavioral.strategy;

/**
 * Created by yesheng on 2016/12/30.
 */
public class OperationMultiply implements Strategy{
    @Override
    public int doOperation(int num1, int num2) {
        return num1 * num2;
    }
}