package org.quickstart.guava.base;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.checkerframework.checker.nullness.qual.Nullable;

import com.google.common.base.Function;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019-07-22 22:52
 */
public class ListenerFutureChain {

  private static final ExecutorService executor = Executors.newFixedThreadPool(2);

  private static final ListeningExecutorService executorService = MoreExecutors.listeningDecorator(executor);

  public void executeChain() {

    AsyncFunction<String, String> asyncFunction = new AsyncFunction<String, String>() {

      @Override
      public ListenableFuture<String> apply(final String input) throws Exception {
        ListenableFuture<String> future = executorService.submit(new Callable<String>() {
          @Override
          public String call() throws Exception {
            System.out.println("STEP1 >>>" + Thread.currentThread().getName());
            return input + "|||step 1 ===--===||| ";
          }
        });

        return future;

      }
    };

    AsyncFunction<String, String> asyncFunction2 = new AsyncFunction<String, String>() {

      @Override
      public ListenableFuture<String> apply(final String input) throws Exception {
        ListenableFuture<String> future = executorService.submit(new Callable<String>() {
          @Override
          public String call() throws Exception {
            System.out.println("STEP2 >>>" + Thread.currentThread().getName());
            return input + "|||step 2 ===--===---||| ";
          }
        });

        return future;

      }
    };



    Function<String,String> asyncFunction11 = new Function<String, String>() {
      @Nullable
      @Override
      public String apply(@Nullable String input) {
        return input +  "---------->asyncFunction11" ;
      }
    };

    Function<String,String> asyncFunction22 = new Function<String, String>() {
      @Nullable
      @Override
      public String apply(@Nullable String input) {
        return input  + "---------->asyncFunction22" ;
      }
    };



    ListenableFuture startFuture = executorService.submit(new Callable() {
      @Override
      public Object call() throws Exception {
        System.out.println("BEGIN >>>" + Thread.currentThread().getName());
        return "BEGIN--->";
      }
    });

    // ListenableFuture future = Futures.transform(startFuture, asyncFunction, executor);
    // ListenableFuture endFuture = Futures.transform(future, asyncFunction2, executor);

    ListenableFuture future = Futures.transform(startFuture, asyncFunction11, executor);
    ListenableFuture endFuture = Futures.transform(future, asyncFunction22, executor);

    Futures.addCallback(endFuture, new FutureCallback() {
      @Override
      public void onSuccess(Object result) {
        System.out.println(result);
        System.out.println("=======OK=======");
      }

      @Override
      public void onFailure(Throwable t) {
        t.printStackTrace();

      }
    }, executor);

  }

  public static void main(String[] args) {
    System.out.println("========START=======");
    System.out.println("MAIN >>>" + Thread.currentThread().getName());
    ListenerFutureChain chain = new ListenerFutureChain();
    chain.executeChain();
    System.out.println("========END=======");



    // executor.shutdown();
    // System.exit(0);
  }

}
