/**
 * 项目名称：quickstart-javase 
 * 文件名：LeackBucket.java
 * 版本信息：
 * 日期：2018年11月23日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.example.limit.control;

/**
 * LeackBucket 
 *  
 * @author：youngzil@163.com
 * @2018年11月23日 下午5:24:32 
 * @since 1.0
 */
public class LeackBucket { // 漏桶
    // 起始时间
    private static long startTime = System.currentTimeMillis();
    // 流出速率 /ms
    private static long speed = 20;
    // 桶的容量
    private static long maxCount = 100;
    //
    private static long nowCount = 0;
 
    public static boolean isAccess() {
        long nowTime = System.currentTimeMillis();
        long outCount = (nowTime - startTime) * speed; // 流出数量
        startTime = nowTime; // 更新
 
        nowCount = nowCount - outCount <= 0 ? 0 : nowCount - outCount;
        if (nowCount < maxCount) {
            nowCount++;
            return true;
        } else {
            return false;
        }
    }
 
    public static void main(String[] args) {
        for (int i = 0; i < 500; i++) {
            if (isAccess()) {
                System.out.println("业务顺利执行...");
            } else {
                System.out.println("业务被丢弃---");
            }
        }
    }
}
