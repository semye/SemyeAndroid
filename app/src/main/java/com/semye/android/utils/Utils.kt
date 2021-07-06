package com.semye.android.utils

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.telephony.TelephonyManager
import androidx.core.content.ContextCompat

/**
 * Created by yesheng on 2018/12/20.
 */
object Utils {
    @SuppressLint("HardwareIds")
    fun getDeviceInfo(context: Context) {
        try {
            val telephonyManager =
                context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ContextCompat.checkSelfPermission(
                        context,
                        Manifest.permission.READ_PHONE_STATE
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    if (telephonyManager != null) {
                        println(telephonyManager.deviceId)
                    }
                }
            } else {
                if (telephonyManager != null) {
                    println(telephonyManager.deviceId)
                }
            }
            if (telephonyManager != null) {
                println(telephonyManager.networkType)
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                if (telephonyManager != null) {
                    println(telephonyManager.dataNetworkType)
                }
            }
            if (telephonyManager != null) {
                if (telephonyManager.phoneType == TelephonyManager.PHONE_TYPE_CDMA) {
                    println("CDMA")
                } else if (telephonyManager.phoneType == TelephonyManager.PHONE_TYPE_GSM) {
                    println("GSM")
                }
            }

            //处理器
            println(Build.BOARD)
            //品牌
            println(Build.MANUFACTURER)
            //型号
            println(Build.MODEL)
            println(Build.TIME)
            println(Build.PRODUCT)
            println(Build.DEVICE)
            println(Build.DISPLAY)
            println(Build.BRAND)
            println(Build.USER)
            println(Build.FINGERPRINT)
            println(Build.HARDWARE)
            println(Build.HOST)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                println(Build.VERSION.BASE_OS)
            }
            println(Build.VERSION.RELEASE)
            println(Build.VERSION.CODENAME)
            println(Build.VERSION.INCREMENTAL)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                println(Build.VERSION.SECURITY_PATCH)
            }
        } catch (e: Exception) {
            //ignore
        }
    }
}