package com.semye.base.lang.thread;

/**
 * priority 线程优先级
 * daemon 守护进程
 * <p>
 * 新线程的优先级等于创建它的线程优先级
 * <p>
 * <p>
 * {@link #yield()}
 * {@link #sleep(long)}
 * {@link #start()}
 * {@link #run()}
 */
public class CustomThread extends Thread {

    private static final ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    private int type = 0;

    public CustomThread(int type) {
        this.type = type;
    }

    @Override
    public void run() {
        super.run();
        System.out.println("CustomThread");
        try {
            if (type == 0) {
                threadLocal.set(0);
                System.out.println(threadLocal.toString());
                Integer result = threadLocal.get();
                System.out.println("type:" + type + "=====>" + result);
            } else {
                System.out.println(threadLocal.toString());
                Integer result = threadLocal.get();
                System.out.println("type:" + type + "=====>" + result);
            }
            System.out.println("线程准备休眠4秒");
            Thread.sleep(4000);
            System.out.println("线程休眠完毕,准备进入等待状态");
//            synchronized (count) {
//                count.wait();
//            }
//            System.out.println("我被唤起了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
