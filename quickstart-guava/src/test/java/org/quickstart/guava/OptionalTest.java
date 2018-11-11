/**
 * 项目名称：quickstart-guava 
 * 文件名：OptionalTest.java
 * 版本信息：
 * 日期：2018年1月3日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.guava;

import com.google.common.base.Optional;

/**
 * OptionalTest
 * 
 * @author：youngzil@163.com
 * @2018年1月3日 上午9:21:27
 * @since 1.0
 */
public class OptionalTest {

    public static void main(String[] args) {

        Optional<Integer> possible = Optional.of(5);

        possible.isPresent(); // returns true
        possible.absent();

        int value = possible.get(); // returns 5

        System.out.println(value);

    }

}
