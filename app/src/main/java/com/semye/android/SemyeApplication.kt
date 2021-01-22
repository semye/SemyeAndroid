package com.semye.android

import android.content.Context
import android.net.ConnectivityManager
import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
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
        connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        println(connectivityManager.toString())
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            connectivityManager?.defaultProxy?.let {
                println("代理host:" + it.host)
                println("代理port:" + it.port)
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            connectivityManager?.addDefaultNetworkActiveListener { }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            connectivityManager?.registerNetworkCallback(
                NetworkRequest.Builder().build(),
                object : ConnectivityManager.NetworkCallback() {
                    override fun onBlockedStatusChanged(network: Network, blocked: Boolean) {
                        super.onBlockedStatusChanged(network, blocked)
                    }

                    override fun onCapabilitiesChanged(
                        network: Network,
                        networkCapabilities: NetworkCapabilities
                    ) {
                        super.onCapabilitiesChanged(network, networkCapabilities)
                        if (networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)) {
                            if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                                Log.e(TAG, "wifi网络已连接");
                            } else {
                                Log.e(TAG, "移动网络已连接");
                            }
                        }
                    }

                    override fun onLost(network: Network) {
                        super.onLost(network)
                        Log.e(TAG, "网络断开了");
                    }

                    override fun onLinkPropertiesChanged(
                        network: Network,
                        linkProperties: LinkProperties
                    ) {
                        super.onLinkPropertiesChanged(network, linkProperties)
                    }

                    override fun onUnavailable() {
                        super.onUnavailable()
                    }

                    override fun onLosing(network: Network, maxMsToLive: Int) {
                        super.onLosing(network, maxMsToLive)
                    }

                    override fun onAvailable(network: Network) {
                        super.onAvailable(network)
                        Log.e(TAG, "网络连接了");
                    }
                })
        }
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