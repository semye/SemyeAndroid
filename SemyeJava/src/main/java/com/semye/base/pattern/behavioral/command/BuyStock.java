package com.semye.base.pattern.behavioral.command;

/**
 * Created by yesheng on 2016/12/30.
 */
public class BuyStock implements Order {
    private Stock abcStock;

    public BuyStock(Stock abcStock){
        this.abcStock = abcStock;
    }

    public void execute() {
        abcStock.buy();
    }
}
