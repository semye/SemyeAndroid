package com.semye.base.concurrent;

/**
 * Created by yesheng on 2020/12/5
 */
public class SynchronizedRunnable implements Runnable {

    private int count = 0;

    public SynchronizedRunnable(int count) {
        this.count = count;
    }

    @Override
    public void run() {
        synchronized (this) {
            count++;
            System.out.println("线程名:" + Thread.currentThread().getName() + " 打印的值:" + count);
        }
    }
}
