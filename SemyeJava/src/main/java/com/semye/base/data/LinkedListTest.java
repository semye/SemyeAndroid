package com.semye.base.data;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by yesheng on 2020/12/29
 * Doubly-linked
 * 双向链表
 * 实现了list和deque
 * 元素可以为null
 * 线程不安全
 */
public class LinkedListTest {


    public static final List<String> wow = Arrays.asList("法师", "萨满祭司", "盗贼", "德鲁伊", "圣骑士", "牧师", "术士", "战士", "武僧", "恶魔猎手", "死亡骑士", "猎人");

    public static void main(String[] args) {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add(null);
        linkedList.add(null);
        linkedList.add(null);
        linkedList.add("法师");
        linkedList.add("萨满祭司");
        linkedList.add("盗贼");
        linkedList.add("德鲁伊");
        linkedList.add("圣骑士");
        linkedList.add("牧师");
        linkedList.add("术士");
        linkedList.add("战士");
        linkedList.add("武僧");
        linkedList.add("恶魔猎手");
        linkedList.add("死亡骑士");
        linkedList.add("猎人");

        // 返回列表的第一个元素并从列表中移除该元素
        String first = linkedList.poll();
        System.out.println(first);
        System.out.println(linkedList.toString());

        // 从列表中移除第一个元素
        String s = linkedList.pop();
        System.out.println(s);
        System.out.println(linkedList.toString());

        // 添加元素到列表头
        linkedList.addFirst("法师");

        // 移除列头的元素 如果列表为空会抛出异常
        String a = linkedList.remove();
        System.out.println(a);
        System.out.println(linkedList.toString());

        // 返回列表的第一个元素 如果列表为空返回null
        String result = linkedList.peek();
        System.out.println(result);
        System.out.println(linkedList.toString());

        // 返回列表的第一个元素 如果列表为空会抛出异常 这是peek和element的区别
        String result2 = linkedList.element();
        System.out.println(result2);
        System.out.println(linkedList.toString());

        // 获取列表的第一个元素
        String getfirst = linkedList.getFirst();
        System.out.println(getfirst);

        // 获取列表的最后一个元素
        String getlast = linkedList.getLast();
        System.out.println(getlast);

    }
}
