package com.semye.android

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/**
 *  Created by yesheng on 2020/9/23
 */
class CountDownTimerActivity : AppCompatActivity(), View.OnClickListener {

    private var mTextView: TextView? = null
    private var mButton: TextView? = null
    private val myCountDownTimer = MyCountDownTimer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_count_down_timer)
        mTextView = findViewById(R.id.text)
        mButton = findViewById(R.id.button)
        mButton?.setOnClickListener(this)
    }

    /**
     * 30秒倒计时
     */
    inner class MyCountDownTimer : CountDownTimer(30000, 1000) {
        @SuppressLint("SetTextI18n")
        override fun onTick(millisUntilFinished: Long) {
            mTextView?.text = "seconds remaining: " + millisUntilFinished / 1000
        }

        @SuppressLint("SetTextI18n")
        override fun onFinish() {
            mTextView?.text = "done!"
        }
    }

    override fun onClick(v: View?) {
        myCountDownTimer.start()
    }
}