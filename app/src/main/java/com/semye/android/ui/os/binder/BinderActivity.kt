package com.semye.android.ui.os.binder

import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import com.semye.android.IMyAidlInterface

/**
 *  Created by yesheng on 2020/11/12
 *  Binder
 *  rpc 调用
 *  通常使用AIDL来实现
 *
 *  IPC(inter-process communication) 进程间通信
 */
class BinderActivity : AppCompatActivity() {

    override fun bindService(service: Intent?, conn: ServiceConnection, flags: Int): Boolean {
        return super.bindService(service, conn, flags)
    }
}