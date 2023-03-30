package com.semye.android.ui.app

import android.app.ZygotePreload
import android.content.pm.ApplicationInfo
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi

/**
 *  Created by yesheng on 2020/11/12
 */
@RequiresApi(Build.VERSION_CODES.Q)
class SemyeZygotePreload : ZygotePreload {
    override fun doPreload(appInfo: ApplicationInfo) {
        Log.d(TAG, "doPreload: $appInfo")
    }
}

const val TAG = "SemyeZygotePreload"