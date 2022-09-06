package com.semye.android.thirdparty.dragger2.component

import com.semye.android.thirdparty.dragger2.Dragger2SampleActivity
import com.semye.android.thirdparty.dragger2.MyCustom
import com.semye.android.thirdparty.dragger2.module.LoginModule
import com.semye.android.thirdparty.dragger2.module.MainModule
import dagger.Component

/**
 * Created by yesheng on 2017/3/9.
 */
//用@Component表示这个接口是一个连接器，能用@Component注解的只
//能是interface或者抽象类
@Component(modules = [MainModule::class, LoginModule::class])
interface MainComponent {
    /**
     * 注入到Dragger2SampleActivity
     */
    fun inject(daggerActivity: Dragger2SampleActivity?)


}