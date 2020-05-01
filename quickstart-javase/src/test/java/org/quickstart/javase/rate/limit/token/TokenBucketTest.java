package org.quickstart.javase.rate.limit.token;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019/12/11 19:33
 */
public class TokenBucketTest {

  public static void main(String[] args) throws InterruptedException {
    TokenBucket tokenBucket = TokenProducerManager.addTokenAtFixRate("request a", 1l, 2000);

    Thread.sleep(6000L);
    System.out.println(tokenBucket.getToken());
    System.out.println(tokenBucket.getToken());
    System.out.println(tokenBucket.getToken());
    System.out.println(tokenBucket.getToken());
    System.out.println(tokenBucket.getToken());
    System.out.println(tokenBucket.getToken());
    System.out.println(tokenBucket.getToken());
    System.out.println(tokenBucket.getToken());
    System.out.println(tokenBucket.getToken());
    System.out.println(tokenBucket.getToken());
    System.out.println(tokenBucket.getToken());
  }


}
