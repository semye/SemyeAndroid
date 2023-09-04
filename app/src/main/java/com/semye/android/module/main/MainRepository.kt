package com.semye.android.module.main

import com.semye.android.module.main.datasource.MainLocalDataSource
import javax.inject.Inject


/**
 *  Created by yesheng on 2020/11/5
 *  请求网络或者查询数据库获取数据
 */
class MainRepository @Inject constructor(private val localDataSource: MainLocalDataSource) {

    fun requestListData(): List<Pair<String, Class<*>>> {
        return localDataSource.requestListData()
    }
}