package com.semye.android.ui.webview

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.net.Uri
import android.net.http.SslError
import android.os.Build
import android.os.Message
import android.view.KeyEvent
import android.view.View
import android.webkit.*
import android.webkit.WebStorage.QuotaUpdater

/**
 * Created by yesheng on 2019/1/29
 */
class WebViewManager(private val webView: WebView?) {
    var pageStatusListener: PageStatusListener? = null
    var progressBarListener: ProgressBarListener? = null
    @SuppressLint("SetJavaScriptEnabled")
    fun init() {
        val webSettings = webView!!.settings
        webSettings.javaScriptEnabled = true
        webSettings.useWideViewPort = true
        webSettings.setSupportZoom(true)
        webSettings.loadWithOverviewMode = true
        webSettings.javaScriptCanOpenWindowsAutomatically = true
        webSettings.displayZoomControls = false
        webSettings.allowFileAccess = true
        webSettings.builtInZoomControls = true
        webSettings.domStorageEnabled = true
        webSettings.layoutAlgorithm = WebSettings.LayoutAlgorithm.NORMAL
//        webSettings.setAppCacheEnabled(true)
        webSettings.cacheMode = WebSettings.LOAD_DEFAULT
//        webSettings.setAppCacheMaxSize((1024 * 1024 * 10).toLong()) // 设置缓冲大小
        webSettings.databaseEnabled = true
        webSettings.setGeolocationEnabled(true)
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH)
        webSettings.allowContentAccess = true
        webSettings.pluginState = WebSettings.PluginState.ON
        //使用https时 如果网页中的图片路径是http的会不显示
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }
        setWebViewClient(BaseWebViewClient())
        setWebChromeClient(BaseWebChromeClient())
    }

    fun setWebViewClient(webViewClient: WebViewClient?) {
        if (webView != null) {
            webView.webViewClient = webViewClient!!
        }
    }

    fun setWebChromeClient(chromeClient: WebChromeClient?) {
        if (webView != null) {
            webView.webChromeClient = chromeClient
        }
    }

    fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
        return false
    }

    fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        return false
    }

    interface ProgressBarListener {
        fun onProgressChanged(view: WebView?, newProgress: Int)
    }

    interface PageStatusListener {
        fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?)
        fun onPageFinished(view: WebView?, url: String?)
    }

    internal inner class BaseWebViewClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            return this@WebViewManager.shouldOverrideUrlLoading(view, url)
        }

        override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
            return this@WebViewManager.shouldOverrideUrlLoading(view, request)
        }

        override fun onPageStarted(view: WebView, url: String, favicon: Bitmap) {
            super.onPageStarted(view, url, favicon)
            if (pageStatusListener != null) {
                pageStatusListener!!.onPageStarted(view, url, favicon)
            }
        }

        override fun onPageFinished(view: WebView, url: String) {
            super.onPageFinished(view, url)
            if (pageStatusListener != null) {
                pageStatusListener!!.onPageFinished(view, url)
            }
        }

        override fun onLoadResource(view: WebView, url: String) {
            super.onLoadResource(view, url)
        }

        override fun onPageCommitVisible(view: WebView, url: String) {
            super.onPageCommitVisible(view, url)
        }

        override fun shouldInterceptRequest(view: WebView, url: String): WebResourceResponse? {
            return super.shouldInterceptRequest(view, url)
        }

        override fun shouldInterceptRequest(view: WebView, request: WebResourceRequest): WebResourceResponse? {
            return super.shouldInterceptRequest(view, request)
        }

        override fun onTooManyRedirects(view: WebView, cancelMsg: Message, continueMsg: Message) {
            super.onTooManyRedirects(view, cancelMsg, continueMsg)
        }

        override fun onReceivedError(view: WebView, errorCode: Int, description: String, failingUrl: String) {
            super.onReceivedError(view, errorCode, description, failingUrl)
        }

        override fun onReceivedError(view: WebView, request: WebResourceRequest, error: WebResourceError) {
            super.onReceivedError(view, request, error)
        }

        override fun onReceivedHttpError(view: WebView, request: WebResourceRequest, errorResponse: WebResourceResponse) {
            super.onReceivedHttpError(view, request, errorResponse)
        }

        override fun onFormResubmission(view: WebView, dontResend: Message, resend: Message) {
            super.onFormResubmission(view, dontResend, resend)
        }

        override fun doUpdateVisitedHistory(view: WebView, url: String, isReload: Boolean) {
            super.doUpdateVisitedHistory(view, url, isReload)
        }

        override fun onReceivedSslError(view: WebView, handler: SslErrorHandler, error: SslError) {
            super.onReceivedSslError(view, handler, error)
        }

        override fun onReceivedClientCertRequest(view: WebView, request: ClientCertRequest) {
            super.onReceivedClientCertRequest(view, request)
        }

        override fun onReceivedHttpAuthRequest(view: WebView, handler: HttpAuthHandler, host: String, realm: String) {
            super.onReceivedHttpAuthRequest(view, handler, host, realm)
        }

        override fun shouldOverrideKeyEvent(view: WebView, event: KeyEvent): Boolean {
            return super.shouldOverrideKeyEvent(view, event)
        }

        override fun onUnhandledKeyEvent(view: WebView, event: KeyEvent) {
            super.onUnhandledKeyEvent(view, event)
        }

        override fun onScaleChanged(view: WebView, oldScale: Float, newScale: Float) {
            super.onScaleChanged(view, oldScale, newScale)
        }

        override fun onReceivedLoginRequest(view: WebView, realm: String, account: String?, args: String) {
            super.onReceivedLoginRequest(view, realm, account, args)
        }

        override fun onRenderProcessGone(view: WebView, detail: RenderProcessGoneDetail): Boolean {
            return super.onRenderProcessGone(view, detail)
        }

        override fun onSafeBrowsingHit(view: WebView, request: WebResourceRequest, threatType: Int, callback: SafeBrowsingResponse) {
            super.onSafeBrowsingHit(view, request, threatType, callback)
        }
    }

    internal inner class BaseWebChromeClient : WebChromeClient() {
        override fun onProgressChanged(view: WebView, newProgress: Int) {
            super.onProgressChanged(view, newProgress)
            if (progressBarListener != null) {
                progressBarListener!!.onProgressChanged(view, newProgress)
            }
            println("=======>$newProgress")
        }

        override fun onReceivedTitle(view: WebView, title: String) {
            super.onReceivedTitle(view, title)
        }

        override fun onReceivedIcon(view: WebView, icon: Bitmap) {
            super.onReceivedIcon(view, icon)
        }

        override fun onReceivedTouchIconUrl(view: WebView, url: String, precomposed: Boolean) {
            super.onReceivedTouchIconUrl(view, url, precomposed)
        }

        override fun onShowCustomView(view: View, callback: CustomViewCallback) {
            super.onShowCustomView(view, callback)
        }

        override fun onShowCustomView(view: View, requestedOrientation: Int, callback: CustomViewCallback) {
            super.onShowCustomView(view, requestedOrientation, callback)
        }

        override fun onHideCustomView() {
            super.onHideCustomView()
        }

        override fun onCreateWindow(view: WebView, isDialog: Boolean, isUserGesture: Boolean, resultMsg: Message): Boolean {
            return super.onCreateWindow(view, isDialog, isUserGesture, resultMsg)
        }

        override fun onRequestFocus(view: WebView) {
            super.onRequestFocus(view)
        }

        override fun onCloseWindow(window: WebView) {
            super.onCloseWindow(window)
        }

        override fun onJsAlert(view: WebView, url: String, message: String, result: JsResult): Boolean {
            return super.onJsAlert(view, url, message, result)
        }

        override fun onJsConfirm(view: WebView, url: String, message: String, result: JsResult): Boolean {
            return super.onJsConfirm(view, url, message, result)
        }

        override fun onJsPrompt(view: WebView, url: String, message: String, defaultValue: String, result: JsPromptResult): Boolean {
            return super.onJsPrompt(view, url, message, defaultValue, result)
        }

        override fun onJsBeforeUnload(view: WebView, url: String, message: String, result: JsResult): Boolean {
            return super.onJsBeforeUnload(view, url, message, result)
        }

        override fun onExceededDatabaseQuota(url: String, databaseIdentifier: String, quota: Long, estimatedDatabaseSize: Long, totalQuota: Long, quotaUpdater: QuotaUpdater) {
            super.onExceededDatabaseQuota(url, databaseIdentifier, quota, estimatedDatabaseSize, totalQuota, quotaUpdater)
        }

        override fun onGeolocationPermissionsShowPrompt(origin: String, callback: GeolocationPermissions.Callback) {
            super.onGeolocationPermissionsShowPrompt(origin, callback)
        }

        override fun onGeolocationPermissionsHidePrompt() {
            super.onGeolocationPermissionsHidePrompt()
        }

        override fun onPermissionRequest(request: PermissionRequest) {
            super.onPermissionRequest(request)
        }

        override fun onPermissionRequestCanceled(request: PermissionRequest) {
            super.onPermissionRequestCanceled(request)
        }

        override fun onJsTimeout(): Boolean {
            return super.onJsTimeout()
        }

        override fun onConsoleMessage(message: String, lineNumber: Int, sourceID: String) {
            super.onConsoleMessage(message, lineNumber, sourceID)
        }

        override fun onConsoleMessage(consoleMessage: ConsoleMessage): Boolean {
            return super.onConsoleMessage(consoleMessage)
        }

        override fun getDefaultVideoPoster(): Bitmap? {
            return super.getDefaultVideoPoster()
        }

        override fun getVideoLoadingProgressView(): View? {
            return super.getVideoLoadingProgressView()
        }

        override fun getVisitedHistory(callback: ValueCallback<Array<String>>) {
            super.getVisitedHistory(callback)
        }

        override fun onShowFileChooser(webView: WebView, filePathCallback: ValueCallback<Array<Uri>>, fileChooserParams: FileChooserParams): Boolean {
            return super.onShowFileChooser(webView, filePathCallback, fileChooserParams)
        }
    }
}