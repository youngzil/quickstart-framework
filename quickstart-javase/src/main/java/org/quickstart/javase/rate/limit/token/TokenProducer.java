package org.quickstart.javase.rate.limit.token;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019/12/11 19:31
 */

/**
 * @author hezhuofan
 */
public class TokenProducer implements Runnable {

  protected TokenBucket tokenBucket;

  public TokenProducer(TokenBucket tokenBucket) {
    this.tokenBucket = tokenBucket;
  }

  @Override
  public void run() {
    tokenBucket.addToken();
  }
}