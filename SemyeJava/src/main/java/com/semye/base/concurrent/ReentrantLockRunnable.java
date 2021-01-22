package com.semye.base.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by yesheng on 2020/12/5
 */
public class ReentrantLockRunnable implements Runnable {

    private int count;

    /**
     * 使用ReentrantLock实现悲观锁时需保证多个线程使用同一个锁
     */
    private static final ReentrantLock reentrantLock = new ReentrantLock();

    private final Condition condition;

    public ReentrantLockRunnable(int count) {
        this.count = count;
        condition = reentrantLock.newCondition();
    }


    @Override
    public void run() {
        reentrantLock.lock();
        count++;
        System.out.println("线程名:" + Thread.currentThread().getName() + " 线程值:" + count);
        reentrantLock.unlock();
    }
}
