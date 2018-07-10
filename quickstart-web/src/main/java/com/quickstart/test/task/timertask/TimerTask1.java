package com.quickstart.test.task.timertask;

import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimerTask1 extends TimerTask {

    private static Logger logger = LoggerFactory.getLogger(TimerTask1.class);

    private static boolean isRunning = false;

    @Override
    public void run() {
        // 获取当前类名字
        String clazzName = "com.yang.test.task.timertask.TimerTask1";

        if (!isRunning) {
            isRunning = true;
            logger.debug("【任务类】" + clazzName + "开始执行任务..."); // 开始任务
            try {
                logger.info("业务代码");

            } catch (Exception e) {
                logger.error(clazzName + "任务执行异常...");// 任务执行异常
                e.printStackTrace();
            } finally {
                logger.info("任务连接释放等操作...");
            }
            logger.debug(clazzName + "执行任务完成..."); // 任务完成
            isRunning = false;// 增加标记，标记上一次任务已经结束了
        } else {
            logger.info(clazzName + "上一次任务执行还未结束..."); // 上一次任务执行还未结束
        }
    }

}
