/**
 * 项目名称：quickstart-example 
 * 文件名：ConcurrentMapWithMap.java
 * 版本信息：
 * 日期：2017年3月7日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.example.map;

/**
 * ConcurrentMapWithMap 
 *  
 * @author：yangzl@asiainfo.com
 * @2017年3月7日 上午11:55:24 
 * @version 1.0
 */
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;

import org.apache.log4j.Logger;

public class ConcurrentMapWithMap {

    private static Map<String, Long> mapWordCounts = new HashMap<>();
    private static ConcurrentMap<String, Long> concurrentMapWordCounts = new ConcurrentHashMap<>();
    public static Logger logger = Logger.getLogger(ConcurrentMapWithMap.class);
    public static int count = 0;

    public static long mapIncrease(String word) {
        Long oldValue = mapWordCounts.get(word);
        Long newValue = (oldValue == null) ? 1L : oldValue + 1;
        mapWordCounts.put(word, newValue);
        return newValue;
    }

    public static long ConcurrentMapIncrease(String word) {
        Long oldValue, newValue;
        while (true) {
            oldValue = concurrentMapWordCounts.get(word);
            if (oldValue == null) {
                // Add the word firstly, initial the value as 1
                newValue = 1L;
                if (concurrentMapWordCounts.putIfAbsent(word, newValue) == null) {
                    break;
                }
            } else {
                newValue = oldValue + 1;
                if (concurrentMapWordCounts.replace(word, oldValue, newValue)) {
                    break;
                }
            }
        }
        return newValue;
    }

    public static void mapWordCount() throws InterruptedException, ExecutionException {
        new Thread(new Runnable() {
            public void run() {
                int count = 0;
                while (count++ < 10000)
                    logger.info("mapIncrease num is " + ConcurrentMapWithMap.mapIncrease("work"));
            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                int count = 0;
                while (count++ < 10000)
                    logger.info("mapIncrease num is " + ConcurrentMapWithMap.mapIncrease("work"));
            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                int count = 0;
                while (count++ < 10000)
                    logger.info("mapIncrease num is " + ConcurrentMapWithMap.mapIncrease("work"));
            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                int count = 0;
                while (count++ < 10000)
                    logger.info("mapIncrease num is " + ConcurrentMapWithMap.mapIncrease("work"));
            }
        }).start();
    }

    public static void concurrentWordCount() throws InterruptedException, ExecutionException {
        new Thread(new Runnable() {
            public void run() {
                int count = 0;
                while (count++ < 10000)
                    logger.info("mapIncrease num is " + ConcurrentMapWithMap.ConcurrentMapIncrease("work"));
            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                int count = 0;
                while (count++ < 10000)
                    logger.info("mapIncrease num is " + ConcurrentMapWithMap.ConcurrentMapIncrease("work"));
            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                int count = 0;
                while (count++ < 10000)
                    logger.info("mapIncrease num is " + ConcurrentMapWithMap.ConcurrentMapIncrease("work"));
            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                int count = 0;
                while (count++ < 10000)
                    logger.info("mapIncrease num is " + ConcurrentMapWithMap.ConcurrentMapIncrease("work"));
            }
        }).start();
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ConcurrentMapWithMap.mapWordCount();
        Thread.sleep(10000);
        logger.info("final count map" + ConcurrentMapWithMap.mapWordCounts.get("work"));// <span style="color:#ff0000;">多线程累加，每次都少于40000，故线程不安全</span>
        System.out.println("final count map" + ConcurrentMapWithMap.mapWordCounts.get("work"));
        ConcurrentMapWithMap.concurrentWordCount();
        Thread.sleep(10000);
        logger.info("final count concurrentMap" + ConcurrentMapWithMap.concurrentMapWordCounts.get("work"));// <span
        System.out.println("final count concurrentMap" + ConcurrentMapWithMap.concurrentMapWordCounts.get("work")); // style="color:#ff0000;">多线程累加，每次都是40000</span>
    }

}
