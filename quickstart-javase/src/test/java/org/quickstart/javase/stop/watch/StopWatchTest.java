package org.quickstart.javase.stop.watch;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.time.DurationFormatUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @description TODO
 *
 * @author yangzl
 * @createTime 2020/6/30 00:02
 */
@Slf4j
public class StopWatchTest {

    @Test
    public void testJdkSystemTime() throws InterruptedException {
        long start = System.currentTimeMillis();
        TimeUnit.SECONDS.sleep(3L);
        System.out.println("it consumes " + (System.currentTimeMillis() - start) + "ms");
        // output :  consumes 3004ms

        long start2 = System.nanoTime();
        // some code
        long finish2 = System.nanoTime();
        long timeElapsed = finish2 - start2;
        System.out.println("timeElapsed=" + timeElapsed);

        Instant start3 = Instant.now();
        // some code
        Instant finish3 = Instant.now();
        long timeElapsed3 = Duration.between(start3, finish3).toMillis();
        System.out.println("timeElapsed3=" + timeElapsed3);

    }

    @Test
    public void testCommonsStopWatch() throws InterruptedException {
        System.out.println("SLAMonitorThread.main() start");
        StopWatch sw = new StopWatch();
        sw.start();

        TimeUnit.SECONDS.sleep(1L);
        sw.split();
        System.out.println("SLAMonitorThread.main() end. split:" + sw.getSplitTime() + ", " + sw.toSplitString());

        TimeUnit.SECONDS.sleep(1L);
        sw.split();
        System.out.println("SLAMonitorThread.main() end. split:" + sw.getSplitTime() + ", " + sw.toSplitString());

        TimeUnit.SECONDS.sleep(1L);
        sw.split();
        System.out.println("SLAMonitorThread.main() end. split:" + sw.getSplitTime() + ", " + sw.toSplitString());

        TimeUnit.SECONDS.sleep(1L);
        System.out.println("SLAMonitorThread.main() end. end:" + sw.getTime() + ", " + sw.toString());

        long start = System.currentTimeMillis();
        TimeUnit.SECONDS.sleep(1L);
        System.out.println("it consumes " + (System.currentTimeMillis() - start) + "ms");
        /*
         * SLAMonitorThread.main() start
         * SLAMonitorThread.main() end. split:1005, 0:00:01.005
         * SLAMonitorThread.main() end. split:2016, 0:00:02.016
         * SLAMonitorThread.main() end. split:3021, 0:00:03.021
         * SLAMonitorThread.main() end. end:4025, 0:00:04.025
         * it consumes 1004ms
         */

    }

    @Test
    public void testGuavaStopWatch() throws InterruptedException {
        String orderNo = "12345678";

        log.info("订单 [{}] 开始处理", orderNo);
        com.google.common.base.Stopwatch stopwatch = com.google.common.base.Stopwatch.createStarted();
        TimeUnit.SECONDS.sleep(1);  // 1秒处理时间
        log.info("订单 [{}] 处理完成，耗时 [{}]", orderNo, stopwatch.stop());

        // 创建stopwatch并开始计时
        Thread.sleep(1980);

        // 以秒打印从计时开始至现在的所用时间，向下取整
        System.out.println(stopwatch.elapsed(TimeUnit.SECONDS)); // 1

        // 停止计时
        stopwatch.stop();
        System.out.println(stopwatch.elapsed(TimeUnit.SECONDS)); // 1

        // 再次计时
        stopwatch.start();
        Thread.sleep(100);
        System.out.println(stopwatch.elapsed(TimeUnit.SECONDS)); // 2

        // 重置并开始
        stopwatch.reset().start();
        Thread.sleep(1030);

        // 检查是否运行
        System.out.println(stopwatch.isRunning()); // true
        long millis = stopwatch.elapsed(TimeUnit.MILLISECONDS); // 1034
        System.out.println(millis);

        // 打印
        System.out.println(stopwatch.toString()); // 1.034 s

    }

    @Test
    public void testDurationFormatUtils() throws InterruptedException {

        Date date1 = new Date();
        date1 = DateUtils.addDays(date1, -12);

        Date date2 = new Date();
        String day = DurationFormatUtils.formatPeriod(date1.getTime(), date2.getTime(), "d");
        System.out.println(day);

        date2 = DateUtils.addYears(date2, 3);
        String day2 = DurationFormatUtils.formatPeriod(date1.getTime(), date2.getTime(), "y");
        System.out.println(day2);

        long t = new Date().getTime();
        //d、 H、 m、s、S
        String time = DurationFormatUtils.formatDuration(t, "dd'天'HH'小时'mm'分钟'ss'秒'SS'毫秒'");
        System.out.println(time);

        long t2 = new Date().getTime();
        //d、 H、 m、s、S
        String time2 = DurationFormatUtils.formatDuration(t2, "dd HH:mm:ss.SS");
        System.out.println(time2);

    }

    @Test
    public void testSpringStopWatch() throws InterruptedException {
        org.springframework.util.StopWatch sw = new org.springframework.util.StopWatch();

        sw.start("起床");
        Thread.sleep(1000);
        sw.stop();

        sw.start("洗漱");
        Thread.sleep(2000);
        sw.stop();

        sw.start("锁门");
        Thread.sleep(500);
        sw.stop();

        System.out.println(sw.prettyPrint());
        System.out.println(sw.getTotalTimeMillis());
        System.out.println(sw.getLastTaskName());
        System.out.println(sw.getLastTaskInfo());
        System.out.println(sw.getTaskCount());
    }

}
