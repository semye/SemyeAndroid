package com.semye.android.ui.item36_webview.jsbridge

import android.graphics.Bitmap
import android.os.Build
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.semye.android.ui.item36_webview.jsbridge.BridgeUtil.webViewLoadLocalJs
import java.io.UnsupportedEncodingException
import java.net.URLDecoder

/**
 * Created by bruce on 10/28/15.
 * 桥接webview的webviewclient
 */
class BridgeWebViewClient(private val webView: BridgeWebView?) : WebViewClient() {
    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
        var url = url
        try {
            url = URLDecoder.decode(url, "UTF-8")
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }
        return if (url.startsWith(BridgeUtil.YY_RETURN_DATA)) { // 如果是返回数据
            webView!!.handlerReturnData(url)
            true
        } else if (url.startsWith(BridgeUtil.YY_OVERRIDE_SCHEMA)) { //
            webView!!.flushMessageQueue()
            true
        } else {
            super.shouldOverrideUrlLoading(view, url)
        }
    }

    // 增加shouldOverrideUrlLoading在api》=24时
    override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            var url = request.url.toString()
            try {
                url = URLDecoder.decode(url, "UTF-8")
            } catch (ex: UnsupportedEncodingException) {
                ex.printStackTrace()
            }
            if (url.startsWith(BridgeUtil.YY_RETURN_DATA)) { // 如果是返回数据
                webView!!.handlerReturnData(url)
                true
            } else if (url.startsWith(BridgeUtil.YY_OVERRIDE_SCHEMA)) { //
                webView!!.flushMessageQueue()
                true
            } else {
                super.shouldOverrideUrlLoading(view, request)
            }
        } else {
            super.shouldOverrideUrlLoading(view, request)
        }
    }

    override fun onPageStarted(view: WebView, url: String, favicon: Bitmap) {
        super.onPageStarted(view, url, favicon)
    }

    override fun onPageFinished(view: WebView, url: String) {
        super.onPageFinished(view, url)
        webViewLoadLocalJs(view, BridgeWebView.toLoadJs)
        if (webView != null && webView.getStartupMessage() != null) {
            for (m in webView.getStartupMessage()!!) {
                webView.dispatchMessage(m)
            }
            webView.setStartupMessage(null)
        }
    }

    override fun onReceivedError(view: WebView, errorCode: Int, description: String, failingUrl: String) {
        super.onReceivedError(view, errorCode, description, failingUrl)
    }
}