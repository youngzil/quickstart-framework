package org.quickstart.javase.jdk8.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019-07-04 12:59
 */
public class BasicMain {

  public static CompletableFuture<Integer> compute() {
    final CompletableFuture<Integer> future = new CompletableFuture<>();
    return future;
  }

  public static void main(String[] args) throws Exception {

    CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
      int i = 1 / 0;
      return 100;
    });
    // future.join();
    future.get();

    final CompletableFuture<Integer> f = compute();
    class Client extends Thread {
      CompletableFuture<Integer> f;

      Client(String threadName, CompletableFuture<Integer> f) {
        super(threadName);
        this.f = f;
      }

      @Override
      public void run() {
        try {
          System.out.println(this.getName() + ": " + f.get());
        } catch (InterruptedException e) {
          e.printStackTrace();
        } catch (ExecutionException e) {
          e.printStackTrace();
        }
      }
    }
    new Client("Client1", f).start();
    new Client("Client2", f).start();
    System.out.println("waiting");
    f.complete(100);
    // f.completeExceptionally(new Exception());
    System.in.read();
  }
}
