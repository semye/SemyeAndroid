package com.semye.android.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by yesheng on 2017/4/9.
 */
public class NotificationUtil {

    public static void showNotification(@NonNull Context context, Class clz, Notification notification) {
        Intent intent = new Intent(context, clz);
        PendingIntent pi = PendingIntent.getActivity(context, 1, intent, 0);
        NotificationManager nm = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        nm.notify(0, notification);
    }
}
