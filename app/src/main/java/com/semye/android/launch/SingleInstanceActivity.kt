package com.semye.android.launch

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.semye.android.R

/**
 * 单例 launchMode
 */
class SingleInstanceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stack_1)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.title = SingleInstanceActivity::class.java.simpleName
        }
        val button1 = findViewById<Button>(R.id.button1)
        button1.setOnClickListener {
            val intent = Intent(this@SingleInstanceActivity, SingleTaskActivity::class.java)
            startActivity(intent)
        }
        val button2 = findViewById<Button>(R.id.button2)
        button2.setOnClickListener {
            val intent = Intent(this@SingleInstanceActivity, SingleTopActivity::class.java)
            startActivity(intent)
        }
        val button3 = findViewById<Button>(R.id.button3)
        button3.setOnClickListener {
            val intent = Intent(this@SingleInstanceActivity, StandardActivity::class.java)
            startActivity(intent)
        }
        val button4 = findViewById<Button>(R.id.button4)
        button4.setOnClickListener {
            val intent = Intent(this@SingleInstanceActivity, SingleInstanceActivity::class.java)
            startActivity(intent)
        }
    }
}