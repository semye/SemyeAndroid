package com.semye.android.ui.item28_okhttp

import okhttp3.Authenticator
import okhttp3.Cache
import okhttp3.Dns
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okhttp3.logging.HttpLoggingInterceptor
import okio.ByteString
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * Created by yesheng on 2019-04-29
 */
object OkHttpUtils {
    /**
     * 创建默认的OkHttpClient
     *
     * @return OkHttpClient
     */
    val defaultOkHttpClient: OkHttpClient by lazy {
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
        builder.build()
    }

    @JvmStatic
    fun webSocket(url: String?) {
        val okHttpClient = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .get()
            .build()
        val webSocket = okHttpClient.newWebSocket(request, object : WebSocketListener() {
            override fun onOpen(
                webSocket: WebSocket,
                response: Response
            ) {
                super.onOpen(webSocket, response)
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                super.onMessage(webSocket, text)
            }

            override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
                super.onMessage(webSocket, bytes)
            }

            override fun onClosing(
                webSocket: WebSocket,
                code: Int,
                reason: String
            ) {
                super.onClosing(webSocket, code, reason)
            }

            override fun onClosed(
                webSocket: WebSocket,
                code: Int,
                reason: String
            ) {
                super.onClosed(webSocket, code, reason)
            }

            override fun onFailure(
                webSocket: WebSocket,
                t: Throwable,
                response: Response?
            ) {
                super.onFailure(webSocket, t, response)
            }
        })
        webSocket.send("hello")
    }
}