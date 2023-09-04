package com.semye.android.ui.item27_connectivitymanager

import android.annotation.TargetApi
import android.content.Context
import android.net.ConnectivityManager
import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.net.NetworkRequest
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.semye.android.R

class ConnectMainActivity : AppCompatActivity() {

    private var connectivityManager: ConnectivityManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_connect)
        connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        println(connectivityManager.toString())
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
                                Log.e(TAG, "wifi网络已连接")
                            } else {
                                Log.e(TAG, "移动网络已连接")
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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager?.activeNetwork
            connectivityManager?.activeNetworkInfo
        }

//        showAllNetworks()
    }

    /**
     * 显示所有网络
     * 5.0之上使用getAllNetwork
     * 5.0以下使用getAllNetworkInfo(已被废弃，无法识别两种相同的网络)
     */
    private fun showAllNetworks() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val networks = connectivityManager?.allNetworks
            Log.d(TAG, "Network size: " + networks?.size)

            networks?.forEach {
                printNetwork(it)
            }
        } else {
            val networkInfos = connectivityManager!!.allNetworkInfo
            for (networkInfo in networkInfos) {
                Log.d(TAG, "NetworkInfo: $networkInfo")
            }
        }
    }

    /**
     * 打印代理信息
     */
    private fun showProxy() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val proxyInfo = connectivityManager!!.defaultProxy
            Log.d(TAG, "Proxy: " + (proxyInfo?.toString() ?: "null"))
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun printNetwork(network: Network) {
        val networkInfo = connectivityManager!!.getNetworkInfo(network)
        if (networkInfo != null) {
            Log.d(TAG, "NetworkInfo: $networkInfo")
        } else {
            Log.d(TAG, "NetworkInfo: null")
        }
        val linkProperties = connectivityManager!!.getLinkProperties(network)
        if (linkProperties != null) {
            Log.d(TAG, "LinkProperties: $linkProperties")
        } else {
            Log.d(TAG, "LinkProperties: null")
        }
        val networkCapabilities = connectivityManager!!.getNetworkCapabilities(network)
        if (networkCapabilities != null) {
            Log.d(TAG, "Capabilities: $networkCapabilities")
        } else {
            Log.d(TAG, "Capabilities: null")
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val preference = connectivityManager!!.getMultipathPreference(network)
            Log.d(TAG, "MultipathPreference: $preference")
        }
    }

    /**
     * 获取当前活动的网络
     */
    private val activeNetworkInfo: NetworkInfo?
        private get() {
            val networkInfo: NetworkInfo?
            networkInfo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val network = connectivityManager!!.activeNetwork
                connectivityManager!!.getNetworkInfo(network)
            } else {
                connectivityManager!!.activeNetworkInfo
            }
            if (networkInfo != null) {
                Log.d(TAG, "Active NetworkInfo: $networkInfo")
            }
            return networkInfo
        }

    companion object {
        private const val TAG = "ConnectivityActivity"
    }
}
