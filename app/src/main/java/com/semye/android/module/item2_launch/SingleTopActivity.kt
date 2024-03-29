package com.semye.android.module.item2_launch

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.semye.android.R

/**
 * single top launchMode
 * 如果该activity在栈顶,则不创建新的activity。调用onNewIntent
 */
class SingleTopActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stack_1)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.title = SingleTopActivity::class.java.simpleName
        }
        val button1 = findViewById<Button>(R.id.button1)
        button1.setOnClickListener {
            val intent = Intent(this@SingleTopActivity, SingleTaskActivity::class.java)
            startActivity(intent)
        }
        val button2 = findViewById<Button>(R.id.button2)
        button2.setOnClickListener {
            val intent = Intent(this@SingleTopActivity, SingleTopActivity::class.java)
            startActivity(intent)
        }
        val button3 = findViewById<Button>(R.id.button3)
        button3.setOnClickListener {
            val intent = Intent(this@SingleTopActivity, StandardActivity::class.java)
            startActivity(intent)
        }
        val button4 = findViewById<Button>(R.id.button4)
        button4.setOnClickListener {
            val intent = Intent(this@SingleTopActivity, SingleInstanceActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        println("SingleTopActivity onNewIntent")
    }
}