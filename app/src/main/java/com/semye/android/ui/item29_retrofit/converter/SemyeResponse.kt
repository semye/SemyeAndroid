package com.semye.android.ui.item29_retrofit.converter

class SemyeResponse<T> {
    var code: Int = 0
    var message: String = "ok"
    var data: T? = null
}