package com.semye.android.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import android.telephony.TelephonyManager;

/**
 * Created by yesheng on 2018/12/20.
 */
public class Utils {


    @SuppressLint("HardwareIds")
    public static void getDeviceInfo(@NonNull Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
                    if (telephonyManager != null) {
                        System.out.println(telephonyManager.getDeviceId());
                    }
                }
            } else {
                if (telephonyManager != null) {
                    System.out.println(telephonyManager.getDeviceId());
                }
            }

            if (telephonyManager != null) {
                System.out.println(telephonyManager.getNetworkType());
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                if (telephonyManager != null) {
                    System.out.println(telephonyManager.getDataNetworkType());
                }
            }

            if (telephonyManager != null) {
                if (telephonyManager.getPhoneType() == TelephonyManager.PHONE_TYPE_CDMA) {
                    System.out.println("CDMA");
                } else if (telephonyManager.getPhoneType() == TelephonyManager.PHONE_TYPE_GSM) {
                    System.out.println("GSM");
                }
            }

            //处理器
            System.out.println(Build.BOARD);
            //品牌
            System.out.println(Build.MANUFACTURER);
            //型号
            System.out.println(Build.MODEL);
            System.out.println(Build.TIME);
            System.out.println(Build.PRODUCT);
            System.out.println(Build.DEVICE);
            System.out.println(Build.DISPLAY);
            System.out.println(Build.BRAND);
            System.out.println(Build.USER);
            System.out.println(Build.FINGERPRINT);
            System.out.println(Build.HARDWARE);
            System.out.println(Build.HOST);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                System.out.println(Build.VERSION.BASE_OS);
            }
            System.out.println(Build.VERSION.RELEASE);
            System.out.println(Build.VERSION.CODENAME);
            System.out.println(Build.VERSION.INCREMENTAL);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                System.out.println(Build.VERSION.SECURITY_PATCH);
            }


        } catch (Exception e) {
            //ignore
        }
    }
}
