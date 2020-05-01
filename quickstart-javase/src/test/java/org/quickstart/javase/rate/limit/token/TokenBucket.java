package org.quickstart.javase.rate.limit.token;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019/12/11 19:31
 */

import java.util.concurrent.atomic.AtomicInteger;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 * 令牌桶
 *
 * 核心算法，每个请求去领取token，拿到token然后继续
 * @author hezhuofan
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenBucket {
  /**
   * 唯一标识
   */
  private String key;
  /**
   * 桶的大小
   */
  private Integer limit;

  /**
   * 桶当前的token
   */
  private volatile AtomicInteger tokens;

  /**
   * 加token
   */
  public void addToken(){
    if(tokens==null){
      tokens=new AtomicInteger(0);
    }
    tokens.incrementAndGet();
  }

  /**
   * 减token
   */
  public void delToken(){
    tokens.decrementAndGet();
  }


  public synchronized  boolean getToken(){
    if(tokens==null){
      tokens=new AtomicInteger(0);
    }
    if(tokens.intValue()>0){
      return  tokens.decrementAndGet()>0;
    }
    return false;
  }

  public static void main(String[] args) {

  }

}