package org.quickstart.javase.rate.limit.token;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019/12/11 19:32
 */

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * token 生产者管理器
 * @author hezhuofan
 */
public class TokenProducerManager
{
  /**
   * 按照指定速率添加token
   * @param key 指定请求ID
   * @param period 速率
   * @param limit 桶的大小
   */
  private static TokenBucket execute(String key,Long period,Integer limit) {
    ScheduledExecutorService scheduledExecutorService= Executors.newScheduledThreadPool(1);
    TokenBucket tokenBucket = TokenBucket.builder().key(key).limit(limit).build();
    scheduledExecutorService.scheduleAtFixedRate(new TokenProducer(tokenBucket),0l,period, TimeUnit.SECONDS);
    return tokenBucket;
  }

  public static TokenBucket addTokenAtFixRate(String key,Long period,Integer limit){
    return execute(key,period,limit);
  }
}