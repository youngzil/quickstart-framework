package com.quickstart.test.task;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

import com.quickstart.test.task.timertask.TimerTask1;

public class TimerManager {

    // 时间间隔，单位ms，周期为24小时
    private static final long PERIOD_TIME = 24 * 60 * 60 * 1000;

    public TimerManager() {

    }

    /**
     * 同步串号在BOSS侧的定时任务
     * 
     * @throws Exception
     * @throws NumberFormatException
     * 
     * @author：youngzil@163.com @2015-6-20 下午03:37:12
     */
    public static void SynchImeiBossInfoTask() {

        long period_time = PERIOD_TIME;
        int hour = 3;
        int minute = 30;
        int second = 0;

        Calendar calendar = Calendar.getInstance();
        /*** 定制每日什么时间执行方法 ***/
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        Date date = calendar.getTime(); // 第一次执行定时任务的时间

        // 如果第一次执行定时任务的时间 小于 当前的时间
        // 此时要在 第一次执行定时任务的时间 加一天，以便此任务在下个时间点执行。如果不加一天，任务会立即执行。
        if (date.before(new Date())) {
            date = addDay(date, 1);
        }
        Timer imeiTimer = new Timer("同步BOSS侧IMEI信息", true);
        // 安排指定的任务在指定的时间开始进行重复的固定延迟执行。
        imeiTimer.schedule(new TimerTask1(), date, period_time);
    }

    /**
     * 在date时间基础上增加或减少天数
     * 
     * @author：youngzil@163.com @2015-6-20 下午03:37:12
     */
    public static Date addDay(Date date, int num) {
        Calendar startDT = Calendar.getInstance();
        startDT.setTime(date);
        startDT.add(Calendar.DAY_OF_MONTH, num);
        return startDT.getTime();
    }

    /**
     * 在date时间基础上增加或减少分钟数
     * 
     * @author：youngzil@163.com @2015-6-20 下午03:37:12
     */
    public static Date addMinute(Date date, int num) {
        Calendar startDT = Calendar.getInstance();
        startDT.setTime(date);
        startDT.add(Calendar.MINUTE, num);
        return startDT.getTime();
    }
}
