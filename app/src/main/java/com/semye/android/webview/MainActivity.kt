package com.semye.android.webview

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import com.semye.android.R

/**
 * Created by yesheng on 2019/1/29
 */
class MainActivity : AppCompatActivity(), View.OnClickListener {
    var editText: AppCompatEditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_webview1)
        editText = findViewById(R.id.edit_url)
        editText?.setText("https://www.bilibili.com")
        val button1 = findViewById<AppCompatButton>(R.id.button1)
        val button2 = findViewById<AppCompatButton>(R.id.button2)
        button1.setOnClickListener(this)
        button2.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        val id = v.id
        when (id) {
            R.id.button1 -> openCustomWeb(editText!!.text.toString())
            R.id.button2 -> openSystemWeb(editText!!.text.toString())
        }
    }

    private fun openCustomWeb(url: String) {
        val intent = Intent(this, SampleWebActivity::class.java)
        intent.putExtra(BaseWebViewToolBarActivity.LOAD_URL, url)
        startActivity(intent)
    }

    private fun openSystemWeb(url: String) {
        try {
            val uri = Uri.parse(url)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            intent.addCategory(Intent.CATEGORY_BROWSABLE)
            startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}