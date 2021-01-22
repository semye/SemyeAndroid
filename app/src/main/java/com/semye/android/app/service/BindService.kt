package com.semye.android.app.service

import android.app.Service
import android.content.Intent
import android.content.ServiceConnection
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

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(SERVICE_TAG, "onCreate: bindservice onStartCommand")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(SERVICE_TAG, "onCreate: bindservice onDestroy")
    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.d(SERVICE_TAG, "onBind: bind service")
        return MySub()
    }

    override fun unbindService(conn: ServiceConnection) {
        super.unbindService(conn)
        Log.d(SERVICE_TAG, "onBind: unbind service")
    }

    class MySub : IMyAidlInterface.Stub() {
        override fun basicTypes(
            anInt: Int,
            aLong: Long,
            aBoolean: Boolean,
            aFloat: Float,
            aDouble: Double,
            aString: String?
        ) {
            Log.d(SERVICE_TAG, "basicTypes: ")
        }
    }
}