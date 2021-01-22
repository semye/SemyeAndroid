package com.semye.android.network.wifi

import android.Manifest
import android.content.pm.PackageManager
import android.net.wifi.WifiManager
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.semye.android.R

class WifiMainActivity : AppCompatActivity() {
    private var wifiManager: WifiManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_wifi)
        wifiManager = applicationContext.getSystemService(WIFI_SERVICE) as WifiManager
        show()
    }

    private fun show() {
        //获取当前的wifi网络配置
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
            || ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_WIFI_STATE) == PackageManager.PERMISSION_GRANTED) {
            for (configuration in wifiManager!!.configuredNetworks) {
                println(configuration.toString())
            }
        }
        val connectionInfo = wifiManager!!.connectionInfo
        println(connectionInfo.toString())
        val dhcpInfo = wifiManager!!.dhcpInfo
        println(dhcpInfo.toString())
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            println("isScanAlwaysAvailable" + wifiManager!!.isScanAlwaysAvailable)
        }
        println("isWifiEnabled" + wifiManager!!.isWifiEnabled)
        println("wifiState" + wifiManager!!.wifiState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            println("5G" + wifiManager!!.is5GHzBandSupported)
            println("isDeviceToApRttSupported" + wifiManager!!.isDeviceToApRttSupported)
            println("isEnhancedPowerReportingSupported" + wifiManager!!.isEnhancedPowerReportingSupported)
            println("isP2pSupported" + wifiManager!!.isP2pSupported)
            println("isPreferredNetworkOffloadSupported" + wifiManager!!.isPreferredNetworkOffloadSupported)
            println("isTdlsSupported" + wifiManager!!.isTdlsSupported)
        }
        val scanResults = wifiManager!!.scanResults
        for (result in scanResults) {
            println(result.toString())
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            try {
                val passpointConfigurations = wifiManager!!.passpointConfigurations
                for (passpointConfiguration in passpointConfigurations) {
                    println(passpointConfiguration.toString())
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}