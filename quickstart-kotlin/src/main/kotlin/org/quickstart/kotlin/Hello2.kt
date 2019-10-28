package org.quickstart.kotlin

/**
 * @description TODO
 *
 * @author youngzil@163.com
 * @createTime 2019/9/14 11:30
 */

class Greeter(val name: String) {
    fun greet() {
        println("Hello World, $name")
    }
}

fun main(args: Array<String>) {
    Greeter("Greeter!").greet()          // 创建一个对象不用 new 关键字
}