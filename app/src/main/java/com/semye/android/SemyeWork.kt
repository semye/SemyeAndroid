package com.semye.android

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.google.common.util.concurrent.ListenableFuture

class SemyeWork(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {


    override fun doWork(): Result {
        TODO("Not yet implemented")
    }
}