package com.semye.android.ui.item26_viewmodel

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {

    val user: MutableLiveData<User> by lazy {
        MutableLiveData<User>()
    }

    fun request() {
        Handler(Looper.getMainLooper()).postDelayed({
            val zhangsan = User()
            zhangsan.name = "张三"
            user.postValue(zhangsan)
        }, 3000)
    }
}