package com.semye.android.ui.threa

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.semye.android.R

class ThreadActivity : AppCompatActivity() {

    class MyThread : Thread("yesheng-thread") {

        override fun run() {
            super.run()
        }

    }

    val myThread = MyThread()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread)
        findViewById<Button>(R.id.start).setOnClickListener(View.OnClickListener {
            myThread.start()
        })
        findViewById<Button>(R.id.start).setOnClickListener(View.OnClickListener {
            myThread.stop()
        })
        findViewById<Button>(R.id.start).setOnClickListener(View.OnClickListener {
            myThread.join()
        })
        findViewById<Button>(R.id.start).setOnClickListener(View.OnClickListener {
            myThread.stop()
        })
    }
}