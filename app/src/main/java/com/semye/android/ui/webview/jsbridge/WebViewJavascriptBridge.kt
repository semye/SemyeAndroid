package com.semye.android.ui.webview.jsbridge

/**
 * WebView JavaScriptBridge 接口
 * https://github.com/lzyzsd/JsBridge.git
 */
interface WebViewJavascriptBridge {
    /**
     * 向html页面发送数据
     *
     * @param data data
     */
    fun send(data: String?)

    /**
     * 向html页面发送数据并回调
     *
     * @param data             data
     * @param responseCallback 向html页面发送数据并回调
     */
    fun send(data: String?, responseCallback: CallBackFunction?)
}