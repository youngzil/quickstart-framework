/**
 * 项目名称：quickstart-joor 
 * 文件名：StringProxyTest.java
 * 版本信息：
 * 日期：2018年11月12日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.joor.example;
//All examples assume the following static import:
import static org.joor.Reflect.*;

import java.util.function.Supplier;

import org.joor.Reflect;
/**
 * StringProxyTest 
 *  
 * @author：youngzil@163.com
 * @2018年11月12日 下午8:36:58 
 * @since 1.0
 */
public class StringProxyTest {
    
    public interface StringProxy {
        String substring(int beginIndex);
      }

    public static void main(String[] args) {
        String substring = on("java.lang.String")
                .create("Hello Worldwww")
                .as(StringProxy.class) // Create a proxy for the wrapped object
                .substring(6);         // Call a proxy method
        
        System.out.println(substring);
        
    }

}



