package com.semye.android.utils

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle

/**
 * Created by yesheng on 2019-12-19
 */
object ApplicationHelper {
    /**
     * 获取当前应用的包信息
     *
     * @param context context
     * @return PackageInfo
     */
    private fun getPackageInfo(context: Context): PackageInfo? {
        try {
            val packageManager = context.packageManager
            return packageManager.getPackageInfo(context.packageName, 0)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return null
    }

    /**
     * 获取versionCode
     *
     * @param context context
     * @return versionCode
     */
    fun getVersionCode(context: Context): Int {
        val packageInfo = getPackageInfo(context)
        return packageInfo?.versionCode ?: 0
    }

    /**
     * 获取versionName
     *
     * @param context context
     * @return versionName
     */
    fun getVersionName(context: Context): String? {
        val packageInfo = getPackageInfo(context)
        return packageInfo?.versionName
    }

    fun getPackage(context: Context?): String {
        return if (context == null) {
            ""
        } else context.packageName
    }

    fun getMetaValue(context: Context?, metaKey: String?): String? {
        var apiKey: String? = null
        if (context == null || metaKey == null) {
            return null
        }
        try {
            var metaData: Bundle? = null
            val ai = context.packageManager.getApplicationInfo(context.packageName, PackageManager.GET_META_DATA)
            if (null != ai) {
                metaData = ai.metaData
            }
            if (null != metaData) {
                apiKey = metaData.getString(metaKey)
            }
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return apiKey
    }
}