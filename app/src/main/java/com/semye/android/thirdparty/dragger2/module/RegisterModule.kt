package com.semye.android.thirdparty.dragger2.module

import com.semye.android.thirdparty.dragger2.MyCustom
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient

@Module
class RegisterModule {

    @Provides
    fun provideMyCustom(): MyCustom {
        return MyCustom()
    }
}