package com.semye.android.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import com.semye.android.R
import com.semye.android.TokenActivity
import com.semye.android.WINDOW_TAG

/**
 * window
 * 退出Activity前如果不清除window上的view。会leak window
 */
class WindowMainActivity : AppCompatActivity(), View.OnClickListener {

    private var textView1: TextView? = null
    private var textView2: TextView? = null
    private var textView3: TextView? = null
    private var textView4: TextView? = null

    interface TextViewFactory {
        fun createTextView(text: String, @ColorInt color: Int): AppCompatTextView
    }

    class DefaultTextViewFactory(var context: Context) : TextViewFactory {
        override fun createTextView(text: String, color: Int): AppCompatTextView {
            return AppCompatTextView(context).apply {
                this.text = text
                this.setTextColor(color)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_window)
        val window = window
        Log.d(WINDOW_TAG, "window:$window")
        Log.d(WINDOW_TAG, "WindowManager:$windowManager")
        findViewById<View>(R.id.add_window).setOnClickListener(this)
        findViewById<View>(R.id.remove_window).setOnClickListener(this)
        findViewById<View>(R.id.token).setOnClickListener(this)

        val defaultFactory = DefaultTextViewFactory(this)
        textView1 = defaultFactory.createTextView("左上角", Color.WHITE)
        textView2 = defaultFactory.createTextView("右上角", Color.WHITE)
        textView3 = defaultFactory.createTextView("右下角", Color.WHITE)
        textView4 = defaultFactory.createTextView("左下角", Color.WHITE)
    }

    @SuppressLint("RtlHardcoded")
    private fun addView1() {
        if (textView1?.isAttachedToWindow == false) {
            val windowManager = windowManager
            val layoutParams = WindowManager.LayoutParams()
            layoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_SUB_PANEL
            layoutParams.flags =
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL or WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
            layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT
            layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT
            layoutParams.gravity = Gravity.LEFT or Gravity.TOP
            layoutParams.x = 0
            layoutParams.y = 0
            windowManager.addView(textView1, layoutParams)
        }
    }

    @SuppressLint("RtlHardcoded")
    private fun addView2() {
        if (textView2?.isAttachedToWindow == false) {
            val windowManager = windowManager
            val layoutParams = WindowManager.LayoutParams()
            layoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_SUB_PANEL
            layoutParams.flags =
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL or WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
            layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT
            layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT
            layoutParams.gravity = Gravity.RIGHT or Gravity.TOP
            layoutParams.x = 0
            layoutParams.y = 0
            windowManager.addView(textView2, layoutParams)
        }
    }

    @SuppressLint("RtlHardcoded")
    private fun addView3() {
        if (textView3?.isAttachedToWindow == false) {
            val windowManager = windowManager
            val layoutParams = WindowManager.LayoutParams()
            layoutParams.title = "helloworld"
            layoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_SUB_PANEL
            layoutParams.flags =
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL or WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
            layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT
            layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT
            layoutParams.gravity = Gravity.RIGHT or Gravity.BOTTOM
            layoutParams.x = 0
            layoutParams.y = 0
            windowManager.addView(textView3, layoutParams)
        }
    }

    @SuppressLint("RtlHardcoded")
    private fun addView4() {
        if (textView4?.isAttachedToWindow == false) {
            val windowManager = windowManager
            val layoutParams = WindowManager.LayoutParams()
            layoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_SUB_PANEL
            layoutParams.flags =
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL or WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
            layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT
            layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT
            layoutParams.gravity = Gravity.LEFT or Gravity.BOTTOM
            layoutParams.x = 0
            layoutParams.y = 0
            windowManager.addView(textView4, layoutParams)
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.add_window -> {
                addView1()
                addView2()
                addView3()
                addView4()
            }
            R.id.remove_window -> {
                removeView()
            }
            R.id.token -> {
                val intent = Intent(this, TokenActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun removeView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (textView1 != null && textView1!!.isAttachedToWindow) {
                windowManager.removeView(textView1)
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (textView2 != null && textView2!!.isAttachedToWindow) {
                windowManager.removeView(textView2)
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (textView3 != null && textView3!!.isAttachedToWindow) {
                windowManager.removeView(textView3)
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (textView4 != null && textView4!!.isAttachedToWindow) {
                windowManager.removeView(textView4)
            }
        }
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}