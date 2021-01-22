package com.semye.android.thirdparty.retrofit

import com.semye.android.thirdparty.retrofit.calladapter.SemyeCallAdapterFactory
import okhttp3.CookieJar
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by yesheng on 2018/9/3.
 */
object RetrofitHelper {

    fun <T> create(service: Class<T>, cookieJar: CookieJar): T {
        val annotations = service.annotations
        var baseUrl = ""
        for (annotation in annotations) {

        }
        val okHttpClient = OkHttpClient.Builder()
            .cookieJar(cookieJar)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(service)
    }

    fun initRetrofit(url: String): Retrofit {
        val builder = OkHttpClient.Builder()
        builder.build()
        return Retrofit.Builder()
            .baseUrl(url)
            .addCallAdapterFactory(SemyeCallAdapterFactory.create())
            .client(builder.build())
            .build()
    }

    val defaultOkHttpClient: OkHttpClient
        get() {
            val builder = OkHttpClient.Builder()
            builder.connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
            return builder.build()
        }
}