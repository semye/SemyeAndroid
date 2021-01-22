package com.semye.base.kotlin.generics


/**
 * 工具人二号
 */
class Token<T> {

}

interface Callback<out T> {
    fun create(): T
}

//型变
//型变注解 out in

/**
 * 1.声明处型变 declaration-site variance
 *   out是作为返回值 in是作为参数
 * 2.类型投影 type projections
 *   星投影
 */
fun main() {
    val aModel = AModel()
    val baseComponent = when (1) {
        1 -> AComponent()
        2 -> BComponent()
        else -> null
    }
//    baseComponent?.setDataModel(aModel)
}