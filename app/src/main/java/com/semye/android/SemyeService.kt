package com.semye.android

import android.app.IntentService
import android.content.Intent

/**
 *  Created by yesheng on 2020/10/3
 *
 *  IntentService里启动了一个HandlerThread
 */
class SemyeService : IntentService("SemyeService") {

    override fun onHandleIntent(intent: Intent?) {
    }
}