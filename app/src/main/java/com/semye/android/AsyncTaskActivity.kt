package com.semye.android

import android.os.AsyncTask
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity

/**
 *  Created by yesheng on 2020/9/23
 */
class AsyncTaskActivity : AppCompatActivity() {

    private var progressBar: ProgressBar? = null
    private var button: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_async_task)
        progressBar = findViewById(R.id.progress_bar)
        button = findViewById(R.id.start)
        button?.setOnClickListener {
            val task = MyAsyncTask()
            task.execute(null)
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, null)
            task.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, null)
        }
    }
}

class MyAsyncTask : AsyncTask<Void, Int, Int>() {

    override fun onPreExecute() {
        super.onPreExecute()
        println("onPreExecute " + Thread.currentThread().name)
    }

    override fun onProgressUpdate(vararg values: Int?) {
        super.onProgressUpdate(*values)
    }

    override fun onPostExecute(result: Int?) {
        super.onPostExecute(result)
    }

    override fun doInBackground(vararg params: Void?): Int {
        publishProgress(1)
        return 1
    }
}