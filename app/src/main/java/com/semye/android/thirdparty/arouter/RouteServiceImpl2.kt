package com.semye.android.thirdparty.arouter

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route

/**
 *  Created by yesheng on 2020/11/27
 */
@Route(path = "/service/route2")
class RouteServiceImpl2 : RouteService {
    override fun init(context: Context?) {
    }
}