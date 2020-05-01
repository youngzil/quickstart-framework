package org.quickstart.javase.rate.limit.leak;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019/12/11 19:35
 */
public class LeakManager {

  private static LeakBucket<String> execute(String key, Long period, Integer limit) {
    ScheduledExecutorService scheduledExecutorService= Executors.newScheduledThreadPool(1);
    LeakBucket<String> leakBucket=new LeakBucket<>();
    leakBucket.setLimit(limit);
    leakBucket.setKey(key);

    ScheduledExecutorService flowScheduledExecutorService= Executors.newScheduledThreadPool(1);
    flowScheduledExecutorService.scheduleAtFixedRate(new FlowTask(leakBucket),0l,period, TimeUnit.SECONDS);

    try {
      Thread.sleep(3000L);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    scheduledExecutorService.scheduleAtFixedRate(new LeakTask(leakBucket),0l,period, TimeUnit.SECONDS);
    return leakBucket;
  }

  /**
   * 按照指定速率添加token
   * @param key 指定请求ID
   * @param period 速率
   * @param limit 桶的大小
   */
  public static LeakBucket<String> leakRequestAtFixRate(String key,Long period,Integer limit){
    return execute(key,period,limit);
  }


}