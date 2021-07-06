package com.semye.android.network

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import android.os.Build
import android.util.Log
import com.semye.android.utils.NetworkUtils

/**
 * Created by yesheng on 2018/11/11.
 */
class NetworkConnectReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val action = intent.action
        Log.d(TAG, "action: $action")
        if (action != null) {
            when (action) {
                WifiManager.NETWORK_STATE_CHANGED_ACTION -> {
                    val networkInfo =
                        intent.getParcelableExtra<NetworkInfo>(WifiManager.EXTRA_NETWORK_INFO)
                    if (networkInfo != null) {
                        Log.d(TAG, "networkInfo: $networkInfo")
                    }
                    val bssid = intent.getStringExtra(WifiManager.EXTRA_BSSID)
                    Log.d(TAG, "bssid: $bssid")
                    val wifiInfo = intent.getParcelableExtra<WifiInfo>(WifiManager.EXTRA_WIFI_INFO)
                    if (wifiInfo != null) {
                        Log.d(TAG, "wifiInfo: $wifiInfo")
                    }
                }
                WifiManager.WIFI_STATE_CHANGED_ACTION -> {
                    val wifi_state = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, 0)
                    when (wifi_state) {
                        WifiManager.WIFI_STATE_DISABLING -> Log.d(TAG, "wifi disabling ")
                        WifiManager.WIFI_STATE_DISABLED -> Log.d(TAG, "wifi disabled ")
                        WifiManager.WIFI_STATE_ENABLING -> Log.d(TAG, "wifi enabling ")
                        WifiManager.WIFI_STATE_ENABLED -> Log.d(TAG, "wifi enabled ")
                        WifiManager.WIFI_STATE_UNKNOWN -> Log.d(TAG, "wifi unknown ")
                    }
                }
                ConnectivityManager.CONNECTIVITY_ACTION -> {
                    val failover =
                        intent.getBooleanExtra(ConnectivityManager.EXTRA_IS_FAILOVER, false)
                    Log.d(TAG, "failover $failover")
                    val networkInfo1 =
                        intent.getParcelableExtra<NetworkInfo>(ConnectivityManager.EXTRA_NETWORK_INFO)
                    Log.d(TAG, "networkInfo1: " + (networkInfo1?.toString() ?: "null"))
                    val type = intent.getIntExtra(ConnectivityManager.EXTRA_NETWORK_TYPE, -1)
                    Log.d(TAG, "type: $type")
                    val no_connectivity =
                        intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false)
                    Log.d(TAG, "no_connectivity: $no_connectivity")
                    val reason = intent.getStringExtra(ConnectivityManager.EXTRA_REASON)
                    Log.d(TAG, "reason: $reason")
                    val extra_info = intent.getStringExtra(ConnectivityManager.EXTRA_EXTRA_INFO)
                    Log.d(TAG, "extra_info: $extra_info")
                    Log.d(TAG, "是否是wifi: " + NetworkUtils.isWifi(context))
                    Log.d(TAG, "是否是mobile: " + NetworkUtils.isMobile(context))
                }
                WifiManager.SCAN_RESULTS_AVAILABLE_ACTION -> if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    val scansuccess =
                        intent.getBooleanExtra(WifiManager.EXTRA_RESULTS_UPDATED, false)
                    Log.d(TAG, "scan wifi success: $scansuccess")
                }
            }
        }
    }

    companion object {
        const val TAG = "NetworkConnectReceiver"
    }
}