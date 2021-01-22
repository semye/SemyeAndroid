package com.semye.android.animation.animator

import android.animation.TimeAnimator
import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import com.semye.android.R

/**
 *  Created by yesheng on 2020/11/9
 *  @see TimeAnimator
 *  弄不懂这个动画到底有什么用，启动了就不断的运行。
 */
class TimeAnimatorActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var mStart: AppCompatButton
    private lateinit var mTotalTime: AppCompatTextView
    private lateinit var mDeltaTime: AppCompatTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animator_time)
        mTotalTime = findViewById(R.id.total_time)
        mDeltaTime = findViewById(R.id.delta_time)
        mStart = findViewById(R.id.start)
        mStart.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.start -> {
                startAnimation()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun startAnimation() {
        val timeAnimator = TimeAnimator()
        timeAnimator.setTimeListener { animation, totalTime, deltaTime ->
            println(animation?.toString())
            mTotalTime.text = "totalTime:$totalTime"
            mDeltaTime.text = "deltaTime:$deltaTime"
        }
        timeAnimator.start()
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
            Handler.createAsync(mainLooper).postDelayed({ timeAnimator.end() }, 500)
        }
    }
}