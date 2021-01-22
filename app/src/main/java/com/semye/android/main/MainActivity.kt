package com.semye.android.main

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.SystemClock
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
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

    private var mTextView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_YES
        setContentView(R.layout.activity_main)
        mRecyclerView = findViewById(R.id.recyclerview)

        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        println(connectivityManager.toString())
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

        // if (delegate.localNightMode == AppCompatDelegate.MODE_NIGHT_YES) {
        //     delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_NO
        // } else {
        //     delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_YES
        // }

        val metrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(metrics)
        println(
            metrics.widthPixels
        )
        println(
            "1dp 等于" + TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 1f,
                metrics
            ) + "像素"
        )
        println(
            "1sp 等于" + TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP, 1f,
                metrics
            ) + "像素"
        )

        println(
            "设备开机到现在的毫秒数,包括休眠时间===>" + SystemClock.elapsedRealtime()
        )
        println(
            "设备开机到现在的毫秒数,不包括休眠时间====>" + SystemClock.uptimeMillis()
        )

        mTextView?.addOnLayoutChangeListener(object : View.OnLayoutChangeListener {
            override fun onLayoutChange(
                v: View?,
                left: Int,
                top: Int,
                right: Int,
                bottom: Int,
                oldLeft: Int,
                oldTop: Int,
                oldRight: Int,
                oldBottom: Int
            ) {
                println("left==>" + left)
                println("top==>" + top)
                println("right==>" + right)
                println("bottom==>" + bottom)
                println("oldLeft==>" + oldLeft)
                println("oldTop==>" + oldTop)
                println("oldRight==>" + oldRight)
                println("oldBottom==>" + oldBottom)
                println("width==>" + mTextView?.width)
                println("heigth==>" + mTextView?.height)
            }
        })
    }
}