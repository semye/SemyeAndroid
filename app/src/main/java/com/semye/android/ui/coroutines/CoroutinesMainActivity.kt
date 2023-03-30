package com.semye.android.ui.coroutines

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.semye.android.R
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Created by yesheng on 2020/11/29.
 * 协程
 * 线程进程都是同步机制，而协程则是异步。
 */
class CoroutinesMainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mBtn: Button
    private lateinit var mBtn2: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine)
        mBtn = findViewById(R.id.btn1)
        mBtn2 = findViewById(R.id.btn2)
        mBtn.setOnClickListener(this)
        mBtn2.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn1 -> {
                GlobalScope.launch { // 在后台启动一个新的协程并继续
                    delay(1000L) // 非阻塞的等待 1 秒钟（默认时间单位是毫秒）
                    println("World1" + Thread.currentThread().name) // 在延迟后打印输出
                }
                GlobalScope.launch { // 在后台启动一个新的协程并继续
                    delay(1000L) // 非阻塞的等待 1 秒钟（默认时间单位是毫秒）
                    println("World2" + Thread.currentThread().name) // 在延迟后打印输出
                }
                println("Hello,") // 协程已在等待时主线程还在继续

                runBlocking {

                }
            }
            R.id.btn2 -> {
                val job = GlobalScope.launch {
                    println("协程" + Thread.currentThread().name)
                }
                println(job)
            }
        }
    }
}