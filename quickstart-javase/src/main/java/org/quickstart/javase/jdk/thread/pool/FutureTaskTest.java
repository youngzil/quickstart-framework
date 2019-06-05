/**
 * 项目名称：quickstart-javase 
 * 文件名：FutureTaskTest.java
 * 版本信息：
 * 日期：2018年4月27日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.jdk.thread.pool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * FutureTaskTest
 * 
 * http://www.importnew.com/25286.html
 * 
 * @author：youngzil@163.com
 * @2018年4月27日 下午12:45:29
 * @since 1.0
 */
public class FutureTaskTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        /**
         * 第一种方式:Future + ExecutorService Task task = new Task(); ExecutorService service = Executors.newCachedThreadPool(); Future<Integer> future = service.submit(task1); service.shutdown();
         */

        /**
         * 第二种方式: FutureTask + ExecutorService ExecutorService executor = Executors.newCachedThreadPool(); Task task = new Task(); FutureTask<Integer> futureTask = new FutureTask<Integer>(task);
         * executor.submit(futureTask); executor.shutdown();
         */

        /**
         * 第三种方式:FutureTask + Thread
         */

        // 2. 新建FutureTask,需要一个实现了Callable接口的类的实例作为构造函数参数
        FutureTask<Integer> futureTask = new FutureTask<Integer>(new Task());
        // 3. 新建Thread对象并启动
        Thread thread = new Thread(futureTask);
        thread.setName("Task thread");
        thread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Thread [" + Thread.currentThread().getName() + "] is running");

        // 4. 调用isDone()判断任务是否结束
        if (!futureTask.isDone()) {
            System.out.println("Task is not done");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int result = 0;
        try {
            // 5. 调用get()方法获取任务结果,如果任务没有执行完成则阻塞等待
            result = futureTask.get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("result is " + result);

    }

    // 1. 继承Callable接口,实现call()方法,泛型参数为要返回的类型
    static class Task implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            System.out.println("Thread [" + Thread.currentThread().getName() + "] is running");
            int result = 0;
            for (int i = 0; i < 100; ++i) {
                result += i;
            }

            Thread.sleep(3000);
            return result;
        }
    }
}
