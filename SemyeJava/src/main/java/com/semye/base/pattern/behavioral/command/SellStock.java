package com.semye.base.pattern.behavioral.command;

/**
 * Created by yesheng on 2016/12/30.
 */
public class SellStock implements Order {
    private Stock abcStock;

    public SellStock(Stock abcStock){
        this.abcStock = abcStock;
    }

    public void execute() {
        abcStock.sell();
    }
}
