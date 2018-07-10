/**
 * 项目名称：quickstart-javase 
 * 文件名：ObjectsTest.java
 * 版本信息：
 * 日期：2018年3月28日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.jdk8;

import java.util.Objects;

/**
 * ObjectsTest
 * 
 * @author：yangzl@asiainfo.com
 * @2018年3月28日 下午9:19:14
 * @since 1.0
 */
public class ObjectsTest {

    public static void main(String[] args) {

        Objects.requireNonNull(null, () -> "不能为空");

    }

}
