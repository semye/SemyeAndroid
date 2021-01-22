package com.semye.base.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

/**
 * Created by yesheng on 2020/12/5
 * synchronized 关键字的使用
 * 使用该关键字实现线程同步
 */
public class SynchronizedTest {

    public static void main(String[] args) {
        SynchronizedRunnable testThread = new SynchronizedRunnable(0);
        ExecutorService executorService = Executors.newCachedThreadPool();
        IntStream.range(1, 10).forEach(new IntConsumer() {
            @Override
            public void accept(int value) {
                executorService.execute(testThread);
            }
        });
        executorService.shutdown();
    }
}
