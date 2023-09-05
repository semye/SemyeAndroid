/*
 * Copyright 2018 Semye
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *
 */
package com.semye.android.ui.item36_webview

import android.graphics.Color
import android.os.Bundle
import android.webkit.WebView
import com.semye.android.R

/**
 * Created by yesheng on 2018/9/30.
 */
class SampleWebActivity : BaseWebViewToolBarActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setToolBarBackgroundColor(R.color.colorPrimary)
        setNavigationIcon(R.drawable.icon_back_white)
        setToolbarTitleColor(Color.parseColor("#FFFFFF"))
        WebView.setWebContentsDebuggingEnabled(true)
    }
}