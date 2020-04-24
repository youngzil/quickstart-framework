package org.quickstart.javase.jdk8.managedblocker;

import org.junit.Test;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2020/4/23 22:39
 */
public class ManagedBlockerTest {

    //    我希望ManagedBlocker使用CompletableFuture来防止ForkJoinPool.commonPool用尽，即：

    @Test
    public void testCompletableFutureWithManagedBlocker() throws ExecutionException, InterruptedException {
        final long startingTime = System.currentTimeMillis();
        final int numberOfFuture = 32;
        final CountDownLatch countDownLatch = new CountDownLatch(numberOfFuture);

        final List<CompletableFuture<Void>> futures = Stream.generate(() -> CompletableFuture.runAsync(() -> {
            countDownLatch.countDown();
            BlockingTasks.callInManagedBlock((() -> {
                try {
                    TimeUnit.SECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }));
        })).limit(numberOfFuture).collect(Collectors.toList());

        futures.forEach((future) -> {
            try {
                countDownLatch.await();
            } catch (InterruptedException ignored) {
            }
            future.join();
        });

        System.out.println("Time taken roughly: [" + (System.currentTimeMillis() - startingTime) + "]ms");
    }

}
