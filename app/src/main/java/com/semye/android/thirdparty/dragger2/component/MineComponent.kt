package com.semye.android.thirdparty.dragger2.component

import com.semye.android.thirdparty.dragger2.SecondActivity
import com.semye.android.thirdparty.dragger2.module.LoginModule
import com.semye.android.thirdparty.dragger2.module.RegisterModule
import dagger.Component

@Component(modules = [LoginModule::class, RegisterModule::class])
interface MineComponent {
    fun inject(secondActivity: SecondActivity)
}