package com.semye.android.animation

import android.animation.ObjectAnimator
import android.animation.TimeAnimator
import android.animation.ValueAnimator
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.semye.android.R
import com.semye.android.animation.StringEvaluator

/**
 * 属性动画
 * @see ValueAnimator
 * @see ObjectAnimator
 * @see TimeAnimator
 * @see android.animation.TypeEvaluator
 */
class PropertyAnimationActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var textView: TextView
    private lateinit var textView1: TextView
    private lateinit var textView2: TextView
    private lateinit var textView3: TextView
    private lateinit var textview4: TextView
    private lateinit var textview5: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_property_animation)
        textView = findViewById(R.id.textview)
        textView1 = findViewById(R.id.textview1)
        textView2 = findViewById(R.id.textview2)
        textView3 = findViewById(R.id.textview3)
        textview4 = findViewById(R.id.textview4)
        textview5 = findViewById(R.id.textview5)
        textView1.setOnClickListener(this@PropertyAnimationActivity)
        textView2.setOnClickListener(this@PropertyAnimationActivity)
        textView3.setOnClickListener(this@PropertyAnimationActivity)
        textview4.setOnClickListener(this@PropertyAnimationActivity)
        textview5.setOnClickListener(this@PropertyAnimationActivity)
    }

    private fun useArgbValueAnimator() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val valueAnimator =
                ValueAnimator.ofArgb(0xff00ffff.toInt(), 0xffff00ff.toInt(), 0xffffff00.toInt())
            valueAnimator.addUpdateListener { animation ->
                val textColor = animation?.animatedValue as Int
                textView.setTextColor(textColor)
            }
            valueAnimator.duration = 2000
            valueAnimator.start()
        }
    }

    private fun useFloatValueAnimator() {
        val valueAnimator = ValueAnimator.ofFloat(0f, 0.2f, 0.5f, 1f)
        valueAnimator.addUpdateListener { animation ->
            val alpha = animation?.animatedValue as Float
            textView.alpha = alpha
        }
        valueAnimator.duration = 2000
        valueAnimator.start()
    }

    private fun useIntValueAnimator() {
        val valueAnimator = ValueAnimator.ofInt(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        valueAnimator.addUpdateListener { animation ->
            val text = animation?.animatedValue as Int
            textView.text = text.toString()
        }
        valueAnimator.duration = 2000
        valueAnimator.start()
    }

    private fun useObjectValueAnimator() {
        val valueAnimator =
            ValueAnimator.ofObject(StringEvaluator(), "haha", "yesheng", "semye", "xixi", "fuck")
        valueAnimator.addUpdateListener { animation ->
            val text = animation?.animatedValue as String
            textView.text = text
        }
        valueAnimator.duration = 2000
        valueAnimator.start()
    }

    private fun useFloatObjectAnimator() {
        //设置透明度
        val propertyValuesHolder =
            ObjectAnimator.ofFloat(textView, "alpha", 0.1f, 0.3f, 0.5f, 0.7f, 0.9f, 1f)
        propertyValuesHolder.duration = 2000
        propertyValuesHolder.start()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.textview1 -> useArgbValueAnimator()
            R.id.textview2 -> useFloatValueAnimator()
            R.id.textview3 -> useIntValueAnimator()
            R.id.textview4 -> useObjectValueAnimator()
            R.id.textview5 -> useFloatObjectAnimator()
        }
    }
}