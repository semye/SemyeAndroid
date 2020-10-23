package com.semye.android.utils;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


import java.lang.reflect.Field;
import java.lang.reflect.Method;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created by yesheng on 2018/6/27.
 */
public class StatusBarUtils {


    public static void adaptStatusBar() {

    }


    /**
     * 小米手机设置字体颜色 只适用于android6.0以下
     *
     * @param darkMode 深色模式
     */
    public static void miuiSetStatusBarDarkMode(@NonNull Window window, boolean darkMode) {
        try {
            Class<? extends Window> clazz = window.getClass();
            int darkModeFlag = 0;
            @SuppressLint("PrivateApi")
            Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
            extraFlagField.invoke(window, darkMode ? darkModeFlag : 0, darkModeFlag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static final int SYSTEM_UI_FLAG_OP_STATUS_BAR_TINT = 0x00000010;


    /**
     * 设置透明系统状态栏
     * 会设置成全屏模式 使布局延伸到系统状态栏中 不能设置 fitsSystemWindows
     */
    public static void setTranslucentStutusBar(@NonNull Window window) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }


    /**
     * 设置系统状态栏颜色
     *
     * @param window         window
     * @param statusBarColor statusBarColor
     * @param darkMode       darkMode
     */
    public static void adaptStatusBar(@NonNull Window window, int statusBarColor, boolean darkMode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //移除透明状态栏
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //在window上绘制一个与系统状态栏区域一样大的背景 颜色填充由 setStatusBarColor 决定
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //如果背景透明 退出
            if (statusBarColor == Color.TRANSPARENT)
                return;
            //若要使setStatusBarColor生效 必须移除透明状态栏 即退出全屏
            //并设置WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS
            window.setStatusBarColor(statusBarColor);
        } else {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //设置透明状态栏 会有一个保护罩 并且会设置 setSystemUiVisibility
            // SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN 和 SYSTEM_UI_FLAG_LAYOUT_STABLE
            //window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            //这个flag是用于让window来负责绘制系统条背景默认会绘制成透明 然后会在window对应的区域绘制指定的颜色
            //由setStatusBarColor 和setNavigationBarColor 来指定颜色
            //WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS

            //6.0以上设置亮色状态栏模式 必须 设置自己绘制状态栏

            //设置状态栏为系统默认的透明色
//            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            int temp = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            window.getDecorView().setSystemUiVisibility(temp);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(statusBarColor);
//
//            if (darkMode) {
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                    window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//                }
//            }

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);


//            if (PhoneUtils.isMIUI())
//                setStatusBarDarkMode(window, b);
//            }
        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //6.0以上设置亮色状态栏模式 必须 设置自己绘制状态栏
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            int temp;
            if (darkMode) {
                temp = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            } else {
                temp = View.VISIBLE;
            }
            window.getDecorView().setSystemUiVisibility(temp);
            window.setStatusBarColor(statusBarColor);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //设置状态栏为系统默认的透明色
            if (PhoneUtils.isMIUI()) {
                miuiSetStatusBarDarkMode(window, darkMode);
            } else if (PhoneUtils.isFlyme()) {
                flymeSetStatusBarLightMode(window, darkMode);
            } else if (PhoneUtils.isColorOS()) {
                oppoStatusBarDarkMode(window, statusBarColor, darkMode);
            } else {//其他类型的手机 vivo 原生android
                window.getDecorView().setSystemUiVisibility(View.VISIBLE);
                window.getDecorView().setFitsSystemWindows(true);
                int temp;
                if (darkMode) {
                    temp = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                } else {
                    temp = View.SYSTEM_UI_FLAG_VISIBLE;
                }
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.getDecorView().setSystemUiVisibility(temp);
                window.setStatusBarColor(statusBarColor);
            }
        } else {
            //4.4需要设置布局根布局背景颜色和导航一致

            if (PhoneUtils.isMIUI()) {
                if (darkMode) {
                    window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                } else {
                    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                    window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
                }
                miuiSetStatusBarDarkMode(window, darkMode);
            } else {
                setTranslucentStatus(window, darkMode);
            }

        }
    }


    /**
     * 设置状态栏字体颜色
     *
     * @param b
     */
    public void setStatusBarFontColor(@NonNull Window window, boolean b) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int temp = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            if (b) {
                temp = temp | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            }
            window.getDecorView().setSystemUiVisibility(temp);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(window, true);
            if (PhoneUtils.isMIUI()) {
                miuiSetStatusBarDarkMode(window, b);
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void oppoStatusBarDarkMode(@NonNull Window window, int statusBarColor, boolean darkMode) {
        int temp;
        if (darkMode) {
            temp = SYSTEM_UI_FLAG_OP_STATUS_BAR_TINT;
        } else {
            temp = View.SYSTEM_UI_FLAG_VISIBLE;
        }
        window.getDecorView().setSystemUiVisibility(temp);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(statusBarColor);
    }

    /**
     * 设置魅族手机状态栏图标颜色风格
     * 可以用来判断是否为Flyme用户
     *
     * @param window 需要设置的窗口
     * @param dark   是否把状态栏字体及图标颜色设置为深色
     * @return boolean 成功执行返回true
     */
    public static void flymeSetStatusBarLightMode(@Nullable Window window, boolean dark) {
        if (window != null) {
            try {
                WindowManager.LayoutParams lp = window.getAttributes();
                Field darkFlag = WindowManager.LayoutParams.class
                        .getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field meizuFlags = WindowManager.LayoutParams.class
                        .getDeclaredField("meizuFlags");
                darkFlag.setAccessible(true);
                meizuFlags.setAccessible(true);
                int bit = darkFlag.getInt(null);
                int value = meizuFlags.getInt(lp);
                if (dark) {
                    value |= bit;
                } else {
                    value &= ~bit;
                }
                meizuFlags.setInt(lp, value);
                window.setAttributes(lp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public static void setTranslucentStatus(Window window, boolean darkMode) {
        WindowManager.LayoutParams winParams = window.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (darkMode) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        window.setAttributes(winParams);
    }

    public static void kitkatCompat(@NonNull Window window) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            View view = window.getDecorView();

        }


    }
}
