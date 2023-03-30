package com.semye.android.ui.view.fitsystemwindow

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.semye.android.R

/**
 *  Created by yesheng on 2020/9/27
 *  fitsSystemWindows 只有在状态栏透明时才生效
 *  设置透明状态栏后会使布局延伸到状态栏中
 *  fitsSystemWindows 为true时会使布局的padding失效
 *  默认值为false
 */
class FitSystemWindowActivity : AppCompatActivity(), View.OnClickListener {

    private var mButton1: Button? = null
    private var mButton2: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fit_system)
        mButton1 = findViewById(R.id.button1)
        mButton2 = findViewById(R.id.button2)
        mButton1?.setOnClickListener(this)
        mButton2?.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button1 -> {
                val intent = Intent(this, FitSystemWindowTrueActivity::class.java)
                startActivity(intent)
            }
            R.id.button2 -> {
                val intent = Intent(this, FitSystemWindowFalseActivity::class.java)
                startActivity(intent)
            }
        }
    }
}