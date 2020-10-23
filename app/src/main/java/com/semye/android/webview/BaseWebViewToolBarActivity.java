package com.semye.android.webview;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;

import com.semye.android.R;


/**
 * Created by yesheng on 2018/9/30.
 */
public abstract class BaseWebViewToolBarActivity extends AppCompatActivity implements ToolbarController,
        WebViewManager.ProgressBarListener,
        WebViewManager.PageStatusListener {


    public static final String LOAD_URL = "load_url";
    public static final String DOCUMENT_TITLE = "document_title";

    private BaseWebView mWebView;
    private ProgressBar mProgressBar;
    private ImageView mBack;
    private TextView mClose;
    private TextView mTitle;
    private RelativeLayout mToolbar;

    private String mUrl;
    private String mDocumentTitle;

    protected void setNavigationIcon(@DrawableRes int resourceId) {
        if (mBack != null) {
            mBack.setImageResource(resourceId);
        }
    }


    protected void setToolbarTitle(String text) {
        if (mTitle != null) {
            mTitle.setText(text);
        }
    }

    protected void setToolbarTitleColor(@ColorInt int color) {
        if (mTitle != null) {
            mTitle.setTextColor(color);
        }
    }

    protected String getToolbarTitle() {
        if (mTitle != null) {
            return mTitle.getText().toString();
        }
        return null;
    }

    @Override
    public void onBackClick(View view) {
        if (mWebView != null && mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            finish();
        }
    }

    @Override
    public void onCloseClick(View view) {
        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_toolbar);


        mBack = findViewById(R.id.back);
        mClose = findViewById(R.id.close);
        mTitle = findViewById(R.id.title);
        mWebView = findViewById(R.id.webView);
        mToolbar = findViewById(R.id.toolbar);
        mProgressBar = findViewById(R.id.progress_bar);

        WebViewManager webViewManager = new WebViewManager(mWebView);
        webViewManager.init();
        webViewManager.setProgressBarListener(this);
        webViewManager.setPageStatusListener(this);


        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackClick(v);
            }
        });

        mClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCloseClick(v);
            }
        });

        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra(DOCUMENT_TITLE)) {
                mDocumentTitle = intent.getStringExtra(DOCUMENT_TITLE);
            }
            if (intent.hasExtra(LOAD_URL)) {
                mUrl = intent.getStringExtra(LOAD_URL);
            }
        }

        mTitle.setText(mDocumentTitle);

        loadUrl(mUrl);
    }


    protected void loadUrl(String url) {
        if (mWebView != null) {
            mWebView.loadUrl(url);
        }
    }


    protected void setToolBarBackgroundColor(int resId) {
        mToolbar.setBackgroundColor(getResources().getColor(resId));
    }

    protected void setToolBarBackgroundColor(int resId, int statusBar) {
        mToolbar.setBackgroundColor(getResources().getColor(resId));
    }

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        mProgressBar.setProgress(newProgress);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        mProgressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void onPageFinished(WebView view, String url) {
        mProgressBar.setVisibility(View.GONE);
    }
}
