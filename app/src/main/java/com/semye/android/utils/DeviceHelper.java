package com.semye.android.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.TelephonyManager;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by yesheng on 2019-12-19
 */
public class DeviceHelper {


    /**
     * 获取设备唯一ID
     */
    @SuppressLint("HardwareIds")
    public static String getDeviceId(@Nullable Context context) {
        if (context == null) {
            return "";
        }
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Activity.TELEPHONY_SERVICE);
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
            return tm.getDeviceId();
        }
        return "";
    }

    public static boolean isCDMAPhone(@Nullable Context context) {
        if (context == null) {
            return false;
        }
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Activity.TELEPHONY_SERVICE);
        if (tm != null) {
            return tm.getPhoneType() == TelephonyManager.PHONE_TYPE_CDMA;
        }
        return false;
    }

    public static String getIMEI(@Nullable Context context) {
        if (context == null) {
            return "";
        }
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Activity.TELEPHONY_SERVICE);
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                return tm.getImei();
            } else {
                //反射获取imei
                try {
                    Class clazz = tm.getClass();
                    Method getImei = clazz.getDeclaredMethod("getImei", int.class);
                    getImei.setAccessible(true);
                    return (String) getImei.invoke(tm);
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
                return "";
            }
        }
        return "";
    }
}
