package com.semye.android.ui.lifecycle

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.semye.android.R

class LifeCycleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifecycle)
        Log.e("yesheng", "LifeCycleActivity onCreate")
        findViewById<Button>(R.id.btn1).setOnClickListener(View.OnClickListener {
            val intent = Intent(LifeCycleActivity@ this, LifeCycleTokenActivity::class.java)
            startActivity(intent)
        })
        findViewById<Button>(R.id.btn2).setOnClickListener(View.OnClickListener {
            val intent = Intent(LifeCycleActivity@ this, LifeCycleTokenActivity::class.java)
            startActivity(intent)
            finish()
        })
    }

    override fun onStart() {
        super.onStart()
        Log.e("yesheng", "LifeCycleActivity onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.e("yesheng", "LifeCycleActivity onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e("yesheng", "LifeCycleActivity onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e("yesheng", "LifeCycleActivity onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("yesheng", "LifeCycleActivity onDestroy")
    }
}