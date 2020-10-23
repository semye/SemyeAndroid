package com.semye.android.okhttp.okhttpsample

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.semye.android.R
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_okhttp)
        val get = findViewById<Button>(R.id.rq_get)
        val post = findViewById<Button>(R.id.rq_post)
        val post2 = findViewById<Button>(R.id.rq_post2)
        val post3 = findViewById<Button>(R.id.rq_post3)
        val post4 = findViewById<Button>(R.id.rq_post4)
        val head = findViewById<Button>(R.id.rq_head)
        val put = findViewById<Button>(R.id.rq_put)
        val delete = findViewById<Button>(R.id.rq_delete)
        val patch = findViewById<Button>(R.id.rq_patch)
        get.setOnClickListener { doGet() }
        post.setOnClickListener { doPost() }
        post2.setOnClickListener { doPost2() }
        post3.setOnClickListener { doPost3() }
        post4.setOnClickListener {
            try {
                val inputStream = assets.open("1.txt")
                doPost4(inputStream.readBytes())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        head.setOnClickListener { doHead() }
        put.setOnClickListener { doPut() }
        delete.setOnClickListener { doDelete() }
        patch.setOnClickListener { doPatch() }
    }
}