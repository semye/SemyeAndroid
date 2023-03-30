package com.semye.android.ui.lifecycle

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.semye.android.R

class LifeCycleTokenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifecycletoken)
        Log.e("yesheng","LifeCycleTokenActivity onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.e("yesheng","LifeCycleTokenActivity onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.e("yesheng","LifeCycleTokenActivity onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e("yesheng","LifeCycleTokenActivity onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e("yesheng","LifeCycleTokenActivity onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("yesheng","LifeCycleTokenActivity onDestroy")
    }
}