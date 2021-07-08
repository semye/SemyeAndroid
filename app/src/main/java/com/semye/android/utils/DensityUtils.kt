package com.semye.android.utils

import android.content.Context
import android.util.TypedValue

class DensityUtils private constructor() {
    companion object {
        /**
         * dp 转化为 px
         *
         * @param context
         * @param dpVal
         * @return
         */
        fun dp2px(context: Context, dpVal: Float): Int {
            return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                    dpVal, context.resources.displayMetrics).toInt()
        }

        /**
         * sp 转化为 px
         *
         * @param context
         * @param spVal
         * @return
         */
        fun sp2px(context: Context, spVal: Float): Int {
            return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                    spVal, context.resources.displayMetrics).toInt()
        }

        /**
         * px 转化为 dp
         *
         * @param context
         * @param pxVal
         * @return
         */
        fun px2dp(context: Context, pxVal: Float): Float {
            val scale = context.resources.displayMetrics.density
            return pxVal / scale
        }

        /**
         * px 转化为 sp
         *
         * @param pxVal
         * @param pxVal
         * @return
         */
        fun px2sp(context: Context, pxVal: Float): Float {
            return pxVal / context.resources.displayMetrics.scaledDensity
        }
    }

    init {
        /* cannot be instantiated */
        throw UnsupportedOperationException("cannot be instantiated")
    }
}