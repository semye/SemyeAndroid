package com.semye.base.lang.thread;

public class CustomRunnable implements Runnable {

    private static final ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    @Override
    public void run() {
        System.out.println("等待三秒后唤起另一个线程");
        try {
            Thread.sleep(5000);
            synchronized (threadLocal) {//获取对象监视器
                threadLocal.notify();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
