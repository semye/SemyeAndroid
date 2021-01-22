package com.semye.base.concurrent;

/**
 * Created by semye on 2020/12/3
 * volatile有两个作用
 * 1.修饰的变量再改变值时会及时的被线程观察到
 * 2.防止指令重排
 * 下面的文章里可以很好的理解指令重排
 * https://blog.csdn.net/a60782885/article/details/77803757
 *
 * volatile不保证原子性
 */
public class VolatileTest extends Thread {

    /**
     * 如果不使用volatile,线程里的循环有可能变成死循环
     */
    public volatile boolean flag = false;

    public static void main(String[] args) throws InterruptedException {
        VolatileTest volatileTest = new VolatileTest();
        volatileTest.start();
        Thread.sleep(1000);
        volatileTest.flag = true;
    }

    @Override
    public void run() {
        int i = 0;
        while (!flag) {
            i++;
        }
        System.out.println("stopped");
    }
}
