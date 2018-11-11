/**
 * 项目名称：quickstart-javase 
 * 文件名：RecursiveTaskTest.java
 * 版本信息：
 * 日期：2018年8月31日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.jdk8;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * RecursiveTaskTest
 * 
 * @author：youngzil@163.com
 * @2018年8月31日 下午4:29:55
 * @since 1.0
 */
public class RecursiveTaskTest extends RecursiveTask<Long> {

    // 要求和的数组
    private final long[] numbers;
    // 子任务处理的数组开始和终止的位置
    private final int start;
    private final int end;
    // 不在将任务分解成子任务的阀值大小
    public static final int THRESHOLD = 10000;

    // 用于创建组任务的构造函数
    public RecursiveTaskTest(long[] numbers) {
        this(numbers, 0, numbers.length);
    }

    // 用于递归创建子任务的构造函数
    public RecursiveTaskTest(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    // 重写接口的方法
    @Override
    protected Long compute() {
        // 当前任务负责求和的部分的大小
        int length = end - start;
        // 如果小于等于阀值就顺序执行计算结果
        if (length <= THRESHOLD) {
            return computeSequentially();
        }
        // 创建子任务来为数组的前一半求和
        RecursiveTaskTest leftTask = new RecursiveTaskTest(numbers, start, start + length / 2);
        // 将子任务拆分出去，丢到ForkJoinPool线程池异步执行。
        leftTask.fork();
        // 创建子任务来为数组的后一半求和
        RecursiveTaskTest rightTask = new RecursiveTaskTest(numbers, start + length / 2, end);
        // 第二个任务直接使用当前线程计算而不再开启新的线程。
        long rightResult = rightTask.compute();
        // 读取第一个子任务的结果，如果没有完成则等待。
        long leftResult = leftTask.join();
        // 合并两个子任务的计算结果
        return rightResult + leftResult;
    }

    // 顺序执行计算的简单算法
    private long computeSequentially() {
        long sum = 0;
        for (int i = start; i < end; i++) {
            sum += numbers[i];
        }
        return sum;
    }

    // 提供给外部使用的入口方法
    public static long forkJoinSum(long n) {
        long[] numbers = LongStream.rangeClosed(1, n).toArray();
        ForkJoinTask<Long> task = new RecursiveTaskTest(numbers);
        return new ForkJoinPool().invoke(task);
    }

}
