package com.semye.android.module.item3_vieweventdispatch

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.semye.android.R

/**
 * Android触摸事件分发
 */
class TouchEventActivity : AppCompatActivity() {

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_touch_event)
        val view1 = findViewById<View>(R.id.event_layout)
        view1.setOnClickListener {
            Log.e("yesheng","layout onClick")
        }
        val view2 = findViewById<View>(R.id.event_view)
        view2.setOnClickListener {
            Log.e("yesheng","view click")
        }
        view2.setOnTouchListener { _, _ ->
            Log.e("yesheng","view onTouch")
            //true 会消费触摸事件
            false
        }
    }

    /**
     * true 当前方法消费掉事件
     * false 当前方法消费掉事件
     * super 交给触点的View或者ViewGroup处理
     */
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        Log.e("yesheng","Activity dispatch touch event " + ev?.action)
        return super.dispatchTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.e("yesheng","Activity consume " + event?.action)
        return super.onTouchEvent(event)
    }
}