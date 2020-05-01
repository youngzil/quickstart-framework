/**
 * 项目名称：quickstart-javase 
 * 文件名：ThreadPoolTest.java
 * 版本信息：
 * 日期：2017年8月20日
 * Copyright yangzl Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.jdk.thread.pool;

/**
 * ThreadPoolTest 
 *  
 * @author：youngzil@163.com
 * @2017年8月20日 上午11:05:17 
 * @since 1.0
 */

/**
 * 总结： 1、用ThreadPoolExecutor自定义线程池，看线程是的用途，如果任务量不大，可以用无界队列，如果任务量非常大，要用有界队列，防止OOM 2、如果任务量很大，还要求每个任务都处理成功，要对提交的任务进行阻塞提交，重写拒绝机制，改为阻塞提交。保证不抛弃一个任务 3、最大线程数一般设为2N+1最好，N是CPU核数
 * 4、核心线程数，看应用，如果是任务，一天跑一次，设置为0，合适，因为跑完就停掉了，如果是常用线程池，看任务量，是保留一个核心还是几个核心线程数 5、如果要获取任务执行结果，用CompletionService，但是注意，获取任务的结果的要重新开一个线程获取，如果在主线程获取，就要等任务都提交后才获取，就会阻塞大量任务结果，队列过大OOM，所以最好异步开个线程获取结果
 */
public class ThreadPoolTest {

}
