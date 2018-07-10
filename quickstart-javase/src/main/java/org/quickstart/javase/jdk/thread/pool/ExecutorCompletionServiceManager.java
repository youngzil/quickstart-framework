/**
 * 项目名称：quickstart-javase 
 * 文件名：ExecutorCompletionServiceManager.java
 * 版本信息：
 * 日期：2018年4月27日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.jdk.thread.pool;

/**
 * ExecutorCompletionServiceManager 
 *  
 * @author：yangzl@asiainfo.com
 * @2018年4月27日 下午12:40:55 
 * @since 1.0
 */
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorCompletionServiceManager {

    public static void main(String[] args) {

        ExecutorCompletionService<String> service = new ExecutorCompletionService<String>(Executors.newCachedThreadPool());

        // 生产者
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    int index = i;
                    service.submit(new Callable<String>() {
                        @Override
                        public String call() throws Exception {
                            return "task done" + index;
                        }
                    });
                }
            }
        }.start();

        // 消费者
        new Thread() {
            @Override
            public void run() {
                try {
                    Future<String> take = service.take();
                    // do some thing........

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }
}
