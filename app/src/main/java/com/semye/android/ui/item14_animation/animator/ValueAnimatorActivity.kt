package com.semye.android.ui.item14_animation.animator

import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import com.semye.android.R
import com.semye.android.ui.item14_animation.StringEvaluator

/**
 *  Created by yesheng on 2020/11/9
 */
class ValueAnimatorActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mArgb: AppCompatButton
    private lateinit var mValueInt: AppCompatButton
    private lateinit var mValueFloat: AppCompatButton
    private lateinit var mValueObject: AppCompatButton
    private lateinit var mProperty: AppCompatButton
    private lateinit var mTarget: AppCompatTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animator)
        mArgb = findViewById(R.id.argb)
        mValueInt = findViewById(R.id.value_int)
        mValueFloat = findViewById(R.id.value_float)
        mValueObject = findViewById(R.id.value_object)
        mProperty = findViewById(R.id.value_property)
        mTarget = findViewById(R.id.target)
        mArgb.setOnClickListener(this)
        mValueFloat.setOnClickListener(this)
        mValueObject.setOnClickListener(this)
        mValueInt.setOnClickListener(this)
        mProperty.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.argb -> {
                useArgbValue()
            }
            R.id.value_int -> {
                useIntValue()
            }
            R.id.value_float -> {
                useFloatValue()
            }
            R.id.value_property -> {
                usePropertyValuesHolder()
            }
            R.id.value_object -> {
                useObjectValue()
            }
        }
    }

    private fun usePropertyValuesHolder() {
        val valueAnimator = ValueAnimator.ofPropertyValuesHolder(
            PropertyValuesHolder.ofFloat("alpha", 0f, 0.5f, 1f),
            PropertyValuesHolder.ofFloat("size", 10f, 14f, 18f, 20f)
        )
        valueAnimator.addUpdateListener { animation ->
            mTarget.alpha = animation.getAnimatedValue("alpha") as Float
            mTarget.textSize = animation.getAnimatedValue("size") as Float
        }
        valueAnimator.duration = 2000
        valueAnimator.start()
    }

    private fun useFloatValue() {
        val valueAnimator = ValueAnimator.ofFloat(0f, 0.2f, 0.5f, 1f)
        valueAnimator.addUpdateListener { animation ->
            val alpha = animation?.animatedValue as Float
            mTarget.alpha = alpha
        }
        valueAnimator.duration = 2000
        valueAnimator.start()
    }

    private fun useIntValue() {
        val valueAnimator = ValueAnimator.ofInt(1, 3, 5, 7, 9)
        valueAnimator.addUpdateListener { animation ->
            val text = animation?.animatedValue as Int
            mTarget.text = text.toString()
        }
        valueAnimator.duration = 2000
        valueAnimator.start()
    }

    private fun useArgbValue() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val valueAnimator =
                ValueAnimator.ofArgb(0xff0000ff.toInt(), 0xff00ffff.toInt(), 0xffff00ff.toInt(), 0xff000000.toInt())
            valueAnimator.addUpdateListener { animation ->
                val textColor = animation?.animatedValue as Int
                mTarget.setTextColor(textColor)
            }
            valueAnimator.duration = 2000
            valueAnimator.start()
        }
    }

    private fun useObjectValue() {
        val valueAnimator =
            ValueAnimator.ofObject(
                StringEvaluator(), "haha", "yesheng", "semye", "xixi",
                "bilibili"
            )
        valueAnimator.addUpdateListener { animation ->
            val text = animation?.animatedValue as String
            mTarget.text = text
        }
        valueAnimator.duration = 2000
        valueAnimator.start()
    }
}