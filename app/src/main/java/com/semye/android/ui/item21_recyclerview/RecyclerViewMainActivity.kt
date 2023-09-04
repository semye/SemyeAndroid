package com.semye.android.ui.item21_recyclerview

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.semye.android.R

/**
 * Created by yesheng on 2019-09-12
 * RecyclerView 使用
 */
class RecyclerViewMainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyclerview_main)
        findViewById<View>(R.id.vertical_list).setOnClickListener(this)
        findViewById<View>(R.id.horizontal_list).setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.vertical_list -> {
                val intent = Intent(this, RecyclerViewActivity::class.java)
                intent.putExtra(RecyclerViewActivity.ORIENTATION, RecyclerView.VERTICAL)
                startActivity(intent)
            }
            R.id.horizontal_list -> {
                val intent = Intent(this, RecyclerViewActivity::class.java)
                intent.putExtra(RecyclerViewActivity.ORIENTATION, RecyclerView.HORIZONTAL)
                startActivity(intent)
            }
        }
    }
}