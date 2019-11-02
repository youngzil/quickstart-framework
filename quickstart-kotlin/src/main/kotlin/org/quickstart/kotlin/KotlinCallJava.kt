package org.quickstart.kotlin

/**
 * @description TODO
 *
 * @author youngzil@163.com
 * @createTime 2019/11/2 11:58
 */
fun main(args: Array<String>) {
    val javaBean = JavaBean("JavaBean")
    println(javaBean.name)     //  JavaBean
    println(javaBean.calc(2, 3))    //  5

    JavaBean.hello(javaBean)        //  Hello, this is Peter

    //  Escaping for Java identifiers that are keywords in Kotlin
    println(javaBean.`is`("Peter")) //  true

//    这种使用方式可以让 Java 享受到 Kotlin 优雅的空值处理方式
    val list = ArrayList<JavaBean>()
    list.add(javaBean)
    val nullable: JavaBean? = list[0]
    val notNull: JavaBean = list[0]
    nullable?.name
    notNull.name
}