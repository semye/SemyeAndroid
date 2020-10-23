package com.semye.android

import android.os.Bundle
import android.os.Environment
import androidx.appcompat.app.AppCompatActivity

/**
 *  Created by yesheng on 2020/9/23
 */
class EnvironmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_environment)
        println(Environment.getRootDirectory())
        println(Environment.getDownloadCacheDirectory())
        println(Environment.getExternalStorageState())
        println(Environment.getDataDirectory())
        println(Environment.isExternalStorageEmulated())
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
            println(Environment.isExternalStorageLegacy())
        }
        println(Environment.isExternalStorageRemovable())
    }
}