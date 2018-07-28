/**
 * 项目名称：quickstart-yugong 
 * 文件名：Test.java
 * 版本信息：
 * 日期：2018年7月16日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.jdk;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Test 
 *  
 * @author：yangzl@asiainfo.com
 * @2018年7月16日 下午7:45:39 
 * @since 1.0
 */
public class IteratorTest {
    
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("s");
//        list.add("s2");
//        list.add("s3");
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println("aaaaaaa");
            System.out.println(iterator.next());
//            list.remove("s");
            System.out.println("bbbbbbb");
        }
    }

}
