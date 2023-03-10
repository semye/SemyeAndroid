package com.semye.android.main

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.MotionEvent
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.semye.android.R
import com.semye.android.view.WindowMainActivity

/**
 *  Created by yesheng on 2020/9/23
 */
class MainActivity : AppCompatActivity() {

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: MainItemAdapter

    private var textView1: TextView? = null
    private var textView2: TextView? = null
    private var textView3: TextView? = null
    private var textView4: TextView? = null

    private val mainViewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(
            MainViewModel::class.java
        )
    }

    private val androidViewModel: AndroidViewModel by lazy {
        ViewModelProvider.AndroidViewModelFactory.getInstance(this.application).create(
            AndroidViewModel::class.java
        )
    }

    override fun onWindowAttributesChanged(params: WindowManager.LayoutParams?) {
        super.onWindowAttributesChanged(params)
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        return super.dispatchTouchEvent(ev)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_NO
        setContentView(R.layout.activity_main)
        Log.e("yesheng", Log.getStackTraceString(Throwable()))
        Log.e("yesheng", "===========>" + Thread.currentThread().name)

        val defaultFactory = WindowMainActivity.DefaultTextViewFactory(this)
        textView1 = defaultFactory.createTextView("左上角", Color.WHITE)
        textView2 = defaultFactory.createTextView("右上角", Color.WHITE)
        textView3 = defaultFactory.createTextView("右下角", Color.WHITE)
        textView4 = defaultFactory.createTextView("左下角", Color.WHITE)

        mRecyclerView = findViewById(R.id.recyclerview)

        mainViewModel.requestListData()

//        startActivity(Intent())

        mainViewModel.mList.observe(this, object : Observer<List<Pair<String, Class<*>>>> {
            override fun onChanged(t: List<Pair<String, Class<*>>>) {

                mAdapter = MainItemAdapter(t)
                val layoutManager = LinearLayoutManager(this@MainActivity)
                mRecyclerView.addItemDecoration(
                    DividerItemDecoration(
                        this@MainActivity,
                        DividerItemDecoration.VERTICAL
                    )
                )
                mRecyclerView.layoutManager = layoutManager
                mRecyclerView.adapter = mAdapter
            }
        })
    }

    override fun onResume() {
        super.onResume()
//        addView4()
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
}