package org.quickstart.kotlin

/**
 * @description TODO
 *
 * @author youngzil@163.com
 * @createTime 2019/11/2 12:01
 */
class KotlinBean(val name: String) {

    fun calc(x: Int, y: Int): Int {
        return x + y
    }

    companion object {
        @JvmStatic
        fun hello(bean: KotlinBean) {
            println("Hello, this is ${bean.name}")
        }

        fun echo(msg: String, bean: KotlinBean) {
            println("$msg, this is ${bean.name}")
        }
    }
}

object KotlinUtils {
    @JvmStatic
    fun foo() {
        println("Foo...")
    }

    fun bar() {
        println("Bar...")
    }
}