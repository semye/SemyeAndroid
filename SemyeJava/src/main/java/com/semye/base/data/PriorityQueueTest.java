package com.semye.base.data;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by yesheng on 2020/12/29
 * 优先级队列
 */
public class PriorityQueueTest {

    public static final List<String> wow = Arrays.asList("法师", "萨满祭司", "盗贼", "德鲁伊", "圣骑士", "牧师", "术士", "战士", "武僧", "恶魔猎手", "死亡骑士", "猎人");

    public static void main(String[] args) {
        PriorityQueue<String> queue = new PriorityQueue<>(wow);

        System.out.println("队列中的所有元素");
        System.out.println(queue.toString());
        System.out.println("======================================================================");
        System.out.println("使用peek获取队列中的第一个元素");
        System.out.println(queue.peek());
        System.out.println(queue.toString());
        System.out.println("======================================================================");
        System.out.println("使用poll获取队列中的第一个元素并从队列中移除");
        System.out.println(queue.poll());
        System.out.println(queue.toString());
        System.out.println("======================================================================");
        System.out.println("使用offer往队列中插入元素");
        boolean result = queue.offer("coder");
        System.out.println(result);
        System.out.println(queue.toString());
        System.out.println("======================================================================");
        System.out.println("使用add往队列中插入元素");
        boolean result1 = queue.add("singer");
        System.out.println(result1);
        System.out.println(queue.toString());
        System.out.println("======================================================================");
    }
}
