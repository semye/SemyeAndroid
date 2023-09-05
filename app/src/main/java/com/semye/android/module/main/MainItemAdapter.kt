package com.semye.android.module.main

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import javax.inject.Inject

/**
 *  Created by yesheng on 2020/10/27
 */
class MainItemAdapter @Inject constructor() :
    RecyclerView.Adapter<ItemViewHolder>() {

    lateinit var mutableMap: List<Pair<String, Class<*>>>

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