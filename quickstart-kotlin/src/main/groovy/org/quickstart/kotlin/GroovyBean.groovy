package org.quickstart.kotlin

/**
 * @description TODO*
 * @author youngzil@163.com
 * @createTime 2019/11/2 11:22
 */
class GroovyBean {

    def name

    GroovyBean(name) {
        this.name = name
    }


    def calc(x, y) {
        x + y
    }

    static def hello(GroovyBean bean) {
        println("Hello, this is ${bean.name}")
    }
}
