package com.semye.android.thirdparty.glide

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Registry
import com.bumptech.glide.module.GlideModule

/**
 * 3.x版本的GlideModule 在清单文件中可注册多个
 */
class Custom3GlideModule : GlideModule {
    override fun applyOptions(context: Context, builder: GlideBuilder) {}
    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {}
}