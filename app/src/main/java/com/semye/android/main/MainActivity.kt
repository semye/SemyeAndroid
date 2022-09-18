package com.semye.android.main

import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_NO
        setContentView(R.layout.activity_main)
        Log.e("yesheng", Log.getStackTraceString(Throwable()))
        Log.e("yesheng", "===========>" + Thread.currentThread().name)

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
}