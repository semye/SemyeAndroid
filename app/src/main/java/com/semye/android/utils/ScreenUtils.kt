package com.semye.android.utils;

import android.content.Context;
import android.util.DisplayMetrics;

import androidx.annotation.NonNull;

/**
 * Created by yesheng on 2016/12/27.
 */
public class ScreenUtils {


    /**
     * 获取屏幕的高度
     *
     * @param context context
     * @return 屏幕的高度像素
     */
    public static int getScreenHeight(@NonNull Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return metrics.heightPixels;
    }


    /**
     * 获取屏幕宽度
     *
     * @param context context
     * @return 屏幕的宽度像素
     */
    public static int getScreenWidth(@NonNull Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return metrics.widthPixels;
    }


    /**
     * 获得设备屏幕密度
     *
     * @param context context
     * @return 设备屏幕密度
     */
    public static float getScreenDensity(@NonNull Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return metrics.density;
    }

    /**
     * 获取Dpi
     *
     * @param context context
     * @return Dpi
     */
    public static int getScreenDensityDpi(@NonNull Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return metrics.densityDpi;
    }

    public static float getScreenXDpi(@NonNull Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return metrics.xdpi;
    }

    public static float getScreenYDpi(@NonNull Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return metrics.ydpi;
    }

}
