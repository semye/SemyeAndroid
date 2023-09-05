package com.semye.android.module.item5_thread

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.semye.android.R

/**
 * 线程
 */
class ThreadActivity : AppCompatActivity() {

    class MyThread : Thread("yesheng-thread") {

        override fun run() {
            super.run()
            for (a in 0..100000000) {
                println(a)
            }
        }

    }

    val myThread = MyThread()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread)
        findViewById<Button>(R.id.start).setOnClickListener(View.OnClickListener {
            myThread.start()
        })
        findViewById<Button>(R.id.stop).setOnClickListener(View.OnClickListener {
            try {
                myThread.stop()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        })
        findViewById<Button>(R.id.join).setOnClickListener(View.OnClickListener {
            myThread.join()
        })
        findViewById<Button>(R.id.interrupt).setOnClickListener(View.OnClickListener {
            myThread.interrupt()
        })
    }
}