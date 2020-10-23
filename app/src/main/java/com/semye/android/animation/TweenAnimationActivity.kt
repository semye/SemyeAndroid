package com.semye.android.animation

import android.os.Bundle
import android.view.View
import android.view.animation.*
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.semye.android.R

/**
 * 补间动画
 */
class TweenAnimationActivity : AppCompatActivity(), View.OnClickListener {
    private var textView: TextView? = null
    private var alphaAnimation: AlphaAnimation = AlphaAnimation(0f, 1f)
    private var scaleAnimation: ScaleAnimation = ScaleAnimation(1.0f, 2.0f, 1.0f, 2.0f)
    private var translateAnimation: TranslateAnimation = TranslateAnimation(0f, 200f, 0f, 100f)
    private var rotateAnimation: RotateAnimation = RotateAnimation(0f, 360f)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tween_animation)
        textView = findViewById(R.id.tv_animator)
        val alpha = findViewById<TextView>(R.id.alpha)
        val translate = findViewById<TextView>(R.id.translate)
        val rotate = findViewById<TextView>(R.id.rotate)
        val scale = findViewById<TextView>(R.id.scale)
        alpha.setOnClickListener(this)
        translate.setOnClickListener(this)
        rotate.setOnClickListener(this)
        scale.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.alpha -> {
                alphaAnimation.duration = 3000 //动画持续时间
                alphaAnimation.repeatCount = Animation.INFINITE //动画重复次数
                alphaAnimation.repeatMode = Animation.REVERSE//这个参数只有在repeat count大于0或者INFINITE时才有效
                alphaAnimation.interpolator = BounceInterpolator()//设置动画插值器
                alphaAnimation.fillAfter = true
                alphaAnimation.fillBefore = true
                alphaAnimation.isFillEnabled = true
                alphaAnimation.backgroundColor = 0 //设置动画背景
                alphaAnimation.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationRepeat(animation: Animation?) {
                    }

                    override fun onAnimationEnd(animation: Animation?) {
                    }

                    override fun onAnimationStart(animation: Animation?) {
                    }
                })
                textView?.startAnimation(alphaAnimation)
            }
            R.id.translate -> {
                translateAnimation.duration = 3000
                translateAnimation.fillAfter = true
                textView?.startAnimation(translateAnimation)
            }
            R.id.rotate -> {
                rotateAnimation.duration = 3000
                rotateAnimation.fillAfter = true
                textView?.startAnimation(rotateAnimation)
            }
            R.id.scale -> {
                scaleAnimation.duration = 3000
                scaleAnimation.fillAfter = true
                textView?.startAnimation(scaleAnimation)
            }
        }
    }
}