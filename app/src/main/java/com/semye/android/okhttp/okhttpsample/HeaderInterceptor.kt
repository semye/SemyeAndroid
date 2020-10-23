package com.semye.android.okhttp.okhttpsample

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * Created by yesheng on 2018/9/15.
 * 自定义http拦截器
 */
class HeaderInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val requestBuilder = originalRequest.newBuilder()
            .header("customhead", "abcdefghijklmn")
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}