package com.semye.android.os

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.semye.android.R

/**
 *  Created by yesheng on 2020/9/23
 *  AsyncTask在API30里已被废弃,使用标准的java并发API或者kotlin并发工具类
 */
class AsyncTaskActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var progressBar: ProgressBar
    private lateinit var button: Button
    private var processTask: ProcessTask? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_async_task)
        progressBar = findViewById(R.id.progress_bar)
        button = findViewById(R.id.start)
        button.setOnClickListener(this)
        processTask = ProcessTask(progressBar)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.start -> {
//                processTask?.execute(null)
                processTask?.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, null)
//            processTask?.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, null)
            }
        }
    }
}

const val TAG = "AsyncTask"

class ProcessTask(var progressBar: ProgressBar) : AsyncTask<Void, Int, Int>() {

    override fun onPreExecute() {
        super.onPreExecute()
        Log.d(TAG, "start process task")
    }

    override fun onProgressUpdate(vararg values: Int?) {
        super.onProgressUpdate(*values)
        values[0]?.also {
            progressBar.progress = it
        }
    }

    override fun onPostExecute(result: Int?) {
        super.onPostExecute(result)
        Log.d(TAG, "process task finished")
    }

    override fun doInBackground(vararg params: Void?): Int {
        for (index in 1..100) {
            publishProgress(index)
            Thread.sleep(100)
        }
        return 0
    }
}