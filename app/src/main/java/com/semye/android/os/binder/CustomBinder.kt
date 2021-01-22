package com.semye.android.os.binder

import android.os.Binder
import android.os.Parcel

/**
 *  Created by yesheng on 2020/11/12
 */
class CustomBinder: Binder() {

    override fun onTransact(code: Int, data: Parcel, reply: Parcel?, flags: Int): Boolean {
        return super.onTransact(code, data, reply, flags)
    }


}