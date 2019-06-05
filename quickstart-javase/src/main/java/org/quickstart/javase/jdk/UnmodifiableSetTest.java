/**
 * 项目名称：quickstart-javase 
 * 文件名：UnmodifiableSetTest.java
 * 版本信息：
 * 日期：2018年3月19日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.jdk;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * UnmodifiableSetTest
 * 
 * @author：youngzil@163.com
 * @2018年3月19日 下午4:33:39
 * @since 1.0
 */
public class UnmodifiableSetTest {

    public static void main(String[] s) {
        // create set
        Set<String> set = new HashSet<String>();

        // populate the set
        set.add("Welcome");
        set.add("to");
        set.add("TP");

        System.out.println("Initial set value: " + set);

        // create unmodifiable set
        Set unmodset = Collections.unmodifiableSet(set);

        // try to modify the set
        unmodset.add("Hello");
    }

}
