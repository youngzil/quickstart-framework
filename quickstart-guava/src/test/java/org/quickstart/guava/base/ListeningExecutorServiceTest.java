package org.quickstart.guava.base;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.junit.Test;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019-07-22 22:45
 */
public class ListeningExecutorServiceTest {

  @Test
  public void test() {
    ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));

    ListenableFuture explosion = service.submit(new Callable() {
      public String call() {
        return "call";
      }

      // public Explosion call() {
      // return pushBigRedButton();
      // }

    });

    Futures.addCallback(explosion, new FutureCallback() {
      // we want this handler to run immediately after we push the big red button!
      // public void onSuccess(Explosion explosion) {
      // walkAwayFrom(explosion);
      // }

      @Override
      public void onSuccess(@Nullable Object result) {
        System.out.println("Success" + result);
      }

      @Override
      public void onFailure(Throwable thrown) {
        // battleArchNemesis(); // escaped the explosion!
        System.out.println("error:" + thrown);
      }

    }, service);

  }

}
