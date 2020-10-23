package com.semye.android

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import androidx.appcompat.app.AppCompatActivity

/**
 *  Created by yesheng on 2020/9/23
 *
 *  looper用于在一个线程中运行消息循环
 */
class LooperActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_looper)

        println(mainLooper.toString())
        LooperThread().start()
    }

    inner class LooperThread : Thread() {

        @SuppressLint("HandlerLeak")
        private var mHandler = object : Handler() {

            override fun handleMessage(msg: Message?) {
                // process incoming messages here
            }
        }

        override fun run() {
            Looper.prepare()
            Looper.loop()
        }
    }
}