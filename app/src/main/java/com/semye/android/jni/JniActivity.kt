package com.semye.android.jni

import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Button
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
                println(soLoader.intFromNative)
            }
            R.id.btn2 -> {
                println(soLoader.stringFromNative)
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