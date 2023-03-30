package com.semye.android.ui.animation.animator

import android.animation.FloatEvaluator
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import com.semye.android.R

/**
 *  Created by yesheng on 2020/11/9
 */
class ObjectAnimatorActivity : AppCompatActivity(), View.OnClickListener {

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
        ObjectAnimator.ofPropertyValuesHolder(
            mTarget,
            PropertyValuesHolder.ofFloat("alpha", 0f, 0.5f, 1f),
            PropertyValuesHolder.ofFloat("textSize", 10f, 14f, 18f, 20f)
        ).setDuration(2000)
            .start()
    }

    private fun useFloatValue() {
        ObjectAnimator.ofFloat(mTarget, "alpha", 0f, 0.2f, 0.5f, 1f)
            .setDuration(2000)
            .start()
    }

    private fun useArgbValue() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ObjectAnimator.ofArgb(
                mTarget, "textColor", 0xff0000ff.toInt(),
                0xff00ffff.toInt(),
                0xffff00ff.toInt(), 0xff000000.toInt()
            )
                .setDuration(2000)
                .start()
        }
    }

    private fun useObjectValue() {
        ObjectAnimator.ofObject(mTarget, "textSize", FloatEvaluator(), 12, 14, 16, 18)
            .setDuration(2000)
            .start()
    }

    private fun useIntValue() {
        ObjectAnimator.ofInt(
            mTarget, "textColor",
            0xff0000ff.toInt(),
            0xff00ffff.toInt(),
            0xffff00ff.toInt(),
            0xff000000.toInt()
        )
            .setDuration(2000)
            .start()
    }
}