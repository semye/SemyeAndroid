package com.semye.android.module.main

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.MotionEvent
import android.view.WindowManager
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.semye.android.R
import com.semye.android.module.item30_window.WindowMainActivity
import com.semye.annotation.SemyeClass
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 *  Created by yesheng on 2020/9/23
 */
@SemyeClass
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var mRecyclerView: RecyclerView

    @Inject
    lateinit var mAdapter: MainItemAdapter

    private var textView1: TextView? = null
    private var textView2: TextView? = null
    private var textView3: TextView? = null
    private var textView4: TextView? = null

    val mainViewModel: MainViewModel by viewModels()


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

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.uiState.collect {
                    mAdapter.mutableMap = it.list
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
            }
        }

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