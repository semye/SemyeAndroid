package com.semye.android.ui.animation

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import com.semye.android.R

/**
 * 动画
 */
class AnimationMainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.alpha -> {
                val intent = Intent(this@AnimationMainActivity, FrameAnimationActivity::class.java)
                startActivity(intent)
            }
            R.id.translate -> {
                val intent = Intent(this@AnimationMainActivity, TweenAnimationActivity::class.java)
                startActivity(intent)
            }
            R.id.rotate -> {
                val intent =
                    Intent(this@AnimationMainActivity, PropertyAnimationActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private lateinit var alpha: AppCompatTextView
    private lateinit var translate: AppCompatTextView
    private lateinit var rotate: AppCompatTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_animation)
        alpha = findViewById(R.id.alpha)
        translate = findViewById(R.id.translate)
        rotate = findViewById(R.id.rotate)
        initListener()
    }

    private fun initListener() {
        alpha.setOnClickListener(this)
        translate.setOnClickListener(this)
        rotate.setOnClickListener(this)
    }
}
