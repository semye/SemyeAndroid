package com.semye.android.module.network

import com.semye.android.ui.item28_okhttp.CustomCookieJar
import com.semye.android.ui.item28_okhttp.HeaderInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Authenticator
import okhttp3.Cache
import okhttp3.Dns
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object OkHttpModule {

    @Provides
    fun provideOkhttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val builder = OkHttpClient.Builder()
            .cookieJar(CustomCookieJar()) // 配置cookie
            .authenticator(Authenticator.NONE)//认证器
            .proxyAuthenticator(Authenticator.NONE)//代理认证器
            .dns(Dns.SYSTEM)
            .callTimeout(0, TimeUnit.MILLISECONDS)
            .cache(Cache(File("yeshengcache"), 10))//设置缓存
            .addNetworkInterceptor(httpLoggingInterceptor)// 添加拦截器
            .addInterceptor(HeaderInterceptor())
        return builder.build()
    }
}