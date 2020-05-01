/**
 * 项目名称：quickstart-javase 
 * 文件名：SecureRandomTest.java
 * 版本信息：
 * 日期：2018年8月28日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.jdk8;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * SecureRandomTest
 * 
 * @author：youngzil@163.com
 * @2018年8月28日 下午11:19:38
 * @since 1.0
 */
public class SecureRandomTest {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        int i = 0;
        while (i < 100) {
            System.out.println(SecureRandom.getInstanceStrong().nextLong());
            i++;
        }
        
        String algorithm = SecureRandom.getInstanceStrong().getAlgorithm();
        System.out.println("algorithm=" + algorithm);
    }

}
