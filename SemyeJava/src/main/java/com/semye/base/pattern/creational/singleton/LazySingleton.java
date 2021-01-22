package com.semye.base.pattern.creational.singleton;

/**
 * Created by yesheng on 2016/12/30.
 * 懒汉式(饱汉式) 指全局的单例实例在第一次被使用时构建。
 */
public class LazySingleton {

    private LazySingleton() {
    }

    private static volatile LazySingleton single = null;

    public static LazySingleton getInstance() {
        if (single == null) {
            synchronized (Singleton.class) {
                if (single == null) {
                    single = new LazySingleton();
                }
            }
        }
        return single;
    }
}
