package com.semye.android.module.main

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.semye.android.R

/**
 *  Created by yesheng on 2020/10/27
 */
class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    init {
        itemView.setOnClickListener(this)
    }

    private val textView: TextView = itemView.findViewById(R.id.textview)

    private var pair: Pair<String, Class<*>>? = null

    private val mContext: Context = itemView.context

    companion object {

        fun createViewHolder(parent: ViewGroup): ItemViewHolder {
            val itemView =
                LayoutInflater.from(parent.context).inflate(R.layout.item_main, parent, false)
            return ItemViewHolder(itemView)
        }
    }

    fun bindData(pair: Pair<String, Class<*>>, position: Int) {
        this.pair = pair
        val pos = if (position < 4) {
            position
        } else {
            position % 4
        }
        itemView.setBackgroundColor(
            when (pos) {
                0 -> Color.parseColor("#48D1CC")
                1 -> Color.parseColor("#FB7299")
                2 -> Color.parseColor("#FF7F50")
                3 -> Color.parseColor("#4169E1")
                else -> Color.WHITE
            }
        )
        textView.text = pair.first
    }

    override fun onClick(v: View?) {
        try {
            pair?.second?.let {
                val intent = Intent(mContext, it)
                mContext.startActivity(intent)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}