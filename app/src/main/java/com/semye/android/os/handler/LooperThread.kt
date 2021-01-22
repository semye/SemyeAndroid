package com.semye.android.os.handler

import android.os.Looper
import android.util.Log

/**
 *  Created by yesheng on 2020/11/11
 *  a thread has a looper
 */
class LooperThread(name: String) : Thread(name) {

    var handler: CustomHandler? = null

    override fun run() {
        super.run()
        Looper.prepare()
        Log.d(TAG, "looper------>" + Looper.myLooper())
        Log.d(TAG, "thread name " + currentThread().name)
        handler = CustomHandler()
        Log.d(TAG, "run: ${handler?.looper}")
        Looper.loop()
    }
}