/**
 * 项目名称：quickstart-javase 
 * 文件名：StringTest.java
 * 版本信息：
 * 日期：2018年3月19日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.jdk;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

/**
 * StringTest
 * 
 * @author：youngzil@163.com
 * @2018年3月19日 下午4:37:01
 * @since 1.0
 */
public class StringTest {

    public static void main(String[] args) throws InterruptedException {
        
        TimeUnit.SECONDS.sleep(1);
        
        
        String formatString = String.format("nThreads: %d (expected: > 0)", 10);
        System.out.println("formatString=" + formatString);
        
        
        String str = "123456";
        changeStr(str);//使用此函数就还是打印“123456”
//        str = "hello";//这样就打印的是“hello”
        System.out.println(str);
        
        
    }
    
    public static void changeStr(String str) {
        System.out.println(str);
        str = "welcome";
        System.out.println(str);
    }

    @Test
    public void testStringSplit(){
        String url = "redirect=openGSM&ticket=2925299881&verifySerial=null";

        int index = url.indexOf("&ticket=");
        System.out.println(index);

        String[] strings = url.split("&ticket=");
        System.out.println(strings);

    }

}
