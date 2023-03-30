package com.semye.android.ui.jetpack.recyclerview.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.semye.android.R
import com.semye.android.ui.jetpack.recyclerview.base.BaseViewHolder

class HeaderViewHolder private constructor(itemView: View) : BaseViewHolder(itemView) {

    override fun bindData(model: Any?) {
        super.bindData(model)
        if (model is String) {
        }
    }

    companion object {
        fun create(parent: ViewGroup): HeaderViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_header, parent, false)
            return HeaderViewHolder(view)
        }
    }
}