package com.semye.base.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

/**
 * 可重入锁,和synchronized关键字实现的效果一样
 *
 * @see SynchronizedTest
 */
public class ReentrantLockTest {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ReentrantLockRunnable reentrantLockRunnable = new ReentrantLockRunnable(0);
        IntStream.range(1, 10).forEach(new IntConsumer() {
            @Override
            public void accept(int value) {
                executorService.execute(reentrantLockRunnable);
            }
        });
        executorService.shutdown();
    }
}
