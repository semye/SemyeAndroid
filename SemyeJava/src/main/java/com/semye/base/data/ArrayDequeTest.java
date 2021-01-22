package com.semye.base.data;

import java.util.ArrayDeque;

/**
 * ArrayDeque使用
 * 双向队列
 */
public class ArrayDequeTest {
    public static void main(String[] args) {

        ArrayDeque<String> arrayDeque = new ArrayDeque<>();
        arrayDeque.addFirst("yesheng");
        arrayDeque.addLast("ye");
        System.out.println(arrayDeque.toString());

    }
}
