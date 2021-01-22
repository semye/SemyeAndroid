package com.semye.base.kotlin

/**
 * lateinit
 */
class Car {


    lateinit var name: String

    constructor(name: String) {
        this.name = name
        println("constructor")
    }

    init {
        println("init")
    }


    fun run() {
        println(name)
    }

    //函数类型 作为参数传参
    fun test1(a: Int, b: (Int) -> Unit) {

    }


}