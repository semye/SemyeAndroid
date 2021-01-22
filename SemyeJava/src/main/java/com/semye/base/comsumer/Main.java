package com.semye.base.comsumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by semye on 2018/8/26.
 * 生产者消费者模式
 */
public class Main {


    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> blockingQueue = new LinkedBlockingDeque<>(10);
        Producer producer1 = new Producer(blockingQueue);
        Producer producer2 = new Producer(blockingQueue);
        Producer producer3 = new Producer(blockingQueue);
        Consumer consumer1 = new Consumer(blockingQueue);
        Consumer consumer2 = new Consumer(blockingQueue);
        Consumer consumer3 = new Consumer(blockingQueue);
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(producer1);
        executorService.execute(producer2);
        executorService.execute(producer3);
        executorService.execute(consumer1);
        executorService.execute(consumer2);
        executorService.execute(consumer3);

        Thread.sleep(10 * 1000);

        producer1.stop();
        producer2.stop();
        producer3.stop();
        Thread.sleep(3000);
        executorService.shutdown();
        System.out.println("执行完毕");
    }

}
