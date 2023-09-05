package com.semye.android.ui.item36_webview

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import com.semye.android.R
import com.semye.android.ui.item36_webview.WebViewManager.PageStatusListener
import com.semye.android.ui.item36_webview.WebViewManager.ProgressBarListener

/**
 * Created by yesheng on 2018/9/30.
 */
abstract class BaseWebViewToolBarActivity : AppCompatActivity(), ToolbarController, ProgressBarListener, PageStatusListener {
    private var mWebView: BaseWebView? = null
    private var mProgressBar: ProgressBar? = null
    private var mBack: ImageView? = null
    private var mClose: TextView? = null
    private var mTitle: TextView? = null
    private var mToolbar: RelativeLayout? = null
    private var mUrl: String? = null
    private var mDocumentTitle: String? = null
    protected fun setNavigationIcon(@DrawableRes resourceId: Int) {
        if (mBack != null) {
            mBack!!.setImageResource(resourceId)
        }
    }

    protected fun setToolbarTitleColor(@ColorInt color: Int) {
        if (mTitle != null) {
            mTitle!!.setTextColor(color)
        }
    }

    protected var toolbarTitle: String?
        protected get() = if (mTitle != null) {
            mTitle!!.text.toString()
        } else null
        protected set(text) {
            if (mTitle != null) {
                mTitle!!.text = text
            }
        }

    override fun onBackClick(view: View?) {
        if (mWebView != null && mWebView!!.canGoBack()) {
            mWebView!!.goBack()
        } else {
            finish()
        }
    }

    override fun onCloseClick(view: View?) {
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview_toolbar)
        mBack = findViewById(R.id.back)
        mClose = findViewById(R.id.close)
        mTitle = findViewById(R.id.title)
        mWebView = findViewById(R.id.webView)
        mToolbar = findViewById(R.id.toolbar)
        mProgressBar = findViewById(R.id.progress_bar)
        val webViewManager = WebViewManager(mWebView)
        webViewManager.init()
        webViewManager.progressBarListener = this
        webViewManager.pageStatusListener = this
        mBack?.setOnClickListener(View.OnClickListener { v -> onBackClick(v) })
        mClose?.setOnClickListener(View.OnClickListener { v -> onCloseClick(v) })
        val intent = intent
        if (intent != null) {
            if (intent.hasExtra(DOCUMENT_TITLE)) {
                mDocumentTitle = intent.getStringExtra(DOCUMENT_TITLE)
            }
            if (intent.hasExtra(LOAD_URL)) {
                mUrl = intent.getStringExtra(LOAD_URL)
            }
        }
        mTitle?.setText(mDocumentTitle)
        loadUrl(mUrl)
    }

    protected fun loadUrl(url: String?) {
        if (mWebView != null) {
            mWebView!!.loadUrl(url!!)
        }
    }

    protected fun setToolBarBackgroundColor(resId: Int) {
        mToolbar!!.setBackgroundColor(resources.getColor(resId))
    }

    protected fun setToolBarBackgroundColor(resId: Int, statusBar: Int) {
        mToolbar!!.setBackgroundColor(resources.getColor(resId))
    }

    override fun onProgressChanged(view: WebView?, newProgress: Int) {
        mProgressBar!!.progress = newProgress
    }

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        mProgressBar!!.visibility = View.VISIBLE
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        mProgressBar!!.visibility = View.GONE
    }

    companion object {
        const val LOAD_URL = "load_url"
        const val DOCUMENT_TITLE = "document_title"
    }
}