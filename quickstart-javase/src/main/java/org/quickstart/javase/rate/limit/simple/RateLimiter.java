package org.quickstart.javase.rate.limit.simple;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019/12/11 19:30
 */

import java.util.concurrent.ConcurrentHashMap;

/**
 * 支持多个请求
 *
 * @author hezhuofan
 */
public class RateLimiter {

  private ConcurrentHashMap<String, Bucket> buckets = new ConcurrentHashMap<>(300);//支持多个请求

  public boolean getKey(String key) {
    Bucket bucket = buckets.get(key);
    if (bucket == null) {
      Bucket bucket1 = Bucket.builder().key(key).limit(12)
          .interval(2000L).build();
      bucket = bucket1;
      buckets.put(key, bucket1);
    }
    return bucket.request();
  }

  public static void main(String[] args) {
    RateLimiter rateLimiter = new RateLimiter();
    rateLimiter.getKey("request a");
    rateLimiter.getKey("request a");
    rateLimiter.getKey("request a");
    rateLimiter.getKey("request a");
    rateLimiter.getKey("request a");
    rateLimiter.getKey("request a");
    rateLimiter.getKey("request a");
    rateLimiter.getKey("request a");
    rateLimiter.getKey("request a");
    rateLimiter.getKey("request a");
    rateLimiter.getKey("request a");
    rateLimiter.getKey("request a");
    rateLimiter.getKey("request a");
    rateLimiter.getKey("request a");
  }
}
