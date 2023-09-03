package com.semye.android.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

/**
 * ViewModel负责为(Activity/Fragment)准备和管理数据
 * 它还处理(Activity/Fragment)与应用程序的其余部分的通信
 *(例如调用业务逻辑类)。
 */
class MainViewModel() : ViewModel() {


    private val mainRepository = MainRepository()

    val mList: MutableLiveData<List<Pair<String, Class<*>>>> =
        MutableLiveData<List<Pair<String, Class<*>>>>()


    fun requestListData() {
        mList.value = mainRepository.requestListData()
        println(mainRepository.toString())
    }

    override fun onCleared() {
        super.onCleared()
        println("onCleared")
    }
}