package com.semye.android.ui.main

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 *  Created by yesheng on 2020/10/27
 */
class MainItemAdapter(private val mutableMap: List<Pair<String, Class<*>>>) :
    RecyclerView.Adapter<ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder.createViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return mutableMap.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bindData(mutableMap[position], position)
    }
}