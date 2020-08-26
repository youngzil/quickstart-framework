package org.quickstart.javase.jdk.thread.pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>描述: [功能描述] </p >
 *
 * @author yangzl
 * @date 2020/8/26 10:43
 * @version v1.0
 */
public class CompletionServiceTest2 {

    public static void main(String[] args)  {
        Long start = System.currentTimeMillis();
        //开启3个线程
        ExecutorService exs = Executors.newFixedThreadPool(5);
        try {
            int taskCount = 10;
            // 结果集
            List<Integer> list = new ArrayList<Integer>();

            // 1.定义CompletionService
            CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(exs);

            // 2.添加任务
            for(int i=0;i<taskCount;i++){
                completionService.submit(new Task(i+1));
            }

            // 3.获取结果
            for(int i=0;i<taskCount;i++){
                Integer result = completionService.take().get();
                list.add(result);
            }

            System.out.println("list="+list);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭线程池
            exs.shutdown();
        }

    }

    static class Task implements Callable<Integer> {
        Integer i;

        public Task(Integer i) {
            super();
            this.i=i;
        }

        @Override
        public Integer call() throws Exception {
            if(i==5) {
                Thread.sleep(5000);
            }else{
                Thread.sleep(1000);
            }
            System.out.println("线程："+Thread.currentThread().getName()+"任务i="+i+",执行完成！");
            return i;
        }

    }

}
