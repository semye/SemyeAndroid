package com.semye.android.utils

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent

/**
 * Created by yesheng on 2017/4/9.
 */
object NotificationUtil {
    fun showNotification(context: Context, clz: Class<*>?, notification: Notification?) {
        val intent = Intent(context, clz)
        val pi = PendingIntent.getActivity(context, 1, intent, 0)
        val nm = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        nm.notify(0, notification)
    }
}