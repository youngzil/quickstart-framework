/**
 * 项目名称：quickstart-javase 
 * 文件名：AtomicReferenceTest3.java
 * 版本信息：
 * 日期：2017年7月27日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.jdk.juc.atomic;

import java.util.concurrent.atomic.AtomicReference;

/**
 * AtomicReferenceTest3
 * 
 * @author：youngzil@163.com
 * @2017年7月27日 下午11:58:28
 * @version 2.0
 */
public class AtomicReferenceTest3 {
    public static void main(String[] args) {
        String initialReference = "initial value referenced";

        AtomicReference<String> atomicStringReference = new AtomicReference<String>(initialReference);

        String newReference = "new value referenced";
        boolean exchanged = atomicStringReference.compareAndSet(initialReference, newReference);
        System.out.println("exchanged: " + exchanged);

        exchanged = atomicStringReference.compareAndSet(initialReference, newReference);
        System.out.println("exchanged: " + exchanged);
    }
}
