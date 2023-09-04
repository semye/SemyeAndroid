package com.semye.android.ui.item12_jni

import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.semye.android.R
import java.util.*

/**
 *  Created by yesheng on 2020/11/26
 */
class JniActivity : AppCompatActivity(), View.OnClickListener {

    private val soLoader by lazy {
        SoLoader()
    }

    private lateinit var btn1: Button
    private lateinit var btn2: Button
    private lateinit var btn3: Button
    private lateinit var btn4: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jni)
        btn1 = findViewById(R.id.btn1)
        btn2 = findViewById(R.id.btn2)
        btn3 = findViewById(R.id.btn3)
        btn4 = findViewById(R.id.btn4)
        btn1.setOnClickListener(this)
        btn2.setOnClickListener(this)
        btn3.setOnClickListener(this)
        btn4.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn1 -> {
                Toast.makeText(this, "从C获取的数字:" + soLoader.getIntFromNative(), Toast.LENGTH_LONG).show()
            }
            R.id.btn2 -> {
                Toast.makeText(this, "进程id:" + soLoader.getStringFromNative(), Toast.LENGTH_LONG).show()
            }
            R.id.btn3 -> {
                soLoader.createFile(Environment.getExternalStorageDirectory().path + "/yesheng.txt")
            }
            R.id.btn4 -> {
                val result = soLoader.bubbleSort(intArrayOf(33, 22, 11, 435, 67, 23, 65))
                println(Arrays.toString(result))
            }
        }
    }
}