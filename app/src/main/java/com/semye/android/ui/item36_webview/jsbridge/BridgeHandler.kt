package com.semye.android.ui.item36_webview.jsbridge

/**
 * bridge handler 接口
 */
interface BridgeHandler {
    /**
     * 处理数据
     *
     * @param data     数据
     * @param function 回调函数
     */
    fun handler(data: String?, function: CallBackFunction?)
}