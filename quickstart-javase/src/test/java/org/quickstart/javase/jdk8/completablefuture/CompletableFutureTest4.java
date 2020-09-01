/**
 * 项目名称：quickstart-javase 文件名：CompletableFutureTest.java 版本信息： 日期：2018年8月28日 Copyright yangzl Corporation 2018 版权所有 *
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
public class CompletableFutureTest4 {
  // 组合多个CompletableFuture：

  public static Integer calc(Integer para) {
    return para / 2;
  }

  public static void main(String[] args) throws InterruptedException, ExecutionException {
    CompletableFuture<Void> fu = CompletableFuture.supplyAsync(() -> calc(50))//
        .thenCompose((i) -> CompletableFuture.supplyAsync(() -> calc(i)))//
        .thenApply((str) -> "\"" + str + "\"")//
        .thenAccept(System.out::println);
    fu.get();
  }

}
