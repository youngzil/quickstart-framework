/**
 * 项目名称：quickstart-javase 
 * 文件名：RateLimiterSample.java
 * 版本信息：
 * 日期：2017年6月27日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.guava;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.common.util.concurrent.RateLimiter;

/**
 * RateLimiterSample
 * 
 * @author：yangzl@asiainfo.com
 * @2017年6月27日 下午10:20:51
 * @version 1.0
 */
public class RateLimiterSample {

    // RateLimiter类似于JDK的信号量Semphore，他用来限制对资源并发访问的线程数。
    static RateLimiter rateLimiter = RateLimiter.create(4.0); // 每秒不超过4个任务被提交

    static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

    public static void main(String[] args) throws InterruptedException {
        // rateLimiter.acquire(); //请求RateLimiter, 超过permits会被阻塞
        // if (rateLimiter.tryAcquire()) {//非阻塞的形式来使用,未请求到limiter则立即返回false

        // create permit 是4 你第一下拿10也是可以的,只是会影响后来的获取
        while (true) {
            if (rateLimiter.tryAcquire(10)) {// 非阻塞的形式来使用,未请求到limiter则立即返回false
                // 未被限流
                System.out.println("未被限流,time=" + formatter.format(new Date()));
            } else {
                // 被限流
                System.out.println("被限流,time=" + formatter.format(new Date()));
            }

            Thread.sleep(100);
        }
    }

}
