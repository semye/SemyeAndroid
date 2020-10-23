package com.semye.android.dragger2;

import dagger.Component;

/**
 * Created by yesheng on 2017/3/9.
 */
//用@Component表示这个接口是一个连接器，能用@Component注解的只
//能是interface或者抽象类
@Component(modules = MainModule.class)
public interface MainComponent {


    void inject(MainActivity daggerActivity);
}
