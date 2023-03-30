package com.semye.android.ui.thirdparty.dagger2.component


import com.semye.android.ui.thirdparty.dagger2.SecondActivity
import com.semye.android.ui.thirdparty.dagger2.module.LoginModule
import com.semye.android.ui.thirdparty.dagger2.module.RegisterModule
import dagger.Component

@Component(modules = [LoginModule::class, RegisterModule::class])
interface MineComponent {
    fun inject(secondActivity: SecondActivity)
}