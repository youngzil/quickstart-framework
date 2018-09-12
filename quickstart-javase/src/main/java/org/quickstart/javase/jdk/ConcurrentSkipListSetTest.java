/**
 * 项目名称：quickstart-javase 
 * 文件名：ConcurrentSkipListSetTest.java
 * 版本信息：
 * 日期：2018年8月29日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.jdk;

import java.util.concurrent.ConcurrentSkipListSet;

/**
 * ConcurrentSkipListSetTest 
 *  
 * @author：yangzl@asiainfo.com
 * @2018年8月29日 下午5:43:02 
 * @since 1.0
 */
public class ConcurrentSkipListSetTest {
    
    public static void main(String[] args) {
        
        ConcurrentSkipListSet<String> set = new ConcurrentSkipListSet<>();
        System.out.println(set.add("123"));
        System.out.println(set.add("124"));
        System.out.println(set.add("125"));
        System.out.println(set.add("123"));
    }

}
