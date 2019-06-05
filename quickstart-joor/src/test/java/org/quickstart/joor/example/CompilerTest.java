/**
 * 项目名称：quickstart-joor 
 * 文件名：CompilerTest.java
 * 版本信息：
 * 日期：2018年11月12日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.joor.example;

import static org.joor.Reflect.on;

import java.util.function.Supplier;

import org.joor.Reflect;
import org.quickstart.joor.example.StringProxyTest.StringProxy;

/**
 * CompilerTest 
 *  
 * @author：youngzil@163.com
 * @2018年11月12日 下午8:40:30 
 * @since 1.0
 */
public class CompilerTest {
    
    public static void main(String[] args) {
        
        Supplier<String> supplier = Reflect.compile(
                "com.example.HelloWorld",
                "package com.example;\n" +
                "class HelloWorld implements java.util.function.Supplier<String> {\n" +
                "    public String get() {\n" +
                "        return \"Hello World!\";\n" +
                "    }\n" +
                "}\n").create().get();

            // Prints "Hello World!"
            System.out.println(supplier.get());
        
    }


}
