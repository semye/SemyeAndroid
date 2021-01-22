package com.semye.android.utils

import android.content.res.Resources

/**
 *  Created by yesheng on 2020/12/11
 */

/**
 * dp ==> px
 */
val <T : Number> T.toPx: Float
    get() = toFloat() * Resources.getSystem().displayMetrics.density + 0.5f

/**
 * px ==> dp
 */
val <T : Number> T.toDp: Float
    get() = toFloat() / Resources.getSystem().displayMetrics.density + 0.5f