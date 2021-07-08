package com.semye.android.utils

import android.content.Context

/**
 * Created by yesheng on 2016/12/27.
 */
object ScreenUtils {
    /**
     * 获取屏幕的高度
     *
     * @param context context
     * @return 屏幕的高度像素
     */
    fun getScreenHeight(context: Context): Int {
        val metrics = context.resources.displayMetrics
        return metrics.heightPixels
    }

    /**
     * 获取屏幕宽度
     *
     * @param context context
     * @return 屏幕的宽度像素
     */
    fun getScreenWidth(context: Context): Int {
        val metrics = context.resources.displayMetrics
        return metrics.widthPixels
    }

    /**
     * 获得设备屏幕密度
     *
     * @param context context
     * @return 设备屏幕密度
     */
    fun getScreenDensity(context: Context): Float {
        val metrics = context.resources.displayMetrics
        return metrics.density
    }

    /**
     * 获取Dpi
     *
     * @param context context
     * @return Dpi
     */
    fun getScreenDensityDpi(context: Context): Int {
        val metrics = context.resources.displayMetrics
        return metrics.densityDpi
    }

    fun getScreenXDpi(context: Context): Float {
        val metrics = context.resources.displayMetrics
        return metrics.xdpi
    }

    fun getScreenYDpi(context: Context): Float {
        val metrics = context.resources.displayMetrics
        return metrics.ydpi
    }
}