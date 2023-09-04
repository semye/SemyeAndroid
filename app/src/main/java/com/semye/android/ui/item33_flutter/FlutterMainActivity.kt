package com.semye.android.ui.item33_flutter

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.idlefish.flutterboost.containers.FlutterBoostActivity
import com.semye.android.R

class FlutterMainActivity : AppCompatActivity() {

    lateinit var viewPager2: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flutter_main)
        savedInstanceState?.let {
            supportFragmentManager.fragments.forEach {
                Log.d("yesheng", it.toString())
            }
        }

        findViewById<View>(R.id.tv_jump)?.setOnClickListener {
            startActivity(
                Intent(this,
                FlutterBoostActivity::class.java)
            )
        }
        viewPager2 = findViewById(R.id.container)
        viewPager2.offscreenPageLimit = 2
        viewPager2.adapter = TabPagerAdapter(this)
    }
}