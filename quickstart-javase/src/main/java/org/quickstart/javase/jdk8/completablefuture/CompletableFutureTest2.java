/**
 * 项目名称：quickstart-javase 
 * 文件名：CompletableFutureTest.java
 * 版本信息：
 * 日期：2018年8月28日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.jdk8.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * CompletableFutureTest
 * 
 * @author：youngzil@163.com
 * @2018年8月28日 下午11:35:30
 * @since 1.0
 */
public class CompletableFutureTest2 {

    public static Integer calc(Integer para) {
        try {
            // 模拟一个长时间的执行
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        return para * para;
    }

//    CompletableFuture的异步执行：
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        final CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> calc(50));
        System.out.println(future.get());
    }

}
