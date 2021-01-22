package com.semye.base.kotlin

import com.semye.base.kotlin.delegate.Player
import com.semye.base.kotlin.delegate.PlayerDelegate

//声明变量
var v1 = 1
val v2 = 2

//Scope Functions  作用域函数
/**
 * Kotlin标准库包含几个函数，它们的唯一目的是在对象的上下文中执行代码块。
 * 当您在提供了lambda表达式的对象上调用这样的函数时，它会形成一个临时作用域。
 * 在此范围内，您可以访问不带名称的对象。这样的函数称为作用域函数。
 * 有五个:let、run、with、apply和also。
 */

//region 作用域函数


fun test1() {
    run {
        testAlso()
    }
}


/**
 * also 函数
 */
fun testAlso() {
    val result = "testAlso".also {
        val list = mutableListOf<String>()
        list.add(it)
        println("====>$list")
    }
    println("===>>$result")
}

/**
 * let 函数
 */
fun testLet() {
    val result = "testLet".let {
        it + "add let"
    }
    println("let result==>$result")
}

fun testRun() {
    val a = "helloworld".run {
        println(this)
        "123"
    }
    println(a)
}

fun testApply() {
    val aaa = "text".apply {
        println(length)
        println(this.hashCode())
    }
    println(aaa)
}

fun fortest1() {
    println("helloworld")
}

fun testWith() {
    val a = with("helloworld") {
        println(this.length)
        length
    }
    println("====>" + a)
}

/**
 * takeif 使用方法
 */
fun testTakeif() {
    mutableListOf("1", "2", "3")
        .takeIf {
            it.size == 3
        }?.run {
            println(this)
        }
}


//endregion

fun numbers() {
    listOf<String>()
    mutableListOf<String>("")
    val numbers = mutableListOf("one", "two", "three", null)
    //集合的遍历
    numbers.forEach {
        println(it)
    }
}


val map = mapOf("a" to 1, "b" to 2, "c" to 3)

fun main() {
    testTakeif()
    testLet()
    testWith()
    testApply()
    val data = User("semye", 28)
    val data2 = data.copy(name = "cai")
    println(data.toString())
    println(data2.toString())
    let()
//    val tag1 = Tag(true)
//    val tag2 = Tag(false)
//    val tag3 = Tag(true)
//    var list = listOf<Tag>(tag1, tag2, tag3)
//
//    var newList = list.filter {
//        it.name
//    }
//    print(newList)


    var result = with(null) {
        this + "abc"
        "fuck"
    }
    println(result)

    val person = Person("sss")
    println(person.toString())
    val student = Student("aaa")
    println(student.toString())
    val teacher = Teacher("bbb")
    println(teacher.toString())

    var a: String? = "hello world"
    if (a.isNullOrEmpty()) {
        println("hahah")
    }
    println("hello world!")
    ok("hello world")
    test()
    foreach()

    var mycustom = Customer("semye", "464622832@qq.com")

    println(map["a"])


    //数字
    val one: Short = 1
    val longs = 1000000000
    val oneInt: Int = 1
    val oneByte: Byte = 1

}

//定义一个函数
fun sum(a: Int, b: Int): Int {
    return a + b
}

//将表达式作为函数体、返回值类型自动推断的函数
fun sum1(a: Int, b: Int) = a + b

//无返回值的函数 Unit可以省略
fun sum2(a: Int, b: Int): Unit {

}

fun variable(zzz: Unit) {
    val a = 1//val 只能赋值一次
    var b = 2
    //val var 类型推断
}

fun ok(obj: Any) {
    if (obj is String) {
        println("====>" + obj.length)
    }
}

data class Customer(var name: String, var email: String)

//参数名带var 属性有get和set方法
class Student(var name: String) : Person(name) {
    init {
        println("学生名为$name")
    }
}

//参数名带val 属性只有get方法
class Teacher(val name: String) : Person(name) {
    init {
        println("老师名为$name")
    }
}

//参数不带任何参数
open class Person(name: String) {
    init {
        println("人名为$name")
    }
}

fun foreach() {
    val items = listOf("apple", "banana", "kiwifruit")
    //扩展函数
    items.filter { it.startsWith("a") }
        .sortedBy { it }
        .map { it.toUpperCase() }
        .forEach {
            println(it)
        }

    for (item in items) {
        println(item)
    }
}

var a: String? = null

fun test() {
    if (a?.length == 0) {
        println("true $a")
    } else {
        println("false $a")
    }
}


class Tag(var name: Boolean)


fun fuck(str: String): Any {
    println(str.length)
    return str
}


fun semye() {
    println("hello")
}

//扩展
/**
 * 扩展是静态解析的
 */

//扩展函数
fun <T> MutableList<T>.swap(index1: Int, index2: Int) {
    val tmp = this[index1]
    this[index1] = this[index2]
    this[index2] = tmp
}

//扩展属性

val <T> List<T>.lastIndex: Int
    get() = size - 1

/**
 * 数据类 提供了copy方法
 */
data class User(val name: String, val age: Int)

/**
 * 密封类 类似枚举 所有子类都必须在与密封类自身相同的文件中声明
 * 密封类是抽象的
 */
sealed class Expr

/**
 * kotlin 泛型
 */

/**
 * kotlin 委托
 */
interface Delegate {
    fun print()
}

class DelegateImpl(val x: Int) : Delegate {
    override fun print() {
        print(x)
    }
}

class Derived(delegate: Delegate) : Delegate by delegate

fun mydelegate() {
    val b = DelegateImpl(10)
    Derived(b).print()
}

/**
 * 委托属性
 */


fun let() {

    semye().let {
        println("semye")
        println(it)
    }
    println("semye2")

    "hello world".let {

    }
}

fun main2() {

    val result1 = 36.7f * 3.0f + 0.5f
    val result2 = 36.7f * 3.0f
    val result3 = 36f * 3.0f + 0.5f
    val result4 = 36f * 3.0f
    println("float===>" + result1)
    println("float===>" + result2)
    println("int===>" + result1.toInt())
    println("int===>" + result2.toInt())
    println("float===>" + result3)
    println("float===>" + result4)
    println("int===>" + result3.toInt())
    println("int===>" + result4.toInt())

    val car = Car("car")
    car.run()
    car.create()
    println(car)
    println("hello world!")
    val player = Player()
    PlayerDelegate(player).play()
}


/**
 * 扩展函数
 */
fun Car.create() {
    println("create")
}

/**
 * Sealed class
 */

/**
 * 泛型约束
 *
 */
