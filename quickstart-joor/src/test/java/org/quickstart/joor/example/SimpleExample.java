/**
 * 项目名称：quickstart-joor 
 * 文件名：SimpleExample.java
 * 版本信息：
 * 日期：2018年11月12日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.joor.example;
//All examples assume the following static import:
import static org.joor.Reflect.*;
/**
 * SimpleExample 
 *  
 * @author：youngzil@163.com
 * @2018年11月12日 下午8:33:57 
 * @since 1.0
 */
public class SimpleExample {
    
    public static void main(String[] args) {
        String world = on("java.lang.String")  // Like Class.forName()
                .create("Hello World") // Call most specific matching constructor
                .call("substring", 6)  // Call most specific matching substring() method
                .call("toString")      // Call toString()
                .get();                // Get the wrapped object, in this case a String
        
        System.out.println(world);
    }

}
