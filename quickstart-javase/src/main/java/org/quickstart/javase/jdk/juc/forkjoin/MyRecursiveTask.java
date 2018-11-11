/**
 * 项目名称：quickstart-javase 
 * 文件名：MyRecursiveTask.java
 * 版本信息：
 * 日期：2017年7月27日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.jdk.juc.forkjoin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 * MyRecursiveTask
 * 
 * @author：youngzil@163.com
 * @2017年7月27日 下午11:31:44
 * @version 2.0
 */
public class MyRecursiveTask extends RecursiveTask<Long> {

    private long workLoad = 0;

    public MyRecursiveTask(long workLoad) {
        this.workLoad = workLoad;
    }

    @Override
    protected Long compute() {

        // if work is above threshold, break tasks up into smaller tasks
        if (this.workLoad > 16) {
            System.out.println("Splitting workLoad : " + this.workLoad);

            List<MyRecursiveTask> subtasks = new ArrayList<MyRecursiveTask>();
            subtasks.addAll(createSubtasks());

            for (MyRecursiveTask subtask : subtasks) {
                subtask.fork();
            }

            long result = 0;
            for (MyRecursiveTask subtask : subtasks) {
                result += subtask.join();
            }
            return result;

        } else {
            System.out.println("Doing workLoad myself: " + this.workLoad);
            return workLoad * 3;
        }
    }

    private List<MyRecursiveTask> createSubtasks() {
        List<MyRecursiveTask> subtasks = new ArrayList<MyRecursiveTask>();

        MyRecursiveTask subtask1 = new MyRecursiveTask(this.workLoad / 2);
        MyRecursiveTask subtask2 = new MyRecursiveTask(this.workLoad / 2);

        subtasks.add(subtask1);
        subtasks.add(subtask2);

        return subtasks;
    }
}
