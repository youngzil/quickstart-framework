package org.quickstart.javase.rate.limit.leak;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019/12/11 19:34
 */

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 漏桶
 * @author hezhuofan
 */
public class LeakBucket<T> {
  private volatile String key;
  private volatile Integer limit=3000;
  private volatile Queue<T> queue = new ArrayDeque<T>(this.limit);

  public boolean flow(T request){
    return queue.add(request);
  }

  public T leak(){
    return queue.poll();
  }

  public void setLimit(Integer limit){
    this.limit=limit;
  }

  public void setKey(String key){
    this.key=key;
  }

}