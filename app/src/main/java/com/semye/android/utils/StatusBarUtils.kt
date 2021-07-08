package com.semye.android.utils

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.Window
import android.view.WindowManager

/**
 * Created by yesheng on 2018/6/27.
 */
class StatusBarUtils {
    /**
     * 设置状态栏字体颜色
     *
     * @param b
     */
    fun setStatusBarFontColor(window: Window, b: Boolean) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            var temp = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            if (b) {
                temp = temp or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }
            window.decorView.systemUiVisibility = temp
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(window, true)
            if (PhoneUtils.isMIUI()) {
                miuiSetStatusBarDarkMode(window, b)
            }
        }
    }

    companion object {
        fun adaptStatusBar() {}

        /**
         * 小米手机设置字体颜色 只适用于android6.0以下
         *
         * @param darkMode 深色模式
         */
        fun miuiSetStatusBarDarkMode(window: Window, darkMode: Boolean) {
            try {
                val clazz: Class<out Window> = window.javaClass
                var darkModeFlag = 0
                @SuppressLint("PrivateApi") val layoutParams =
                    Class.forName("android.view.MiuiWindowManager\$LayoutParams")
                val field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE")
                darkModeFlag = field.getInt(layoutParams)
                val extraFlagField = clazz.getMethod(
                    "setExtraFlags",
                    Int::class.javaPrimitiveType,
                    Int::class.javaPrimitiveType
                )
                extraFlagField.invoke(window, if (darkMode) darkModeFlag else 0, darkModeFlag)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        private const val SYSTEM_UI_FLAG_OP_STATUS_BAR_TINT = 0x00000010

        /**
         * 设置透明系统状态栏
         * 会设置成全屏模式 使布局延伸到系统状态栏中 不能设置 fitsSystemWindows
         */
        fun setTranslucentStatusBar(window: Window) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.statusBarColor = Color.TRANSPARENT
            } else {
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            }
        }

        /**
         * 设置系统状态栏颜色
         *
         * @param window         window
         * @param statusBarColor statusBarColor
         * @param darkMode       darkMode
         */
        fun adaptStatusBar(window: Window, statusBarColor: Int, darkMode: Boolean) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                //移除透明状态栏
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                //在window上绘制一个与系统状态栏区域一样大的背景 颜色填充由 setStatusBarColor 决定
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                //如果背景透明 退出
                if (statusBarColor == Color.TRANSPARENT) return
                //若要使setStatusBarColor生效 必须移除透明状态栏 即退出全屏
                //并设置WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS
                window.statusBarColor = statusBarColor
            } else {
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
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
                val temp =
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                window.decorView.systemUiVisibility = temp
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.statusBarColor = statusBarColor
                //
//            if (darkMode) {
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                    window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//                }
//            }
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)


//            if (PhoneUtils.isMIUI())
//                setStatusBarDarkMode(window, b);
//            }
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                //6.0以上设置亮色状态栏模式 必须 设置自己绘制状态栏
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                val temp: Int
                temp = if (darkMode) {
                    View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                } else {
                    View.VISIBLE
                }
                window.decorView.systemUiVisibility = temp
                window.statusBarColor = statusBarColor
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                //设置状态栏为系统默认的透明色
                if (PhoneUtils.isMIUI()) {
                    miuiSetStatusBarDarkMode(window, darkMode)
                } else if (PhoneUtils.isFlyme()) {
                    flymeSetStatusBarLightMode(window, darkMode)
                } else if (PhoneUtils.isColorOS()) {
                    oppoStatusBarDarkMode(window, statusBarColor, darkMode)
                } else { //其他类型的手机 vivo 原生android
                    window.decorView.systemUiVisibility = View.VISIBLE
                    window.decorView.fitsSystemWindows = true
                    val temp: Int
                    temp = if (darkMode) {
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    } else {
                        View.SYSTEM_UI_FLAG_VISIBLE
                    }
                    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                    window.decorView.systemUiVisibility = temp
                    window.statusBarColor = statusBarColor
                }
            } else {
                //4.4需要设置布局根布局背景颜色和导航一致
                if (PhoneUtils.isMIUI()) {
                    if (darkMode) {
                        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                    } else {
                        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
                    }
                    miuiSetStatusBarDarkMode(window, darkMode)
                } else {
                    setTranslucentStatus(window, darkMode)
                }
            }
        }

        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        fun oppoStatusBarDarkMode(window: Window, statusBarColor: Int, darkMode: Boolean) {
            val temp: Int
            temp = if (darkMode) {
                SYSTEM_UI_FLAG_OP_STATUS_BAR_TINT
            } else {
                View.SYSTEM_UI_FLAG_VISIBLE
            }
            window.decorView.systemUiVisibility = temp
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = statusBarColor
        }

        /**
         * 设置魅族手机状态栏图标颜色风格
         * 可以用来判断是否为Flyme用户
         *
         * @param window 需要设置的窗口
         * @param dark   是否把状态栏字体及图标颜色设置为深色
         * @return boolean 成功执行返回true
         */
        fun flymeSetStatusBarLightMode(window: Window?, dark: Boolean) {
            if (window != null) {
                try {
                    val lp = window.attributes
                    val darkFlag = WindowManager.LayoutParams::class.java
                        .getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON")
                    val meizuFlags = WindowManager.LayoutParams::class.java
                        .getDeclaredField("meizuFlags")
                    darkFlag.isAccessible = true
                    meizuFlags.isAccessible = true
                    val bit = darkFlag.getInt(null)
                    var value = meizuFlags.getInt(lp)
                    value = if (dark) {
                        value or bit
                    } else {
                        value and bit.inv()
                    }
                    meizuFlags.setInt(lp, value)
                    window.attributes = lp
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

        fun setTranslucentStatus(window: Window, darkMode: Boolean) {
            val winParams = window.attributes
            val bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
            if (darkMode) {
                winParams.flags = winParams.flags or bits
            } else {
                winParams.flags = winParams.flags and bits.inv()
            }
            window.attributes = winParams
        }

        fun kitkatCompat(window: Window) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                val view = window.decorView
            }
        }
    }
}