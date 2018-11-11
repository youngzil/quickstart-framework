/**
 * 项目名称：quickstart-concurrentlinkedhashmap 
 * 文件名：MyTread.java
 * 版本信息：
 * 日期：2018年5月21日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.concurrentlinkedhashmap;

/**
 * MyTread 
 *  
 * @author：youngzil@163.com
 * @2018年5月21日 下午11:40:33 
 * @since 1.0
 */
import java.util.Random;

import com.googlecode.concurrentlinkedhashmap.ConcurrentLinkedHashMap;

public class MyTread extends Thread {
    public static ConcurrentLinkedHashMap<Integer, DO> cache;

    public MyTread(ConcurrentLinkedHashMap<Integer, DO> cache) {
        MyTread.cache = cache;
    }

    @Override
    public void run() {
        Random r1 = new Random(10);
        while (true) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
            }

            int i = r1.nextInt(10);
            // System.out.print("a");
            cache.get(i);
        }
    }
}
