package com.semye.base.kotlin.generics

abstract class BaseComponent<T : BaseModel> {

    abstract fun setDataModel(data: T)
}