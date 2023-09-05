package com.semye.android.module.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semye.android.base.BaseViewModel
import com.semye.android.module.main.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel负责为(Activity/Fragment)准备和管理数据
 * 它还处理(Activity/Fragment)与应用程序的其余部分的通信
 *(例如调用业务逻辑类)。
 */
@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository) :
    BaseViewModel() {

    private val _uiState = MutableStateFlow(MainUiState())

    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()


    fun requestListData() {
        viewModelScope.launch {
            val data = mainRepository.requestListData()
            _uiState.update {
                it.copy(list = data)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        println("onCleared")
    }
}