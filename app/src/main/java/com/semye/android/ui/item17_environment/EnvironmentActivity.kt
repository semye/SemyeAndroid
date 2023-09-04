package com.semye.android.ui.item17_environment

import android.os.Bundle
import android.os.Environment
import android.os.Process
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import com.semye.android.R

/**
 *  Created by yesheng on 2020/9/23
 */
class EnvironmentActivity : AppCompatActivity() {

    private lateinit var mInfo: AppCompatTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_environment)
        mInfo = findViewById(R.id.info)
        println("RootDirectory:" + Environment.getRootDirectory())
        println("DownloadCacheDirectory:" + Environment.getDownloadCacheDirectory())
        println("ExternalStorageState:" + Environment.getExternalStorageState())
        println("DataDirectory:" + Environment.getDataDirectory())
        println(Environment.isExternalStorageEmulated())
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
            println(Environment.isExternalStorageLegacy())
        }
        println(Environment.isExternalStorageRemovable())
        mInfo.text = StringBuilder().append("PID:")
            .append(Process.myPid())
            .append("\n")
            .append("TID:")
            .append(Process.myTid())
            .append("\n")
            .append("UID:")
            .append(Process.myUid())
    }
}