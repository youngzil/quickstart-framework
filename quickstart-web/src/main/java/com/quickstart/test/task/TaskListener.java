package com.quickstart.test.task;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class TaskListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent event) {
        // 初始化执行
        // 同步串号在BOSS侧的定时任务
        TimerManager.SynchImeiBossInfoTask();

    }

    public void contextDestroyed(ServletContextEvent event) {

    }

}
