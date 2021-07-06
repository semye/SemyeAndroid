package com.semye.android.network

import android.app.Service
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import android.os.IBinder

/**
 * Created by yesheng on 2018/7/12.
 */
class NetworkListenerService : Service() {
    private var networkConnectReceiver: NetworkConnectReceiver? = null
    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        networkConnectReceiver = NetworkConnectReceiver()
        val intentFilter = IntentFilter()
        intentFilter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION)
        intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)
        intentFilter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION)
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(networkConnectReceiver, intentFilter)
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        if (networkConnectReceiver != null) {
            unregisterReceiver(networkConnectReceiver)
        }
        super.onDestroy()
    }

    companion object {
        private const val TAG = "NetworkListenerService"
    }
}