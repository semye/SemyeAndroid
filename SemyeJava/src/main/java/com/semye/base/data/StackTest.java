package com.semye.base.data;

import java.util.Stack;
import java.util.function.Consumer;

/**
 * Created by yesheng on 2020/12/30
 * last in first out 后进先出
 *
 * @see Stack#push(Object) 入栈
 * @see Stack#pop() 出栈
 * @see Stack#peek() 查看栈顶的元素
 * @see Stack#empty() 判断栈是否为空
 * @see Stack#search(Object) 查询栈中元素的index 从1开始
 */
class StackTest {

    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        Constants.WOW.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                stack.push(s);
            }
        });
        System.out.println("栈===>" + stack.toString());
        System.out.println("查看栈顶的元素:" + stack.peek());
        System.out.println("搜索法师的index:" + stack.search("法师"));
        while (!stack.empty()) {
            System.out.println(stack.pop());
        }
        System.out.println("栈===>" + stack.toString());
    }
}
