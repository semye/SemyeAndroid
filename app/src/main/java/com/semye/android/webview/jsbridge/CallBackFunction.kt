package com.semye.android.webview.jsbridge

/**
 * js回调
 */
interface CallBackFunction {
    /**
     * 回调的数据
     *
     * @param data data
     */
    fun onCallBack(data: String?)
}