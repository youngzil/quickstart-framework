/**
 * 项目名称：quickstart-guava 
 * 文件名：OptionalTest.java
 * 版本信息：
 * 日期：2018年1月3日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.guava;

import com.google.common.base.Objects;

/**
 * OptionalTest
 * 
 * @author：youngzil@163.com
 * @2018年1月3日 上午9:21:27
 * @since 1.0
 */
public class ObjectsTest {

    public static void main(String[] args) {

        Objects.equal("a", "a"); // returns true
        Objects.equal(null, "a"); // returns false
        Objects.equal("a", null); // returns false
        Objects.equal(null, null); // returns true

    }

}
