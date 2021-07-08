package com.semye.android.utils

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.telephony.TelephonyManager
import androidx.core.content.ContextCompat
import java.lang.reflect.InvocationTargetException

/**
 * Created by yesheng on 2019-12-19
 */
object DeviceHelper {
    /**
     * 获取设备唯一ID
     */
    @SuppressLint("HardwareIds")
    fun getDeviceId(context: Context?): String {
        if (context == null) {
            return ""
        }
        val tm = context.getSystemService(Activity.TELEPHONY_SERVICE) as TelephonyManager
        return if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
            tm.deviceId
        } else ""
    }

    fun isCDMAPhone(context: Context?): Boolean {
        if (context == null) {
            return false
        }
        val tm = context.getSystemService(Activity.TELEPHONY_SERVICE) as TelephonyManager
        return if (tm != null) {
            tm.phoneType == TelephonyManager.PHONE_TYPE_CDMA
        } else false
    }

    fun getIMEI(context: Context?): String {
        if (context == null) {
            return ""
        }
        val tm = context.getSystemService(Activity.TELEPHONY_SERVICE) as TelephonyManager
        return if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                tm.imei
            } else {
                //反射获取imei
                try {
                    val clazz: Class<*> = tm.javaClass
                    val getImei = clazz.getDeclaredMethod("getImei", Int::class.javaPrimitiveType)
                    getImei.isAccessible = true
                    return getImei.invoke(tm) as String
                } catch (e: NoSuchMethodException) {
                    e.printStackTrace()
                } catch (e: IllegalAccessException) {
                    e.printStackTrace()
                } catch (e: InvocationTargetException) {
                    e.printStackTrace()
                }
                ""
            }
        } else ""
    }
}