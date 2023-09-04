package com.semye.android.ui.item15_handler

import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log

/**
 *  Created by yesheng on 2020/10/27
 *  custom handler to handle message
 */
class CustomHandler : Handler {
    constructor(looper: Looper) : super(looper)
    constructor() : super()

    override fun handleMessage(msg: Message) {
        super.handleMessage(msg)
        Log.d(TAG, "handleMessage: $msg")
        when (msg.what) {
            CUSTOM_HANDLER_EMPTY_MESSAGE -> {
                Log.d(TAG, "[${Thread.currentThread().name}]收到空消息")
            }
            CUSTOM_HANDLER_MESSAGE -> {
                Log.d(TAG, "[${Thread.currentThread().name}]收到消息")
            }
        }
    }
}