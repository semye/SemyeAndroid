package com.semye.android.ui.jetpack.startup

import android.content.Context
import android.util.Log
import androidx.startup.Initializer
import com.facebook.common.logging.FLog
import com.facebook.drawee.backends.pipeline.Fresco

/**
 * startup库的使用
 * 初始化fresco
 */
const val TAG = "Initializer_tag"

class FrescoInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        Log.d(TAG, "初始化fresco")
        Fresco.initialize(context)
        FLog.setMinimumLoggingLevel(FLog.VERBOSE)
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }
}