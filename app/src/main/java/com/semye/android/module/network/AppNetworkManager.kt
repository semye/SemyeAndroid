package com.semye.android.module.network

import android.content.Context
import android.net.*
import android.os.Build
import android.util.Log
import com.semye.android.SemyeApplication

object AppNetworkManager {

    private var connectivityManager: ConnectivityManager? = null

    private const val TAG = "AppNetworkManager"

    interface NetworkListener {

    }

    private val mListener = arrayListOf<NetworkListener>()

    fun addListener(listener: NetworkListener) {
        if (!mListener.contains(listener)) {
            mListener.add(listener)
        }
    }

    fun init(context: Context) {
        connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        Log.d(TAG, connectivityManager.toString())
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            connectivityManager?.defaultProxy?.let {

                println("代理host:" + it.host)
                println("代理port:" + it.port)
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            connectivityManager?.addDefaultNetworkActiveListener {
                println("network active")
            }
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
                                Log.d(SemyeApplication.TAG, "wifi网络已连接");
                            } else {
                                Log.d(SemyeApplication.TAG, "移动网络已连接");
                            }
                        }
                    }

                    override fun onLost(network: Network) {
                        super.onLost(network)
                        Log.e(SemyeApplication.TAG, "网络断开了");
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
                        Log.e(SemyeApplication.TAG, "网络连接了");
                    }
                })
        }
    }


}