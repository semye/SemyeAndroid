package com.semye.android.jetpack.viewmodel

import android.os.Handler
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {

    var user: MutableLiveData<User> = MutableLiveData()

    fun request() {
        Handler().postDelayed({
            val zhangsan = User()
            zhangsan.name = "张三"
            user.postValue(zhangsan)
        }, 3000)
    }
}