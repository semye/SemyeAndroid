package com.semye.android.app.service.job

import android.app.job.JobParameters
import android.app.job.JobService
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.semye.android.SERVICE_TAG

/**
 *  Created by yesheng on 2020/11/12
 *  可参考文章:https://www.jianshu.com/p/aa957774a965
 *  https://www.jianshu.com/p/1f2103d3d2a2
 *
 *  adb shell dumpsys jobscheduler
 */
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class SemyeJobService : JobService() {

    override fun onCreate() {
        super.onCreate()
        Log.d(SERVICE_TAG, "SemyeJobService created ")
    }

    override fun onStartJob(params: JobParameters?): Boolean {
        Log.d(SERVICE_TAG, "onStartJob: ======>")
        kotlin.runCatching {
            Thread {
                for (index in 1..10) {
                    Log.d(SERVICE_TAG, "onStartJob: $index")
                    Thread.sleep(100)
                }
                jobFinished(params, true)
            }.start()
        }
        return true
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        return false
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(SERVICE_TAG, "SemyeJobService destroyed ")
    }
}