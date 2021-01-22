package com.semye.android.jetpack.recyclerview.adapter

import android.view.ViewGroup
import com.semye.android.jetpack.recyclerview.base.BaseAdapter
import com.semye.android.jetpack.recyclerview.base.BaseViewHolder
import com.semye.android.jetpack.recyclerview.viewholder.DefaultViewHolder
import com.semye.android.jetpack.recyclerview.viewholder.HeaderViewHolder

/**
 * Created by yesheng on 2019-09-12
 */
class MainAdapter : BaseAdapter<String, BaseViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return if (viewType == 0) {
            HeaderViewHolder.create(parent)
        } else DefaultViewHolder.create(parent)
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int, s: String?) {
        if (holder is DefaultViewHolder) {
            holder.bindData("positon$position")
        }
        if (holder is HeaderViewHolder) {
            val headerViewHolder = holder
        }
    }

    override fun getItemCount(): Int {
        return 10000
    }
}