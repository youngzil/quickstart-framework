package org.quickstart.javase.rate.limit.simple;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019/12/11 19:29
 */

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 简单的速率限制器
 *
 * @author hezhuofan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bucket {

  /**
   * 指定的请求
   */
  private volatile String key;
  /**
   * 开始时间
   */
  private volatile Long start;
  /**
   * 定时时长
   */
  private volatile Long interval;
  /**
   * 当前次数
   */
  private volatile AtomicInteger count;
  /**
   * 请求次数
   */
  private volatile Integer limit;

  public static void main(String[] args) {
    Bucket bucket = Bucket.builder().key("request a").limit(5)
        .interval(2000L).build();
    bucket.request();
    bucket.request();
    bucket.request();
    bucket.request();
    bucket.request();
    bucket.request();
    bucket.request();
    bucket.request();
    bucket.request();
    bucket.request();
    bucket.request();
    bucket.request();
    bucket.request();
    bucket.request();
    bucket.request();
  }

  public boolean request() {
    if (start == null) {
      start = new Date().getTime();
    }
    if (System.currentTimeMillis() - start <= interval) {
      if (count == null) {
        count = new AtomicInteger(0);
      }
      if (count.intValue() <= limit) {
        count.incrementAndGet();
        return true;
      } else {
        System.out.println(key + "被拒绝访问");
        return false;
      }
    } else {
      start = new Date().getTime();
      return request();
    }
  }
}
