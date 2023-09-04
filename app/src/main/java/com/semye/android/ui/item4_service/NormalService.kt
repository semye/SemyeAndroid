package com.semye.android.ui.item4_service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.semye.android.SERVICE_TAG

/**
 *  Created by yesheng on 2020/11/12
 *  8.0后台限制
 *  这个服务在华为手机上测试 1分钟左右会被关掉
 */
class NormalService : Service() {

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(SERVICE_TAG, "NormalService started ")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(SERVICE_TAG, "onStartCommand: ${Thread.currentThread().name}")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        Log.d(SERVICE_TAG, "NormalService destroyed ")
    }
}