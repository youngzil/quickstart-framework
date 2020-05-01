package org.quickstart.javase.rate.limit.leak;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019/12/11 19:34
 */
public class FlowTask implements Runnable{
  protected LeakBucket<String> leakBucket;
  public FlowTask(LeakBucket<String> leakBucket){
    this.leakBucket=leakBucket;
  }

  @Override
  public void run() {
    leakBucket.flow("request a");
  }
}
