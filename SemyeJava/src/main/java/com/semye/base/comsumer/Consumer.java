package com.semye.base.comsumer;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * Created by semye on 2018/8/26.
 */
public class Consumer implements Runnable {


    public static final int SLEEP_TIME = 1000;

    private BlockingQueue<String> queue;

    public Consumer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {

        Random random = new Random();
        try {

            while (true) {
                String data = queue.take();
                Thread.sleep(random.nextInt(SLEEP_TIME));

                System.out.println("====>" + data);
            }


        } catch (Exception e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }

    }
}
