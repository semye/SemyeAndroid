package com.semye.android.thirdparty.dragger2

import javax.inject.Inject

/**
 * Created by yesheng on 2017/3/9.
 */
class Item {
    var name: String
        private set

    // 用Inject标记构造函数,表示用它来注入到目标对象中去
    @Inject
    constructor() {
        name = "dagger"
    }

    constructor(name: String) {
        this.name = name
    }

    fun println() {
        println("=====>hello world!")
    }
}