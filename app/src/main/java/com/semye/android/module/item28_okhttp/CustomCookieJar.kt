package com.semye.android.module.item28_okhttp

import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl

/**
 * Created by yesheng on 2019/3/28
 * 自定义Cookie管理
 */
class CustomCookieJar : CookieJar {
    override fun saveFromResponse(
        url: HttpUrl,
        cookies: List<Cookie>
    ) {

        log(url.toString())

        log(cookies.size.toString() + "")
    }

    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        return emptyList()
    }
}