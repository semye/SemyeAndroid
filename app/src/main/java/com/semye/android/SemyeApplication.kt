package com.semye.android

import android.net.ConnectivityManager
import android.text.TextUtils
import android.util.Log
import android.webkit.CookieManager
import androidx.multidex.MultiDexApplication

/**
 * Created by yesheng on 2020/5/21
 */
class SemyeApplication : MultiDexApplication() {

    private var connectivityManager: ConnectivityManager? = null

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "application create")
        AppNetworkManager.inits(this)
    }

    private val cookies: HashMap<String, String>
        private get() {
            val cookie = CookieManager.getInstance().getCookie("wdzj.com")
            val hashMap = HashMap<String, String>()
            if (!TextUtils.isEmpty(cookie)) {
                val cookieArray = TextUtils.split(cookie, "; ")
                for (a in cookieArray) {
                    val keyValue = TextUtils.split(a, "=")
                    if (keyValue.size == 2) {
                        hashMap[keyValue[0]] = keyValue[1]
                    }
                }
            }
            return hashMap
        }

    private fun cookie() {
        Log.d(TAG, "before cookie")
        //获取CookieManager的单例
        val cookieManager = CookieManager.getInstance()
        //设置这个application的webview是否发送和接收cookie
        cookieManager.setAcceptCookie(true)
        Log.d(TAG, "after cookie")

        /*先去判断cookies数据库是否存在 不存在就去创建该数据库*/
        val value = "semye=19910806;Domain=.wdzj.com;Path=/;Max-Age=94608000"
        cookieManager.setCookie("wdzj.com", value)
        val cookie = CookieManager.getInstance().getCookie("www.wdzj.com")
        Log.d(TAG, "cookie:$cookie")
    }

    companion object {
        const val TAG = "SemyeApplication"
    }
}