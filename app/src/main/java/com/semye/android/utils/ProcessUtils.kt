package com.semye.android.utils

import android.app.ActivityManager
import android.content.Context
import android.os.Process
import android.text.TextUtils

/**
 * Created by yesheng on 2018/11/13.
 */
object ProcessUtils {
    /**
     * 判断当前app的进程是否是主进程
     *
     * @param context context
     * @return
     */
    fun isMainProcess(context: Context): Boolean {
        return TextUtils.equals(context.packageName, getProcessName(context, Process.myPid()))
    }

    /**
     * 根据进程id获取进程名
     *
     * @param context
     * @param pid
     * @return
     */
    fun getProcessName(context: Context, pid: Int): String? {
        val am = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        if (am != null) {
            val runningApps = am.runningAppProcesses ?: return null
            for (processInfo in runningApps) {
                if (processInfo.pid == pid) {
                    return processInfo.processName
                }
            }
        }
        return null
    }
}