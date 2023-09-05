package com.semye.android.module.item15_handler

import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.os.Message
import android.os.MessageQueue
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.semye.android.R

/**
 *  Created by yesheng on 2020/9/23
 *  Handler消息机制
 *  @see HandlerThread Handler+Thread的封装
 *  @see Looper looper用于在一个线程中运行消息循环.looper内部创建了一个MessageQueue
 *  @see Message
 *  @see Handler 创建handler时如果不指定looper则默认使用当前线程的looper
 *  @see MessageQueue 消息队列
 */
class HandlerActivity : AppCompatActivity(), View.OnClickListener {

    private val mHandlerThread: HandlerThread by lazy {
        HandlerThread("handler-thread")
    }
    private val mLooperThread: LooperThread by lazy {
        LooperThread("looper-thread")
    }
    private var mCurrentHandler: Handler? = null
    private var mHandlerThreadHandler: CustomHandler? = null
    private var mLooperThreadHandler: CustomHandler? = null
    private var mMainThreadHandler: CustomHandler? = null

    private lateinit var mSendDelayMessageButton: AppCompatButton
    private lateinit var mSendEmptyMessageButton: AppCompatButton
    private lateinit var mSendMessageButton: AppCompatButton

    private lateinit var mLooperThreadButton: AppCompatButton
    private lateinit var mHandlerThreadButton: AppCompatButton
    private lateinit var mMainThreadButton: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_handle)

        mLooperThreadButton = findViewById(R.id.looper_thread_handler)
        mHandlerThreadButton = findViewById(R.id.handler_thread_handler)
        mMainThreadButton = findViewById(R.id.main_thread_handler)
        mLooperThreadButton.setOnClickListener(this)
        mHandlerThreadButton.setOnClickListener(this)
        mMainThreadButton.setOnClickListener(this)

        mSendDelayMessageButton = findViewById(R.id.send_delay_message)
        mSendMessageButton = findViewById(R.id.send_message)
        mSendEmptyMessageButton = findViewById(R.id.send_empty_message)
        mSendDelayMessageButton.setOnClickListener(this)
        mSendEmptyMessageButton.setOnClickListener(this)
        mSendMessageButton.setOnClickListener(this)

        if (!mLooperThread.isAlive) {
            mLooperThread.start()
        }

        if (!mHandlerThread.isAlive) {
            mHandlerThread.start()
            mHandlerThreadHandler = mHandlerThread.looper?.let {
                CustomHandler(it)
            }
        }
        mMainThreadHandler = CustomHandler()
        mCurrentHandler = mMainThreadHandler
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.send_delay_message -> {
                mCurrentHandler?.postDelayed({
                    Toast.makeText(this, "延迟的message", Toast.LENGTH_SHORT).show()
                }, 1000)
            }
            R.id.send_empty_message -> {
                mCurrentHandler?.sendEmptyMessage(CUSTOM_HANDLER_EMPTY_MESSAGE)
            }
            R.id.send_message -> {
                mCurrentHandler?.sendMessage(obtain())
            }
            R.id.looper_thread_handler -> {
                if (mLooperThreadHandler == null) {
                    mLooperThreadHandler = mLooperThread.handler
                }
                mCurrentHandler = mLooperThreadHandler
            }
            R.id.handler_thread_handler -> {
                mCurrentHandler = mHandlerThreadHandler
            }
            R.id.main_thread_handler -> {
                mCurrentHandler = mMainThreadHandler
            }
        }
    }

    private fun obtain(): Message {
        return Message.obtain().apply {
            what = CUSTOM_HANDLER_MESSAGE
            obj = "这个一条消息"
        }
    }
}