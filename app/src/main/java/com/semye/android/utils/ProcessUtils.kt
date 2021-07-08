package com.semye.android.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Process;
import androidx.annotation.NonNull;
import android.text.TextUtils;

import java.util.List;

/**
 * Created by yesheng on 2018/11/13.
 */
public class ProcessUtils {

    /**
     * 判断当前app的进程是否是主进程
     *
     * @param context context
     * @return
     */
    public static boolean isMainProcess(@NonNull Context context) {
        return TextUtils.equals(context.getPackageName(), getProcessName(context, Process.myPid()));
    }


    /**
     * 根据进程id获取进程名
     *
     * @param context
     * @param pid
     * @return
     */
    public static String getProcessName(@NonNull Context context, int pid) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (am != null) {
            List<ActivityManager.RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
            if (runningApps == null) {
                return null;
            }
            for (ActivityManager.RunningAppProcessInfo processInfo : runningApps) {
                if (processInfo.pid == pid) {
                    return processInfo.processName;
                }
            }
        }
        return null;
    }


}
