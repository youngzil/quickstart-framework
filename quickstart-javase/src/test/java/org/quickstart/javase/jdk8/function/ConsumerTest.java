/**
 * 项目名称：quickstart-javase 
 * 文件名：ConsumerTest.java
 * 版本信息：
 * 日期：2018年8月31日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.jdk8.function;

import java.util.function.Consumer;

/**
 * ConsumerTest
 * 
 * @author：youngzil@163.com
 * @2018年8月31日 下午5:08:27
 * @since 1.0
 */
public class ConsumerTest {

    // 该接口表示接受单个输入参数并且没有返回值的操作。接口里面重要方法为：
    // void accept(T t);
    
//    主要就是 Consumer 接口没有返回值， Function 接口有返回值。

    public static void main(String[] args) throws InterruptedException {
        String name = "";
        String name1 = "12345";

        validInput(name, inputStr -> System.out.println(inputStr.isEmpty() ? "名字不能为空" : "名字正常"));
        validInput(name1, inputStr -> System.out.println(inputStr.isEmpty() ? "名字不能为空" : "名字正常"));
//        validInput(name1, inputStr -> inputStr.length() > 3 ? System.out.println("名字过长") : System.out.println("名字正常"));
    }

    public static void validInput(String name, Consumer<String> function) {
        function.accept(name);
    }

}
