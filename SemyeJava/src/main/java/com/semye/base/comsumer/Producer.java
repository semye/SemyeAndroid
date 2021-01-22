package com.semye.base.comsumer;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by semye on 2018/8/26.
 * 生产者
 */
public class Producer implements Runnable {

    private volatile boolean isRunning = true;


    public Producer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    /**
     * 内存缓冲区
     */
    private BlockingQueue<String> queue;

    private static AtomicInteger count = new AtomicInteger(); //总数  原子操作

    private static final int SLEEP_TIME = 1000;

    @Override
    public void run() {

        String a = String.valueOf(count.incrementAndGet());

        Random random = new Random();

        System.out.println("start producing id" + Thread.currentThread().getId());


        try {
            while (isRunning) {
                Thread.sleep(random.nextInt(SLEEP_TIME));
                if (!queue.offer(a, 2, TimeUnit.SECONDS)) {
                    System.out.println("加入队列失败");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public void stop() {
        isRunning = false;
    }
}
