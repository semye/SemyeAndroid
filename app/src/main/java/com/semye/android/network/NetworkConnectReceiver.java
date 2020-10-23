package com.semye.android.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

import androidx.annotation.NonNull;

import com.semye.android.utils.NetworkUtils;

/**
 * Created by yesheng on 2018/11/11.
 */
public class NetworkConnectReceiver extends BroadcastReceiver {

    public static final String TAG = "NetworkConnectReceiver";

    @Override
    public void onReceive(@NonNull Context context, @NonNull Intent intent) {
        final String action = intent.getAction();
        Log.d(TAG, "action: " + action);
        if (action != null) {
            switch (action) {
                case WifiManager.NETWORK_STATE_CHANGED_ACTION:
                    final NetworkInfo networkInfo = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
                    if (networkInfo != null) {
                        Log.d(TAG, "networkInfo: " + networkInfo.toString());
                    }

                    final String bssid = intent.getStringExtra(WifiManager.EXTRA_BSSID);
                    Log.d(TAG, "bssid: " + bssid);

                    final WifiInfo wifiInfo = intent.getParcelableExtra(WifiManager.EXTRA_WIFI_INFO);
                    if (wifiInfo != null) {
                        Log.d(TAG, "wifiInfo: " + wifiInfo.toString());
                    }
                    break;
                case WifiManager.WIFI_STATE_CHANGED_ACTION://wifi的状态改变监听
                    final int wifi_state = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, 0);
                    switch (wifi_state) {
                        case WifiManager.WIFI_STATE_DISABLING:
                            Log.d(TAG, "wifi disabling ");
                            break;
                        case WifiManager.WIFI_STATE_DISABLED:
                            Log.d(TAG, "wifi disabled ");
                            break;
                        case WifiManager.WIFI_STATE_ENABLING:
                            Log.d(TAG, "wifi enabling ");
                            break;
                        case WifiManager.WIFI_STATE_ENABLED:
                            Log.d(TAG, "wifi enabled ");
                            break;
                        case WifiManager.WIFI_STATE_UNKNOWN:
                            Log.d(TAG, "wifi unknown ");
                            break;
                    }
                    break;
                case ConnectivityManager.CONNECTIVITY_ACTION://网络连接建立或者断开会调用
                    boolean failover = intent.getBooleanExtra(ConnectivityManager.EXTRA_IS_FAILOVER, false);
                    Log.d(TAG, "failover " + failover);
                    NetworkInfo networkInfo1 = intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
                    Log.d(TAG, "networkInfo1: " + (networkInfo1 == null ? "null" : networkInfo1.toString()));
                    int type = intent.getIntExtra(ConnectivityManager.EXTRA_NETWORK_TYPE, -1);
                    Log.d(TAG, "type: " + type);
                    boolean no_connectivity = intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false);
                    Log.d(TAG, "no_connectivity: " + no_connectivity);
                    String reason = intent.getStringExtra(ConnectivityManager.EXTRA_REASON);
                    Log.d(TAG, "reason: " + reason);
                    String extra_info = intent.getStringExtra(ConnectivityManager.EXTRA_EXTRA_INFO);
                    Log.d(TAG, "extra_info: " + extra_info);
                    Log.d(TAG, "是否是wifi: " + NetworkUtils.isWifi(context));
                    Log.d(TAG, "是否是mobile: " + NetworkUtils.isMobile(context));
                    break;
                case WifiManager.SCAN_RESULTS_AVAILABLE_ACTION:
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                        boolean scansuccess = intent.getBooleanExtra(WifiManager.EXTRA_RESULTS_UPDATED, false);
                        Log.d(TAG, "scan wifi success: " + scansuccess);
                    }
                    break;
            }
        }
    }
}
