package org.quickstart.javase.rate.limit.leak;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019/12/11 19:35
 */
public class LeakBucketTest {

  public static void main(String[] args) throws InterruptedException {
    LeakBucket<String> leakBucket = LeakManager.leakRequestAtFixRate("request a", 1l, 2000);

    Thread.sleep(6000L);
    System.out.println(leakBucket.leak());
    System.out.println(leakBucket.leak());
    System.out.println(leakBucket.leak());
    System.out.println(leakBucket.leak());
    System.out.println(leakBucket.leak());
    System.out.println(leakBucket.leak());
    System.out.println(leakBucket.leak());
    System.out.println(leakBucket.leak());
    System.out.println(leakBucket.leak());
    System.out.println(leakBucket.leak());
    System.out.println(leakBucket.leak());
    System.out.println(leakBucket.leak());
    System.out.println(leakBucket.leak());
    System.out.println(leakBucket.leak());
    System.out.println(leakBucket.leak());
    System.out.println(leakBucket.leak());
    System.out.println(leakBucket.leak());
    System.out.println(leakBucket.leak());
    System.out.println(leakBucket.leak());
    System.out.println(leakBucket.leak());
    System.out.println(leakBucket.leak());
    System.out.println(leakBucket.leak());
    System.out.println(leakBucket.leak());
    System.out.println(leakBucket.leak());
  }

}
