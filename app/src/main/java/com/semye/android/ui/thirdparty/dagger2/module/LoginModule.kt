package com.semye.android.ui.thirdparty.dagger2.module

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