package com.semye.android.module.item14_animation

import android.animation.LayoutTransition
import android.animation.ObjectAnimator
import android.animation.TimeAnimator
import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import com.semye.android.R
import com.semye.android.module.item14_animation.animator.ObjectAnimatorActivity
import com.semye.android.module.item14_animation.animator.TimeAnimatorActivity
import com.semye.android.module.item14_animation.animator.ValueAnimatorActivity

/**
 * 属性动画
 * @see ValueAnimator
 * @see ObjectAnimator
 * @see TimeAnimator
 * @see android.animation.TypeEvaluator
 * @see android.animation.TimeInterpolator
 * 类的属性随插值器变化
 */
class PropertyAnimationActivity : AppCompatActivity(), View.OnClickListener,
    LayoutTransition.TransitionListener {

    private lateinit var valueAnimator: AppCompatTextView
    private lateinit var objectAnimator: AppCompatTextView
    private lateinit var timeAnimator: AppCompatTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_property_animation)
        valueAnimator = findViewById(R.id.valueanimator)
        objectAnimator = findViewById(R.id.objectanimator)
        timeAnimator = findViewById(R.id.timeanimator)
        valueAnimator.setOnClickListener(this)
        objectAnimator.setOnClickListener(this)
        timeAnimator.setOnClickListener(this)


        val transition = LayoutTransition()
        transition.addTransitionListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.valueanimator -> {
                val intent =
                    Intent(this@PropertyAnimationActivity, ValueAnimatorActivity::class.java)
                startActivity(intent)
            }

            R.id.objectanimator -> {
                val intent =
                    Intent(this@PropertyAnimationActivity, ObjectAnimatorActivity::class.java)
                startActivity(intent)
            }

            R.id.timeanimator -> {
                val intent =
                    Intent(this@PropertyAnimationActivity, TimeAnimatorActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun startTransition(
        transition: LayoutTransition?,
        container: ViewGroup?,
        view: View?,
        transitionType: Int
    ) {
    }

    override fun endTransition(
        transition: LayoutTransition?,
        container: ViewGroup?,
        view: View?,
        transitionType: Int
    ) {
    }
}