package com.semye.android.ui.item4_service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.semye.android.IMyAidlInterface
import com.semye.android.SERVICE_TAG

/**
 *  Created by yesheng on 2020/11/12
 *  绑定的service
 */
class BindService : Service() {

    override fun onCreate() {
        super.onCreate()
        Log.d(SERVICE_TAG, "onCreate: bindservice created")
    }

    /**
     * 绑定的service不会执行onStart
     */
    override fun onStart(intent: Intent?, startId: Int) {
        super.onStart(intent, startId)
        Log.d(SERVICE_TAG, "bindservice onStart")
    }

    /**
     * 绑定的service不会执行onStartCommand
     */
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(SERVICE_TAG, "bindservice onStartCommand")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(SERVICE_TAG, "bindservice onDestroy")
    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.d(SERVICE_TAG, "bindservice: onBind service")
        return MySub()
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.d(SERVICE_TAG, "bindservice: onUnbind")
        return super.onUnbind(intent)
    }

    class MySub : IMyAidlInterface.Stub() {
        override fun basicTypes(aString: String?) {
            Log.d(SERVICE_TAG, "basicTypes: " + aString)
        }
    }
}