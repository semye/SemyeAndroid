package com.semye.android.ui.item36_webview.jsbridge

/**
 * 默认的handler处理的实现
 */
class DefaultHandler : BridgeHandler {
    var TAG = "DefaultHandler"
    override fun handler(data: String?, function: CallBackFunction?) {
        function?.onCallBack("DefaultHandler response data")
    }
}