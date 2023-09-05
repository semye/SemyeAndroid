package com.semye.android.module.jetpack.startup

import android.content.Context
import android.util.Log
import androidx.startup.Initializer
import com.bumptech.glide.Glide

class GlideInitializer : Initializer<Glide> {
    override fun create(context: Context): Glide {
        Log.d(TAG, "初始化glide ")
        val file = Glide.getPhotoCacheDir(context)
        if (file != null) {
            println(file.name)
        }
        val file1 = Glide.getPhotoCacheDir(context, "semye")
        if (file1 != null) {
            println(file1.name)
        }
        return Glide.get(context)
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }
}