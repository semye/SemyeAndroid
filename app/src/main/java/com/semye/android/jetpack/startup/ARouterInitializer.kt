package com.semye.android.jetpack.startup

import android.app.Application
import android.content.Context
import androidx.startup.Initializer
import com.alibaba.android.arouter.launcher.ARouter

/**
 *  Created by yesheng on 2020/11/27
 */
class ARouterInitializer : Initializer<Any> {
    override fun create(context: Context): Any {
        if (context is Application) {
            ARouter.init(context)
        }
        return Any()
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }
}