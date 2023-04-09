package com.semye.android.ui.jetpack.startup

import android.app.Application
import android.content.Context
import androidx.startup.Initializer
import com.alibaba.android.arouter.launcher.ARouter

/**
 *  Created by yesheng on 2020/11/27
 */
class ARouterInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        if (context is Application) {
            ARouter.init(context)
        }
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }
}