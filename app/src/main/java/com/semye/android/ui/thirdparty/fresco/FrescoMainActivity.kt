package com.semye.android.ui.thirdparty.fresco

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder
import com.facebook.drawee.generic.RoundingParams
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.request.ImageRequestBuilder
import com.semye.android.R
import com.semye.android.REMOTE_GIF_IMAGE_URL

/**
 *  Created by yesheng on 2020/10/27
 *
 *  文档
 *  https://www.fresco-cn.org/docs/getting-started.html
 */
class FrescoMainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mSimpleDraweeView: SimpleDraweeView
    private lateinit var mLoadGif: AppCompatButton
    private lateinit var mLoadWebp: AppCompatButton
    private lateinit var mLoadStaticImage: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fresco)

        mLoadGif = findViewById(R.id.load_gif)
        mLoadWebp = findViewById(R.id.load_webp)
        mLoadStaticImage = findViewById(R.id.load_static_image)
        mLoadGif.setOnClickListener(this)
        mLoadWebp.setOnClickListener(this)
        mLoadStaticImage.setOnClickListener(this)

        mSimpleDraweeView = findViewById(R.id.simple_drawee_view)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.load_gif -> {
                val imageRequest =
                    ImageRequestBuilder.newBuilderWithSource(
                        Uri.parse(REMOTE_GIF_IMAGE_URL)
                    ).disableMemoryCache()
                        .build()
                val roundingParams = RoundingParams.fromCornersRadius(100f)
                // 动图是无法实现圆角的 通过overlayColor设置颜色和背景色一样实现圆角
                roundingParams.overlayColor = resources.getColor(android.R.color.black)
                val genericDraweeHierarchy = GenericDraweeHierarchyBuilder.newInstance(resources)
                    .setRoundingParams(roundingParams)
                    .build()
                val controller = Fresco.newDraweeControllerBuilder()
                    .setImageRequest(imageRequest)
                    .setOldController(mSimpleDraweeView.controller)
                    .setAutoPlayAnimations(true)
                    .build()
                mSimpleDraweeView.aspectRatio = 1.33f
                mSimpleDraweeView.hierarchy = genericDraweeHierarchy
                mSimpleDraweeView.controller = controller
            }
            R.id.load_webp -> {
                val imageRequest =
                    ImageRequestBuilder.newBuilderWithSource(
                        Uri.parse("asset:///24069894bdc39a1ef7c0bdb.webp")
                    ).disableMemoryCache()
                        .build()
                val roundingParams = RoundingParams.fromCornersRadius(100f)
                // 动图是无法实现圆角的 通过overlayColor设置颜色和背景色一样实现圆角
                roundingParams.overlayColor = resources.getColor(android.R.color.black)
                val genericDraweeHierarchy = GenericDraweeHierarchyBuilder.newInstance(resources)
                    .setRoundingParams(roundingParams)
                    .build()
                val controller = Fresco.newDraweeControllerBuilder()
                    .setImageRequest(imageRequest)
                    .setOldController(mSimpleDraweeView.controller)
                    .setAutoPlayAnimations(true)
                    .build()
                mSimpleDraweeView.aspectRatio = 1.33f
                mSimpleDraweeView.hierarchy = genericDraweeHierarchy
                mSimpleDraweeView.controller = controller
            }
            R.id.load_static_image -> {
                val imageRequest =
                    ImageRequestBuilder.newBuilderWithSource(
                        Uri.parse("asset:///8cb1cb134954092341ce53589258d109b3de4974.jpg")
                    ).disableMemoryCache()
                        .build()
                val roundingParams = RoundingParams.fromCornersRadius(100f)
                val genericDraweeHierarchy = GenericDraweeHierarchyBuilder.newInstance(resources)
                    .setRoundingParams(roundingParams)
                    .build()
                val controller = Fresco.newDraweeControllerBuilder()
                    .setImageRequest(imageRequest)
                    .setOldController(mSimpleDraweeView.controller)
                    .setAutoPlayAnimations(true)
                    .build()
                mSimpleDraweeView.aspectRatio = 1.33f
                mSimpleDraweeView.hierarchy = genericDraweeHierarchy
                mSimpleDraweeView.controller = controller
            }
        }
    }
}