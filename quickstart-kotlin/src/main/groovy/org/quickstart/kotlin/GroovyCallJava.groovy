package org.quickstart.kotlin
/**
 * @description TODO*
 * @author youngzil@163.com
 * @createTime 2019/11/2 11:19
 */
class GroovyCallJava {

    static void main(args) {
        JavaBean javaBean = new JavaBean("Groovy call JavaBean")
        println javaBean.getName()  //  JavaBean
        println javaBean.calc(2, 3) //  5
        JavaBean.hello(javaBean)    //  Hello, this is JavaBean
    }
}
