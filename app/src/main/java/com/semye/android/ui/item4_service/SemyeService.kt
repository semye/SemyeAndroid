package com.semye.android.ui.item4_service

import android.app.IntentService
import android.content.Intent
import android.util.Log
import com.semye.android.SERVICE_TAG

/**
 *  Created by yesheng on 2020/10/3
 *
 *  IntentService里启动了一个HandlerThread
 *
 * 使用 WorkManager 或jobIntentService替代
 * 因为IntentService受8.0后台限制
 *
 */
class SemyeService : IntentService("SemyeService") {

    override fun onCreate() {
        super.onCreate()
        Log.d(SERVICE_TAG, "SemyeService created")
    }

    override fun onStart(intent: Intent?, startId: Int) {
        super.onStart(intent, startId)
        Log.d(SERVICE_TAG, "onStart")
    }

    override fun onHandleIntent(intent: Intent?) {
        Log.d(SERVICE_TAG, "onHandle:start")
        Log.d(SERVICE_TAG, "current thread name:${Thread.currentThread().name}")
        for (index in 1..100) {
            Thread.sleep(1000)
            Log.d(SERVICE_TAG, "onHandle index:$index")
        }
        Log.d(SERVICE_TAG, "onHandle:end")
    }

    override fun onDestroy() {
        Log.d(SERVICE_TAG, "SemyeService destroyed ")
    }
}