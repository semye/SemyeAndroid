package com.semye.android.ui.item21_recyclerview.base

import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.RecyclerView

/**
 * @param Model
 * @param ViewHolder
 */
abstract class BaseAdapter<Model, ViewHolder : BaseViewHolder> :
    RecyclerView.Adapter<ViewHolder>() {
    var offset = 0
    private var mData: MutableList<Model>? = null
    private var mContext: Context? = null

    fun addData(data: MutableList<Model>?) {
        data?.let {
            mData?.addAll(it)
        }
        notifyDataSetChanged()
    }

    private var data: MutableList<Model>?
        get() = mData
        set(mData) {
            this.mData = mData
            notifyDataSetChanged()
        }

    val dataCount: Int
        get() = mData?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: MutableList<Any>) {
        super.onBindViewHolder(holder, position, payloads)
        Log.d(TAG, "onBindViewHolder $position")
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position)
        } else {
            //局部刷新操作
        }
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun onViewRecycled(holder: ViewHolder) {
        super.onViewRecycled(holder)
        Log.d(TAG, "ViewHolder 被回收了 $holder")
    }

    override fun onViewAttachedToWindow(holder: ViewHolder) {
        super.onViewAttachedToWindow(holder)
        Log.d(TAG, "attach to window $holder")
    }

    override fun onViewDetachedFromWindow(holder: ViewHolder) {
        super.onViewDetachedFromWindow(holder)
        Log.d(TAG, "detach from window $holder")
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        Log.d(TAG, "attach to recyclerView")
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        Log.d(TAG, "detach from recyclerView")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        onBindViewHolder(holder, position, findModelByPosition(position))
    }

    abstract fun onBindViewHolder(holder: ViewHolder, position: Int, model: Model?)

    fun findModelByPosition(position: Int): Model? {
        return mData?.get(position)
    }

    override fun getItemCount(): Int {
        return mData?.size ?: 0
    }

    companion object {
        const val TAG = "BaseAdapter"
    }
}