package com.semye.android.launch

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.semye.android.R

class StandardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stack_1)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.title = StandardActivity::class.java.simpleName
        }
        val button1 = findViewById<Button>(R.id.button1)
        button1.setOnClickListener {
            val intent = Intent(this@StandardActivity, SingleTaskActivity::class.java)
            startActivity(intent)
        }
        val button2 = findViewById<Button>(R.id.button2)
        button2.setOnClickListener {
            val intent = Intent(this@StandardActivity, SingleTopActivity::class.java)
            startActivity(intent)
        }
        val button3 = findViewById<Button>(R.id.button3)
        button3.setOnClickListener {
            val intent = Intent(this@StandardActivity, StandardActivity::class.java)
            startActivity(intent)
        }
        val button4 = findViewById<Button>(R.id.button4)
        button4.setOnClickListener {
            val intent = Intent(this@StandardActivity, SingleInstanceActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStop() {
        super.onStop()
        Log.e("yesheng","StandardActivity onStop ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("yesheng","StandardActivity onDestroy ")
    }
}