/**
 * 项目名称：quickstart-javase 
 * 文件名：UnsafeTest.java
 * 版本信息：
 * 日期：2018年3月16日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.jdk;

import java.lang.reflect.Field;

import org.junit.Test;

import sun.misc.Unsafe;

/**
 * UnsafeTest
 * 
 * @author：youngzil@163.com
 * @2018年3月16日 上午10:00:38
 * @since 1.0
 */
@SuppressWarnings("restriction")
public class UnsafeTest {

    @Test
    public void testGetUnsafe() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        Unsafe UNSAFE = (Unsafe) theUnsafe.get(null);
        System.out.println(UNSAFE);
    }

}
