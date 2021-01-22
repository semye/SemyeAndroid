package com.semye.android.thirdparty.glide

import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import androidx.vectordrawable.graphics.drawable.Animatable2Compat
import com.bumptech.glide.Glide
import com.bumptech.glide.integration.webp.decoder.WebpDrawable
import com.bumptech.glide.integration.webp.decoder.WebpDrawableTransformation
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target

/**
 * Created by yesheng on 2018/9/3.
 */
object SemyeImageLoader {

    @JvmStatic
    fun displayImage(context: Context, url: String?, imageView: ImageView?) {
        if (imageView == null) return
        GlideApp.with(context)
            .load(url)
            .into(imageView)
    }

    @JvmStatic
    fun displayCircleImage(context: Context, url: String?, imageView: ImageView?) {
        if (imageView == null) return
        GlideApp.with(context)
            .load(url)
            .circleCrop()
            .into(imageView)
    }

    @JvmStatic
    fun displayCenterCropImage(context: Context, url: String?, imageView: ImageView?) {
        if (imageView == null) return
        GlideApp.with(context)
            .load(url)
            .centerCrop()
            .into(imageView)
    }

    /**
     * 图片设置圆角
     *
     * @param context
     * @param uri
     * @param imageView
     * @param roundingRadius
     */
    @JvmStatic
    fun displayImageWithCorner(
        context: Context,
        url: String?,
        imageView: ImageView?,
        roundingRadius: Int
    ) {
        if (imageView == null) return
        val roundedCorners = RoundedCorners(roundingRadius)
        GlideApp.with(context)
            .load(url)
            .apply(RequestOptions.bitmapTransform(roundedCorners))
            .into(imageView)
    }

    @JvmStatic
    fun displayCircleWebpImage(context: Context, url: String?, imageView: ImageView?) {
        if (imageView == null) return
        GlideApp.with(context)
            .asWebp()
            .load(url)
            .circleCrop()
            .into(imageView)
    }

    @JvmStatic
    fun displayCenterCropWebpImage(context: Context, url: String?, imageView: ImageView?) {
        if (imageView == null) return
        GlideApp.with(context)
            .asWebp()
            .load(url)
            .centerCrop()
            .into(imageView)
    }

    /**
     * 图片设置圆角
     *
     * @param context
     * @param uri
     * @param imageView
     * @param roundingRadius
     */
    @JvmStatic
    fun displayWebpImageWithCorner(
        context: Context,
        url: String?,
        imageView: ImageView?,
        roundingRadius: Int
    ) {
        if (imageView == null) return
        val roundedCorners = RoundedCorners(roundingRadius)
        GlideApp.with(context)
            .asWebp()
            .load(url)
            .transform(WebpDrawable::class.java, WebpDrawableTransformation(roundedCorners))
            .into(imageView)
    }

    @JvmStatic
    fun displayGifImageWithLoopCount(
        context: Context,
        url: String?,
        imageView: ImageView?,
        loopCount: Int
    ) {
        if (imageView == null) return
        GlideApp.with(context)
            .asGif()
            .load(url)
            .listener(object : RequestListener<GifDrawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any,
                    target: Target<GifDrawable>,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: GifDrawable,
                    model: Any,
                    target: Target<GifDrawable>,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    val frameCount = resource.frameCount
                    println("帧数$frameCount")
                    resource.setLoopCount(loopCount)
                    resource.registerAnimationCallback(object :
                        Animatable2Compat.AnimationCallback() {
                        override fun onAnimationStart(drawable: Drawable) {
                            super.onAnimationStart(drawable)
                            println("gif开始播放")
                        }

                        override fun onAnimationEnd(drawable: Drawable) {
                            super.onAnimationEnd(drawable)
                            println("gif停止播放")
                            //回到首帧
                            GlideApp.with(context)
                                .load(url)
                                .apply(RequestOptions.noAnimation())
                                .into(imageView)
                        }
                    })
                    return false
                }
            })
            .into(imageView)
    }
}