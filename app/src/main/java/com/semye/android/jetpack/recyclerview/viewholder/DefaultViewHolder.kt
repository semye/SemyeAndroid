package com.semye.android.jetpack.recyclerview.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.semye.android.R
import com.semye.android.jetpack.recyclerview.base.BaseViewHolder

class DefaultViewHolder private constructor(itemView: View) : BaseViewHolder(itemView) {

    private var textView: TextView = itemView.findViewById(R.id.text)

    override fun bindData(model: Any?) {
        super.bindData(model)
        if (model is String) {
            textView.text = model
        }
    }

    companion object {
        fun create(parent: ViewGroup): DefaultViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_item_default, parent, false)
            return DefaultViewHolder(view)
        }
    }
}