package com.semye.android.animation

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.semye.android.R

/**
 * 帧动画
 */
class FrameAnimationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frame_animation)

        //帧动画
        val imageView = findViewById<ImageView>(R.id.loading)
        imageView.setBackgroundResource(R.drawable.loading)
        val animationDrawable = imageView.background as AnimationDrawable
        animationDrawable.isOneShot = true//设置只执行一次
        animationDrawable.start()
    }
}