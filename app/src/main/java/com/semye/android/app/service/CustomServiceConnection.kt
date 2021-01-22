package com.semye.android.app.service

import android.content.ComponentName
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log
import com.semye.android.SERVICE_TAG

/**
 *  Created by yesheng on 2020/11/12
 */
object CustomServiceConnection : ServiceConnection {

    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        Log.d(SERVICE_TAG, "onServiceConnected:")
    }

    /**
     * service正常关闭时不会调用
     */
    override fun onServiceDisconnected(name: ComponentName?) {
        Log.d(SERVICE_TAG, "onServiceDisconnected: ")
    }
}