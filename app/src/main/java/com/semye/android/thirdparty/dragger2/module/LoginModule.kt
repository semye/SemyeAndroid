package com.semye.android.thirdparty.dragger2.module

import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient

@Module
class LoginModule {


    @Provides
    fun provideOkhttpClient(): OkHttpClient {
        return OkHttpClient()
    }
}