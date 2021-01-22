package com.semye.android.thirdparty.glide

import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.annotation.GlideExtension
import com.bumptech.glide.annotation.GlideOption
import com.bumptech.glide.annotation.GlideType
import com.bumptech.glide.integration.webp.decoder.WebpDrawable
import com.bumptech.glide.integration.webp.decoder.WebpDrawableTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.request.BaseRequestOptions

/**
 * 自定义glide扩展,会在GlideApp中生成对应的方法
 * 这里主要是对webp做了支持
 */
@GlideExtension
object SemyeGlideExtension {

    @JvmStatic
    @GlideOption(override = GlideOption.OVERRIDE_EXTEND)
    fun centerCrop(options: BaseRequestOptions<*>): BaseRequestOptions<*> {
        return options.transform(WebpDrawable::class.java, WebpDrawableTransformation(CenterCrop()))
    }

    @JvmStatic
    @GlideOption(override = GlideOption.OVERRIDE_EXTEND)
    fun circleCrop(options: BaseRequestOptions<*>): BaseRequestOptions<*> {
        return options.transform(WebpDrawable::class.java, WebpDrawableTransformation(CircleCrop()))
    }

    @JvmStatic
    @GlideOption(override = GlideOption.OVERRIDE_EXTEND)
    fun centerInside(options: BaseRequestOptions<*>): BaseRequestOptions<*> {
        return options.transform(
            WebpDrawable::class.java,
            WebpDrawableTransformation(CenterInside())
        )
    }

    @JvmStatic
    @GlideOption(override = GlideOption.OVERRIDE_EXTEND)
    fun fitCenter(options: BaseRequestOptions<*>): BaseRequestOptions<*> {
        return options.transform(WebpDrawable::class.java, WebpDrawableTransformation(FitCenter()))
    }

    @GlideType(WebpDrawable::class)
    @JvmStatic
    fun asWebp(requestBuilder: RequestBuilder<WebpDrawable>): RequestBuilder<WebpDrawable> {
        return requestBuilder
    }
}