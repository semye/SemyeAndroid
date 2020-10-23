package com.semye.android.view

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.semye.android.R

class TouchEventActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_touch_event)
        val view1 = findViewById<View>(R.id.event_layout)
        view1.setOnClickListener {
            println("layout onClick")
        }
        val view2 = findViewById<View>(R.id.event_view)
        view2.setOnClickListener {
            println("view click")
        }
        view2.setOnTouchListener { _, _ ->
            println("view onTouch")
            //true 会消费触摸事件
            false
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        println("Activity dispatch touch event " + ev?.action)
        return super.dispatchTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        println("Activity consume " + event?.action)
        return super.onTouchEvent(event)
    }
}