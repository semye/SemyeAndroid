package com.semye.android.utils

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo

/**
 * Created by yesheng on 15/6/9.
 * 网络连接工具类
 */
object NetworkUtils {
    /**
     * 判断网络是否可用
     *
     * @param context context
     * @return true 网络可用  false 网络不可用
     */
    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val networkInfo = connectivityManager.activeNetworkInfo
            if (networkInfo != null) {
                return networkInfo.isConnected && networkInfo.isAvailable
            }
        }
        return false
    }

    /**
     * 是否是指定网络
     *
     * @param context context
     * @param name    网络名
     * @return
     */
    private fun isSpecialNetwork(context: Context, name: String): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val networkInfo = connectivityManager.activeNetworkInfo
            if (networkInfo != null) {
                return networkInfo.typeName == name
            }
        }
        return false
    }

    fun getNetworkInfo(context: Context): NetworkInfo? {
        val connectManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return if (connectManager != null) {
            connectManager.activeNetworkInfo
        } else null
    }

    fun isNetworkConnected(context: Context): Boolean {
        val networkInfo = getNetworkInfo(context)
        return networkInfo != null && networkInfo.isConnected
    }

    /**
     * make true current connect service is wifi
     *
     * @param context
     * @return
     */
    fun isWifi(context: Context): Boolean {
        val networkInfo = getNetworkInfo(context)
        return networkInfo != null && networkInfo.type == ConnectivityManager.TYPE_WIFI
    }

    /**
     * make true current connect service is wifi
     *
     * @param context
     * @return
     */
    fun isMobile(context: Context): Boolean {
        val networkInfo = getNetworkInfo(context)
        return networkInfo != null && networkInfo.type == ConnectivityManager.TYPE_MOBILE
    }

    /**
     * @param context
     * @return 有网络 返回true
     */
    fun checkNetWorkStatus(context: Context): Boolean {
        var netStatus = false
        var isConnected = false
        val networkInfo = getNetworkInfo(context)
        if (networkInfo != null) {
            netStatus = networkInfo.isAvailable
            isConnected = networkInfo.isConnected
        }
        return netStatus
    }

    fun setNetwork(context: Context) {
        val alert = AlertDialog.Builder(context).setTitle("网络暂时不可用")
            .setMessage("是否对网络进行设置？")
        alert.setPositiveButton("是") { dialog, whichButton ->
            val mIntent = Intent("android.settings.WIRELESS_SETTINGS") //进入无线网络配置界面
            //				Intent mIntent = new Intent("android.settings.ACTION_WIFI_SETTINGS"); //进入手机中的wifi网络设置界面
            (context as Activity).startActivityForResult(
                mIntent,
                0
            ) // 如果在设置完成后需要再次进行操作，可以重写操作代码，在这里不再重写
            dialog.cancel()
        }
            .setNeutralButton("否") { dialog, whichButton -> dialog.cancel() }.show()
    }

    fun setNetworkAlon(vp: Activity) {
        val builder = AlertDialog.Builder(vp).setTitle("网络暂时不可用")
            .setMessage("是否对网络进行设置？")
        builder.setPositiveButton("是") { dialog, whichButton ->
            val mIntent = Intent("android.settings.WIRELESS_SETTINGS") //进入无线网络配置界面
            //				Intent mIntent = new Intent("android.settings.ACTION_WIFI_SETTINGS"); //进入手机中的wifi网络设置界面
            vp.startActivityForResult(mIntent, 0) // 如果在设置完成后需要再次进行操作，可以重写操作代码，在这里不再重写
            dialog.cancel()
        }
            .setNeutralButton("否") { dialog, whichButton -> dialog.cancel() }
        val alert = builder.create()
        if (!alert.isShowing) {
            alert.show()
        }
    }
}