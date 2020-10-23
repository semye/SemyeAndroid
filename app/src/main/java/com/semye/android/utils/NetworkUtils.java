package com.semye.android.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.annotation.NonNull;


/**
 * Created by yesheng on 15/6/9.
 * 网络连接工具类
 */
public class NetworkUtils {


    /**
     * 判断网络是否可用
     *
     * @param context context
     * @return true 网络可用  false 网络不可用
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null) {
                return networkInfo.isConnected() && networkInfo.isAvailable();
            }
        }
        return false;
    }


    /**
     * 是否是指定网络
     *
     * @param context context
     * @param name    网络名
     * @return
     */
    private static boolean isSpecialNetwork(Context context, String name) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null) {
                return networkInfo.getTypeName().equals(name);
            }
        }
        return false;
    }


    public static NetworkInfo getNetworkInfo(Context context) {
        ConnectivityManager connectManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectManager != null) {
            return connectManager.getActiveNetworkInfo();
        }
        return null;
    }


    public static boolean isNetworkConnected(@NonNull Context context) {
        NetworkInfo networkInfo = getNetworkInfo(context);
        return networkInfo != null && networkInfo.isConnected();
    }


    /**
     * make true current connect service is wifi
     *
     * @param context
     * @return
     */
    public static boolean isWifi(@NonNull Context context) {
        NetworkInfo networkInfo = getNetworkInfo(context);
        return networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_WIFI;
    }

    /**
     * make true current connect service is wifi
     *
     * @param context
     * @return
     */
    public static boolean isMobile(@NonNull Context context) {
        NetworkInfo networkInfo = getNetworkInfo(context);
        return networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_MOBILE;
    }

    /**
     * @param context
     * @return 有网络 返回true
     */
    public static boolean checkNetWorkStatus(@NonNull Context context) {
        boolean netStatus = false;
        boolean isConnected = false;
        NetworkInfo networkInfo = getNetworkInfo(context);
        if (networkInfo != null) {
            netStatus = networkInfo.isAvailable();
            isConnected = networkInfo.isConnected();
        }
        return netStatus;
    }


    public static void setNetwork(@NonNull final Context context) {
        AlertDialog.Builder alert = new AlertDialog.Builder(context).setTitle("网络暂时不可用")
                .setMessage("是否对网络进行设置？");
        alert.setPositiveButton("是", new DialogInterface.OnClickListener() {
            public void onClick(@NonNull DialogInterface dialog, int whichButton) {
                Intent mIntent = new Intent("android.settings.WIRELESS_SETTINGS");//进入无线网络配置界面
//				Intent mIntent = new Intent("android.settings.ACTION_WIFI_SETTINGS"); //进入手机中的wifi网络设置界面
                ((Activity) context).startActivityForResult(mIntent, 0); // 如果在设置完成后需要再次进行操作，可以重写操作代码，在这里不再重写
                dialog.cancel();
            }
        }).setNeutralButton("否", new DialogInterface.OnClickListener() {
            public void onClick(@NonNull DialogInterface dialog, int whichButton) {
                dialog.cancel();
            }
        }).show();
    }


    public static void setNetworkAlon(@NonNull final Activity vp) {
        AlertDialog.Builder builder = new AlertDialog.Builder(vp).setTitle("网络暂时不可用")
                .setMessage("是否对网络进行设置？");
        builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
            public void onClick(@NonNull DialogInterface dialog, int whichButton) {
                Intent mIntent = new Intent("android.settings.WIRELESS_SETTINGS");//进入无线网络配置界面
//				Intent mIntent = new Intent("android.settings.ACTION_WIFI_SETTINGS"); //进入手机中的wifi网络设置界面
                vp.startActivityForResult(mIntent, 0); // 如果在设置完成后需要再次进行操作，可以重写操作代码，在这里不再重写
                dialog.cancel();
            }
        }).setNeutralButton("否", new DialogInterface.OnClickListener() {
            public void onClick(@NonNull DialogInterface dialog, int whichButton) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        if (!alert.isShowing()) {
            alert.show();
        }

    }
}
