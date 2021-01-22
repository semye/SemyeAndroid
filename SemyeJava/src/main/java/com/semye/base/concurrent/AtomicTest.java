package com.semye.base.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

/**
 * atomic package
 * <p>
 * CAS(compare and swap)操作和while循环来实现原子性
 * 原子类的使用
 */
public class AtomicTest {

    private static final AtomicInteger atomicInteger = new AtomicInteger(1);

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        IntStream.range(1, 100).forEach(new IntConsumer() {
            @Override
            public void accept(int value) {
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        int i = atomicInteger.getAndIncrement();
                        System.out.println("=====>" + i);
                    }
                });
            }
        });
        executorService.shutdown();
        // 线程执行完毕后再查看变量值
        System.out.println(atomicInteger.get());
    }
}
