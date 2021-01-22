package com.semye.base.object;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Object类
 * <p>
 * 对象方法的研究
 *
 * @see Object#hashCode()
 * @see Object#clone()
 * @see Object#toString()
 * @see Object#equals(Object)
 * @see Object#finalize()
 * @see Object#wait()
 * @see Object#wait(long)
 * @see Object#wait(long, int)
 * @see Object#notify()
 * @see Object#notifyAll()
 * 通过synchronized关键字获取对象的监视器
 */
public class ObjectMain {

    private static final ExecutorService executorService = Executors.newFixedThreadPool(5);
    private static final Object foo = new Object();

    public static void main(String[] args) {
        try {
            testClone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        testWaitAndNotify();

    }

    private static void testWaitAndNotify() {
        executorService.execute(new Action());
        //必须获取对象监视器才能调用wait() notify()等方法
        synchronized (foo) {
            try {
                foo.wait();
                System.out.println("好的");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        executorService.shutdownNow();
        System.out.println("关闭线程池");
    }

    /**
     * 可重写clone方法创建一个对象和原始对象一模一样的数据
     */
    private static void testClone() throws CloneNotSupportedException {
        TestClone testClone = new TestClone("abc");
        System.out.println(testClone.hashCode());
        System.out.println(testClone.abc);
        Object cloneObject = testClone.clone();
        System.out.println(cloneObject.hashCode());
        System.out.println(((TestClone) cloneObject).abc);
    }

    private static class TestClone implements Cloneable {
        public String abc;

        public TestClone(String abc) {
            this.abc = abc;
        }

        @Override
        public Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }


    private static class Action implements Runnable {
        @Override
        public void run() {
            System.out.println("子线程干活啦");
            try {
                Thread.sleep(5000);
                System.out.println("活干完啦,告诉大佬");
                synchronized (foo) {
                    foo.notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
