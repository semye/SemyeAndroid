package com.semye.android.view.statusbar

import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.annotation.ColorInt

object StatusBarCompat {

    @JvmStatic
    fun setTranslucentStatusBar(window: Window) {
        setStatusBarColor(window, Color.TRANSPARENT)
    }

    @JvmStatic
    fun setStatusBarColor(window: Window?, @ColorInt color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window?.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window?.statusBarColor = color
        } else {
            //需设置fitSystemWindow=true
            window?.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
    }
}