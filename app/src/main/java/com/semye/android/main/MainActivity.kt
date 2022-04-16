package com.semye.android.main

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.semye.android.R

/**
 *  Created by yesheng on 2020/9/23
 */
class MainActivity : AppCompatActivity() {

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: MainItemAdapter

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

    private var mTextView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_NO
        setContentView(R.layout.activity_main)
        mRecyclerView = findViewById(R.id.recyclerview)

        mainViewModel.requestListData()

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
}