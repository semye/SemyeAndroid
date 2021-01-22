package com.semye.android.thirdparty.dragger2;

import javax.inject.Inject;

/**
 * Created by yesheng on 2017/3/9.
 */
public class Item {

    private String name;

    // 用Inject标记构造函数,表示用它来注入到目标对象中去
    @Inject
    public Item() {
        name = "dagger";
    }

    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void println() {
        System.out.println("=====>hello world!");
    }
}
