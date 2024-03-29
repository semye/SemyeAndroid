package com.semye.android.module.item24_glide

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Registry
import com.bumptech.glide.module.GlideModule

/**
 * 3.x版本GlideModule
 */
class Custom2GlideModule : GlideModule {
    override fun applyOptions(context: Context, builder: GlideBuilder) {}
    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {}
}