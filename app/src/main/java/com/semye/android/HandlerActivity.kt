package com.semye.android

import android.os.*
import androidx.appcompat.app.AppCompatActivity

/**
 *  Created by yesheng on 2020/9/23
 *  @see HandlerThread Handler+Thread的封装
 *  @see Looper
 *  @see Message
 *  @see Handler
 */
class HandlerActivity : AppCompatActivity(), Handler.Callback {

    private var handlerThread: HandlerThread? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_handle)
        val myHandler = Handler(this)
        MyThread("1").start()
        MyThread("2").start()
        MyThread("3").start()

        handlerThread = HandlerThread("My HandlerThread")
        handlerThread?.start()
        handlerThread?.quit()
        println(Looper.getMainLooper().toString())
        println(handlerThread?.looper.toString())
    }


    override fun handleMessage(msg: Message?): Boolean {
        when (msg?.what) {
            1 -> println("fuck")
        }
        println("收到了message")
        return true
    }

    class MyThread(name: String) : Thread() {

        private val handler = Handler()

        override fun run() {
            super.run()
            Looper.prepare()
            println("looper------>" + Looper.myLooper())
            println("thread name " + currentThread().name)
            handler.sendEmptyMessage(1)
            handler.post {
                println("post thread===>" + currentThread().name)
            }
        }
    }
}