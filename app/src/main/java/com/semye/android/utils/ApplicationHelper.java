package com.semye.android.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created by yesheng on 2019-12-19
 */
public class ApplicationHelper {

    /**
     * 获取当前应用的包信息
     *
     * @param context context
     * @return PackageInfo
     */
    private static PackageInfo getPackageInfo(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            return packageManager.getPackageInfo(context.getPackageName(), 0);

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取versionCode
     *
     * @param context context
     * @return versionCode
     */
    public static int getVersionCode(@NonNull Context context) {
        PackageInfo packageInfo = getPackageInfo(context);
        if (packageInfo != null) {
            return packageInfo.versionCode;
        }
        return 0;
    }

    /**
     * 获取versionName
     *
     * @param context context
     * @return versionName
     */
    public static String getVersionName(@NonNull Context context) {
        PackageInfo packageInfo = getPackageInfo(context);
        if (packageInfo != null) {
            return packageInfo.versionName;
        }
        return null;
    }



    public static String getPackage(@Nullable Context context) {
        if (context == null) {
            return "";
        }
        return context.getPackageName();
    }

    @Nullable
    public static String getMetaValue(@Nullable Context context, @Nullable String metaKey) {
        String apiKey = null;
        if (context == null || metaKey == null) {
            return null;
        }
        try {
            Bundle metaData = null;
            ApplicationInfo ai = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            if (null != ai) {
                metaData = ai.metaData;
            }
            if (null != metaData) {
                apiKey = metaData.getString(metaKey);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return apiKey;
    }

}
