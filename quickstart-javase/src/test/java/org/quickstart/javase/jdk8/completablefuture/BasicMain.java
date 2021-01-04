package org.quickstart.javase.jdk8.completablefuture;

import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

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

    @Test
    public void testComplete() throws IOException {
        CompletableFuture<String> groupCodeResult = CompletableFuture.supplyAsync(() -> {
            System.out.println("111 start" + System.currentTimeMillis());
      /*try {
        TimeUnit.SECONDS.sleep(10);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }*/
            System.out.println("111 end" + System.currentTimeMillis());
            return "111";
        })//
            .thenApplyAsync(GroupCode -> {

                System.out.println("222 start" + System.currentTimeMillis());
          /*try {
            TimeUnit.SECONDS.sleep(10);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }*/
                System.out.println("222 end" + System.currentTimeMillis());
                return "222";

            })//

            .thenApplyAsync(GroupCode -> {

                System.out.println("333 start" + System.currentTimeMillis());
          /*try {
            TimeUnit.SECONDS.sleep(10);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }*/
                System.out.println("333 end" + System.currentTimeMillis());
                return "333";

            })//
            .thenApplyAsync(GroupCode -> {

                System.out.println("444 start" + System.currentTimeMillis());
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int a = 0;
                a = 4 / a;
                System.out.println("444 end" + System.currentTimeMillis());
                return "444";

            })//
            .handleAsync((s, t) -> {
                if (t != null) {
                    t.printStackTrace();
                }
                return s;
            });

        System.out.println("eee end" + System.currentTimeMillis());
        boolean dd = groupCodeResult.complete("complete");
        System.out.println(dd);
        System.out.println("fff end" + System.currentTimeMillis());

        System.in.read();
    }

    //如果吧thenApply换成thenApplyAsync就抓不到异常了
    @Test
    public void testExceptionComplete() throws IOException {
        CompletableFuture<String> groupCodeResult = CompletableFuture.supplyAsync(() -> {
            System.out.println("111 start" + System.currentTimeMillis());
      /*try {
        TimeUnit.SECONDS.sleep(10);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }*/
            System.out.println("111 end" + System.currentTimeMillis());
            return "111";
        })//
            .thenApplyAsync(GroupCode -> {

                System.out.println("222 start" + System.currentTimeMillis());
          /*try {
            TimeUnit.SECONDS.sleep(10);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }*/
                System.out.println("222 end" + System.currentTimeMillis());
                return "222";

            })//
            .thenApply(GroupCode -> {

                System.out.println("444 start" + System.currentTimeMillis());
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int a = 0;
                a = 4 / a;
                System.out.println("444 end" + System.currentTimeMillis());
                return "444";

            })//
            .thenApplyAsync(GroupCode -> {

                System.out.println("333 start" + System.currentTimeMillis());
          /*try {
            TimeUnit.SECONDS.sleep(10);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }*/
                System.out.println("333 end" + System.currentTimeMillis());
                return "333";

            })//
            .exceptionally(ex -> {
                System.out.println("Exce" + ex);
                return String.valueOf(false);
            });

        System.out.println("eee end" + System.currentTimeMillis());
        boolean dd = groupCodeResult.complete("complete");
        System.out.println("dd=" + dd);
        System.out.println("fff end" + System.currentTimeMillis());

        System.in.read();
    }

    ExecutorService executor = Executors.newFixedThreadPool(3);

    @Test
    public void testMethod() {
        String[] orders = {"1", "2", "3", "4", "5", "6"};
        Arrays.stream(orders)
            .forEach(id -> CompletableFuture.supplyAsync(() -> submit(id), executor).exceptionally(e -> {
                System.out.println(e);
                return false;
            }));

        executor.shutdown();
        while (!executor.isTerminated()) {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static Boolean submit(String order) {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("抛一个异常" + order);
    }

}
