package com.semye.android.thirdparty.dagger2.module

import com.semye.android.thirdparty.dagger2.model.MyCustom
import dagger.Module
import dagger.Provides

@Module
class RegisterModule {

    @Provides
    fun provideMyCustom(): MyCustom {
        return MyCustom()
    }
}