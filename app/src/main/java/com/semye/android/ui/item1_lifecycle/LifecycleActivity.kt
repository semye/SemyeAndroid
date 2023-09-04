package com.semye.android.ui.item1_lifecycle

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.semye.android.R

/**
 * 生命周期
 */
class LifecycleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifecycle)
        Log.e("yesheng", "LifecycleActivity onCreate")
        findViewById<Button>(R.id.btn1).setOnClickListener(View.OnClickListener {
            val intent = Intent(LifecycleActivity@ this, LifecycleTokenActivity::class.java)
            startActivity(intent)
        })
        findViewById<Button>(R.id.btn2).setOnClickListener(View.OnClickListener {
            val intent = Intent(LifecycleActivity@ this, LifecycleTokenActivity::class.java)
            startActivity(intent)
            finish()
        })
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        Log.e("yesheng", "LifecycleActivity onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.e("yesheng", "LifecycleActivity onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e("yesheng", "LifecycleActivity onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e("yesheng", "LifecycleActivity onStop")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("yesheng", "LifecycleActivity onDestroy")
    }
}