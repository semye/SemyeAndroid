package com.semye.android.ui.thirdparty.glide

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import com.semye.android.LOCAL_GIF_URL
import com.semye.android.LOCAL_IMAGE_URL
import com.semye.android.LOCAL_WEBP_IMAGE_URL_ANIMATION
import com.semye.android.R

/**
 * Created by yesheng on 2020/11/13
 * Glide源码太过复杂。
 */
class GlideMainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var imageUrl: AppCompatTextView

    private var mUrl: String = LOCAL_IMAGE_URL
    private lateinit var imageView: ImageView

    private lateinit var mOriginalBtn: Button
    private lateinit var mCircleCropBtn: Button
    private lateinit var mCenterCropBtn: Button
    private lateinit var mCornerBtn: Button

    private lateinit var mLocalImageBtn: Button
    private lateinit var mLocalGifBtn: Button
    private lateinit var mLocalWebpBtn: Button

    private var mIsWebp: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_glide)
        imageUrl = findViewById(R.id.imageUrl)

        imageView = findViewById(R.id.image)

        mOriginalBtn = findViewById(R.id.btn_original)
        mOriginalBtn.setOnClickListener(this)

        mCircleCropBtn = findViewById(R.id.btn_circle_crop)
        mCircleCropBtn.setOnClickListener(this)

        mCenterCropBtn = findViewById(R.id.btn_center_crop)
        mCenterCropBtn.setOnClickListener(this)

        mCornerBtn = findViewById(R.id.btn_corner)
        mCornerBtn.setOnClickListener(this)

        mLocalImageBtn = findViewById(R.id.btn_load_local_image)
        mLocalImageBtn.setOnClickListener(this)

        mLocalGifBtn = findViewById(R.id.btn_load_local_gif)
        mLocalGifBtn.setOnClickListener(this)

        mLocalWebpBtn = findViewById(R.id.btn_load_local_webp)
        mLocalWebpBtn.setOnClickListener(this)

        showUrl()
    }

    companion object {
        val TAG = GlideMainActivity::class.java.simpleName
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_load_local_image -> {
                mUrl = LOCAL_IMAGE_URL
                showUrl()
                mIsWebp = false
            }
            R.id.btn_load_local_gif -> {
                mUrl = LOCAL_GIF_URL
                showUrl()
                mIsWebp = false
            }
            R.id.btn_load_local_webp -> {
                mUrl = LOCAL_WEBP_IMAGE_URL_ANIMATION
                showUrl()
                mIsWebp = true
            }
            R.id.btn_original -> {
                SemyeImageLoader.displayImage(this@GlideMainActivity, mUrl, imageView)
            }
            R.id.btn_circle_crop -> {
                if (mIsWebp) {
                    SemyeImageLoader.displayCircleWebpImage(this@GlideMainActivity, mUrl, imageView)
                } else {
                    SemyeImageLoader.displayCircleImage(this@GlideMainActivity, mUrl, imageView)
                }
            }
            R.id.btn_center_crop -> {
                if (mIsWebp) {
                    SemyeImageLoader.displayCenterCropWebpImage(
                        this@GlideMainActivity,
                        mUrl,
                        imageView
                    )
                } else {
                    SemyeImageLoader.displayCenterCropImage(this@GlideMainActivity, mUrl, imageView)
                }
            }
            R.id.btn_corner -> {
                if (mIsWebp) {
                    SemyeImageLoader.displayWebpImageWithCorner(
                        this@GlideMainActivity,
                        mUrl,
                        imageView,
                        10
                    )
                } else {
                    SemyeImageLoader.displayImageWithCorner(
                        this@GlideMainActivity,
                        mUrl,
                        imageView,
                        10
                    )
                }
            }
        }
    }

    private fun showUrl() {
        imageUrl.text = mUrl
    }
}