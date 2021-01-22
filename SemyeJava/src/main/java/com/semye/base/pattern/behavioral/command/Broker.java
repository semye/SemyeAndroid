package com.semye.base.pattern.behavioral.command;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yesheng on 2016/12/30.
 */
public class Broker {

    private final List<Order> orderList = new ArrayList<Order>();

    public void takeOrder(Order order) {
        orderList.add(order);
    }

    public void placeOrders() {
        for (Order order : orderList) {
            order.execute();
        }
        orderList.clear();
    }

}
