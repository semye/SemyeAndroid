package com.semye.android.jsbridge;

/**
 * WebView JavaScriptBridge 接口
 * https://github.com/lzyzsd/JsBridge.git
 */
public interface WebViewJavascriptBridge {

    /**
     * 向html页面发送数据
     *
     * @param data data
     */
    void send(String data);

    /**
     * 向html页面发送数据并回调
     *
     * @param data             data
     * @param responseCallback 向html页面发送数据并回调
     */
    void send(String data, CallBackFunction responseCallback);


}
