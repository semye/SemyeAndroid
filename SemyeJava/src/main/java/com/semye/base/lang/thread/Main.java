package com.semye.base.lang.thread;

/**
 * 线程相关
 *
 * @see Thread 线程
 * @see ThreadGroup 线程组
 * @see ThreadLocal 修饰线程本地变量
 * <p>
 * 线程状态
 * @see Thread.State
 * new 新建状态
 * runnable 可运行状态
 * blocked 阻塞状态 等待监控锁 monitor lock
 * waiting 等待状态 线程等待状态是等待另一个线程执行特定的操作
 * timed_waiting 定时等待状态 sleep触发
 * terminated 线程终止状态
 * <p>
 * 线程继续执行,除非遇到以下两种情况
 * 1.Runtime.exit方法被调用并且安全管理器已允许退出操作
 * 2.所有非守护线程都死亡
 * <p>
 * 线程创建方式
 * 1.实现Runnable接口
 * 2.继承Thread
 * <p>
 * 线程方法
 * yield 使线程暂停并允许执行其他线程
 * 1.对debug和test很有用
 * 2.可以减少由于竞争条件产生的bug
 * 3.当使用locks设计并发控制结构时很有用
 * join  A线程调用B线程的join方法，A线程会被阻塞，要等到B线程执行完成后才会执行A线程
 * interrupt 中断线程
 * @see ThreadLocal
 * 通常在线程里定义成private static final
 * 所有该线程的对象共享此ThreadLocal
 * ThreadLocal里维护了一个table
 * ThreadLocal内存泄漏 是由于table的key为null value不为null
 * 解决内存泄漏需要调用remove方法
 */
public class Main {

    public static void main(String[] args) {
        try {
            CustomThread thread1 = new CustomThread(0);
            thread1.start();
            thread1.join();
            CustomThread thread2 = new CustomThread(1);
            thread2.start();
            Thread thread = new Thread(new CustomRunnable());
            thread.start();
            thread.join();
            Thread.sleep(3000);
            System.out.println("thread 线程状态:" + thread.getState());
            System.out.println("thread1 线程状态:" + thread1.getState());
            System.out.println("thread2 线程状态:" + thread2.getState());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void printThreadInfo(Thread thread) {
        // 新创建的线程优先级等于创建它的线程优先级
        System.out.println("线程名:" + thread.getName());
        System.out.println("线程优先级：" + thread.getPriority());
        System.out.println("线程ID：" + thread.getId());
        System.out.println("线程状态：" + thread.getState());
    }

    private static void printThreadGroupInfo(Thread thread) {
        ThreadGroup threadGroup = thread.getThreadGroup();
        if (threadGroup != null) {
            System.out.println("线程组:" + threadGroup.getName());
            System.out.println("现场组活动线程数" + threadGroup.activeCount());
        }
    }
}
