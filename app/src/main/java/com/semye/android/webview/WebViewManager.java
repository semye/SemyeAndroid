package com.semye.android.webview;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.ClientCertRequest;
import android.webkit.ConsoleMessage;
import android.webkit.GeolocationPermissions;
import android.webkit.HttpAuthHandler;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.PermissionRequest;
import android.webkit.RenderProcessGoneDetail;
import android.webkit.SafeBrowsingResponse;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebStorage;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;


/**
 * Created by yesheng on 2019/1/29
 */
public class WebViewManager {

    private WebView webView;

    private PageStatusListener pageStatusListener;

    private ProgressBarListener progressBarListener;

    public WebViewManager(WebView webView) {
        this.webView = webView;
    }

    @SuppressLint("SetJavaScriptEnabled")
    public void init() {
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setSupportZoom(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setDisplayZoomControls(false);
        webSettings.setAllowFileAccess(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        webSettings.setAppCacheEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setAppCacheMaxSize(1024 * 1024 * 10);// 设置缓冲大小
        webSettings.setDatabaseEnabled(true);
        webSettings.setGeolocationEnabled(true);
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSettings.setAllowContentAccess(true);
        webSettings.setPluginState(WebSettings.PluginState.ON);
        //使用https时 如果网页中的图片路径是http的会不显示
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        setWebViewClient(new BaseWebViewClient());
        setWebChromeClient(new BaseWebChromeClient());
    }

    public void setWebViewClient(WebViewClient webViewClient) {
        if (webView != null) {
            webView.setWebViewClient(webViewClient);
        }
    }

    public void setWebChromeClient(WebChromeClient chromeClient) {
        if (webView != null) {
            webView.setWebChromeClient(chromeClient);
        }
    }


    public PageStatusListener getPageStatusListener() {
        return pageStatusListener;
    }

    public void setPageStatusListener(PageStatusListener pageStatusListener) {
        this.pageStatusListener = pageStatusListener;
    }

    public ProgressBarListener getProgressBarListener() {
        return progressBarListener;
    }

    public void setProgressBarListener(ProgressBarListener progressBarListener) {
        this.progressBarListener = progressBarListener;
    }

    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        return false;
    }

    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        return false;
    }


    public interface ProgressBarListener {
        void onProgressChanged(WebView view, int newProgress);
    }

    public interface PageStatusListener {

        void onPageStarted(WebView view, String url, Bitmap favicon);

        void onPageFinished(WebView view, String url);
    }

    class BaseWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return WebViewManager.this.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return WebViewManager.this.shouldOverrideUrlLoading(view, request);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            if (pageStatusListener != null) {
                pageStatusListener.onPageStarted(view, url, favicon);
            }
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            if (pageStatusListener != null) {
                pageStatusListener.onPageFinished(view, url);
            }
        }

        @Override
        public void onLoadResource(WebView view, String url) {
            super.onLoadResource(view, url);
        }

        @Override
        public void onPageCommitVisible(WebView view, String url) {
            super.onPageCommitVisible(view, url);
        }

        @Nullable
        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
            return super.shouldInterceptRequest(view, url);
        }

        @Nullable
        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
            return super.shouldInterceptRequest(view, request);
        }

        @Override
        public void onTooManyRedirects(WebView view, Message cancelMsg, Message continueMsg) {
            super.onTooManyRedirects(view, cancelMsg, continueMsg);
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
        }

        @Override
        public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
            super.onReceivedHttpError(view, request, errorResponse);
        }

        @Override
        public void onFormResubmission(WebView view, Message dontResend, Message resend) {
            super.onFormResubmission(view, dontResend, resend);
        }

        @Override
        public void doUpdateVisitedHistory(WebView view, String url, boolean isReload) {
            super.doUpdateVisitedHistory(view, url, isReload);
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            super.onReceivedSslError(view, handler, error);
        }

        @Override
        public void onReceivedClientCertRequest(WebView view, ClientCertRequest request) {
            super.onReceivedClientCertRequest(view, request);
        }

        @Override
        public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host, String realm) {
            super.onReceivedHttpAuthRequest(view, handler, host, realm);
        }

        @Override
        public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
            return super.shouldOverrideKeyEvent(view, event);
        }

        @Override
        public void onUnhandledKeyEvent(WebView view, KeyEvent event) {
            super.onUnhandledKeyEvent(view, event);
        }

        @Override
        public void onScaleChanged(WebView view, float oldScale, float newScale) {
            super.onScaleChanged(view, oldScale, newScale);
        }

        @Override
        public void onReceivedLoginRequest(WebView view, String realm, @Nullable String account, String args) {
            super.onReceivedLoginRequest(view, realm, account, args);
        }

        @Override
        public boolean onRenderProcessGone(WebView view, RenderProcessGoneDetail detail) {
            return super.onRenderProcessGone(view, detail);
        }

        @Override
        public void onSafeBrowsingHit(WebView view, WebResourceRequest request, int threatType, SafeBrowsingResponse callback) {
            super.onSafeBrowsingHit(view, request, threatType, callback);
        }
    }

    class BaseWebChromeClient extends WebChromeClient {

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            if (progressBarListener != null) {
                progressBarListener.onProgressChanged(view, newProgress);
            }
            System.out.println("=======>" + newProgress);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
        }

        @Override
        public void onReceivedIcon(WebView view, Bitmap icon) {
            super.onReceivedIcon(view, icon);
        }

        @Override
        public void onReceivedTouchIconUrl(WebView view, String url, boolean precomposed) {
            super.onReceivedTouchIconUrl(view, url, precomposed);
        }

        @Override
        public void onShowCustomView(View view, CustomViewCallback callback) {
            super.onShowCustomView(view, callback);
        }

        @Override
        public void onShowCustomView(View view, int requestedOrientation, CustomViewCallback callback) {
            super.onShowCustomView(view, requestedOrientation, callback);
        }

        @Override
        public void onHideCustomView() {
            super.onHideCustomView();
        }

        @Override
        public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {
            return super.onCreateWindow(view, isDialog, isUserGesture, resultMsg);
        }

        @Override
        public void onRequestFocus(WebView view) {
            super.onRequestFocus(view);
        }

        @Override
        public void onCloseWindow(WebView window) {
            super.onCloseWindow(window);
        }

        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            return super.onJsAlert(view, url, message, result);
        }

        @Override
        public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
            return super.onJsConfirm(view, url, message, result);
        }

        @Override
        public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
            return super.onJsPrompt(view, url, message, defaultValue, result);
        }

        @Override
        public boolean onJsBeforeUnload(WebView view, String url, String message, JsResult result) {
            return super.onJsBeforeUnload(view, url, message, result);
        }

        @Override
        public void onExceededDatabaseQuota(String url, String databaseIdentifier, long quota, long estimatedDatabaseSize, long totalQuota, WebStorage.QuotaUpdater quotaUpdater) {
            super.onExceededDatabaseQuota(url, databaseIdentifier, quota, estimatedDatabaseSize, totalQuota, quotaUpdater);
        }

        @Override
        public void onReachedMaxAppCacheSize(long requiredStorage, long quota, WebStorage.QuotaUpdater quotaUpdater) {
            super.onReachedMaxAppCacheSize(requiredStorage, quota, quotaUpdater);
        }

        @Override
        public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
            super.onGeolocationPermissionsShowPrompt(origin, callback);
        }

        @Override
        public void onGeolocationPermissionsHidePrompt() {
            super.onGeolocationPermissionsHidePrompt();
        }

        @Override
        public void onPermissionRequest(PermissionRequest request) {
            super.onPermissionRequest(request);
        }

        @Override
        public void onPermissionRequestCanceled(PermissionRequest request) {
            super.onPermissionRequestCanceled(request);
        }

        @Override
        public boolean onJsTimeout() {
            return super.onJsTimeout();
        }

        @Override
        public void onConsoleMessage(String message, int lineNumber, String sourceID) {
            super.onConsoleMessage(message, lineNumber, sourceID);
        }

        @Override
        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            return super.onConsoleMessage(consoleMessage);
        }

        @Nullable
        @Override
        public Bitmap getDefaultVideoPoster() {
            return super.getDefaultVideoPoster();
        }

        @Nullable
        @Override
        public View getVideoLoadingProgressView() {
            return super.getVideoLoadingProgressView();
        }

        @Override
        public void getVisitedHistory(ValueCallback<String[]> callback) {
            super.getVisitedHistory(callback);
        }

        @Override
        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
            return super.onShowFileChooser(webView, filePathCallback, fileChooserParams);
        }
    }
}
