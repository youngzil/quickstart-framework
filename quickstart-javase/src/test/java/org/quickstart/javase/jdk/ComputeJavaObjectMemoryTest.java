package org.quickstart.javase.jdk;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019/11/21 00:00
 */
public class ComputeJavaObjectMemoryTest {

  public static void main(String[] args) {

    Object obj = new Object();

    // 计算指定对象及其引用树上的所有对象的综合大小，单位字节
    // long size = RamUsageEstimator.sizeOf(obj);

    // 计算指定对象本身在堆空间的大小，单位字节
    long size2 = org.apache.lucene.util.RamUsageEstimator.shallowSizeOf(obj);

    // 计算指定对象及其引用树上的所有对象的综合大小，返回可读的结果，如：2KB
    // String size3 = RamUsageEstimator.humanSizeOf(obj);

    long size5 = com.carrotsearch.sizeof.RamUsageEstimator.shallowSizeOf(obj);

  }

}
