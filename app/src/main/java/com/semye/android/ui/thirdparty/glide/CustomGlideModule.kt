package com.semye.android.ui.thirdparty.glide

import android.content.Context
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions
import com.semye.android.BuildConfig
import com.semye.android.GLIDE_MODULE_TAG
import com.semye.android.R

/**
 * Created by yesheng on 2018/11/8.
 * 自定义AppGlideModule 一个应用只能有一个AppGlideModule
 * 默认生成了GlideApp类 通过该类简化了调用,主要是error,placeholder等API
 */
@GlideModule
class CustomGlideModule : AppGlideModule() {

    override fun isManifestParsingEnabled(): Boolean {
        return false
    }

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        Log.d(GLIDE_MODULE_TAG, "应用选项")
        if (BuildConfig.DEBUG) {
            builder.setLogLevel(Log.DEBUG)
        }
        builder.setDefaultRequestOptions(RequestOptions().apply {
            placeholder(R.mipmap.ic_launcher)
            error(R.mipmap.ic_launcher)
            fallback(R.mipmap.ic_launcher)
//            diskCacheStrategy(DiskCacheStrategy.NONE)
            skipMemoryCache(true)// 跳过内存缓存
        })
    }

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        super.registerComponents(context, glide, registry)
    }
}