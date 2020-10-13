package org.quickstart.javase.jdk8.completablefuture;

import org.junit.Test;

import java.sql.Time;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019-07-04 13:55
 */
public class Main {
    private static Random rand = new Random();
    private static long t = System.currentTimeMillis();

    static int getMoreData() {
        System.out.println("begin to start compute");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("end to start compute. passed " + (System.currentTimeMillis() - t) / 1000 + " seconds");
        return rand.nextInt(1000);
    }

    public static void main(String[] args) throws Exception {

        // 不以Async结尾的方法由原来的线程计算，以Async结尾的方法由默认的线程池ForkJoinPool.commonPool()或者指定的线程池executor运行。

        // 1、创建CompletableFuture对象。
        // public static CompletableFuture<Void> runAsync(Runnable runnable)
        // public static CompletableFuture<Void> runAsync(Runnable runnable, Executor executor)
        // public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier)
        // public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier, Executor executor)

        CompletableFuture.completedFuture(10000).get();

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(Main::getMoreData);

        // 2、计算结果完成时的处理

        // public CompletableFuture<T> whenComplete(BiConsumer<? super T,? super Throwable> action)
        // public CompletableFuture<T> whenCompleteAsync(BiConsumer<? super T,? super Throwable> action)
        // public CompletableFuture<T> whenCompleteAsync(BiConsumer<? super T,? super Throwable> action, Executor executor)
        // public CompletableFuture<T> exceptionally(Function<Throwable,? extends T> fn)

        Future<Integer> f = future.whenComplete((v, e) -> {
            System.out.println("value=" + v);
            System.out.println("exception=" + e);
        });

        System.out.println(f.get());

        System.out.println("common end=========");

        // 3、对计算结果进行转换handleXXX 和 thenApplyXXX

        // 当原先的CompletableFuture的值计算完成或者抛出异常的时候，会触发这个CompletableFuture对象的计算，结果由BiFunction参数计算而得。因此这组方法兼有whenComplete和转换的两个功能。
        // public <U> CompletableFuture<U> handle(BiFunction<? super T,Throwable,? extends U> fn)
        // public <U> CompletableFuture<U> handleAsync(BiFunction<? super T,Throwable,? extends U> fn)
        // public <U> CompletableFuture<U> handleAsync(BiFunction<? super T,Throwable,? extends U> fn, Executor executor)

        // 转换:这一组函数的功能是当原来的CompletableFuture计算完后，将结果传递给函数fn，将fn的结果作为新的CompletableFuture计算结果。因此它的功能相当于将CompletableFuture<T>转换成CompletableFuture<U>。
        // public <U> CompletableFuture<U> thenApply(Function<? super T,? extends U> fn)
        // public <U> CompletableFuture<U> thenApplyAsync(Function<? super T,? extends U> fn)
        // public <U> CompletableFuture<U> thenApplyAsync(Function<? super T,? extends U> fn, Executor executor)

        // handle方法的区别在于handle方法会处理正常计算值和异常，因此它可以屏蔽异常，避免异常继续抛出。
        // 而thenApply方法只是用来处理正常值，因此一旦有异常就会抛出。

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            return 100;
        });
        CompletableFuture<String> f2 = future2.thenApplyAsync(i -> i * 10).thenApply(i -> i.toString());
        System.out.println(f2.get()); // "1000"

        System.out.println("handleXXX 和 thenApplyXXX end=========");

        // 4、纯消费(执行Action)
        // public CompletableFuture<Void> thenAccept(Consumer<? super T> action)
        // public CompletableFuture<Void> thenAcceptAsync(Consumer<? super T> action)
        // public CompletableFuture<Void> thenAcceptAsync(Consumer<? super T> action, Executor executor)

        CompletableFuture<Integer> future3 = CompletableFuture.supplyAsync(() -> {
            return 100;
        });
        CompletableFuture<Void> f3 = future3.thenAccept(System.out::println);
        System.out.println(f3.get());

        System.out.println("thenAccept end=========");

        // thenAcceptBoth以及相关方法提供了类似的功能，当两个CompletionStage都正常完成计算的时候，就会执行提供的action，它用来组合另外一个异步的结果。
        // runAfterBoth是当两个CompletionStage都正常完成计算的时候,执行一个Runnable，这个Runnable并不使用计算的结果。
        // public <U> CompletableFuture<Void> thenAcceptBoth(CompletionStage<? extends U> other, BiConsumer<? super T,? super U> action)
        // public <U> CompletableFuture<Void> thenAcceptBothAsync(CompletionStage<? extends U> other, BiConsumer<? super T,? super U> action)
        // public <U> CompletableFuture<Void> thenAcceptBothAsync(CompletionStage<? extends U> other, BiConsumer<? super T,? super U> action, Executor executor)
        // public CompletableFuture<Void> runAfterBoth(CompletionStage<?> other, Runnable action)

        CompletableFuture<Integer> future4 = CompletableFuture.supplyAsync(() -> {
            return 100;
        });
        CompletableFuture<Void> f4 =
            future4.thenAcceptBoth(CompletableFuture.completedFuture(10), (x, y) -> System.out.println(x * y));
        System.out.println(f4.get());

        System.out.println("thenAcceptBoth end=========");

        // 下面一组方法当计算完成的时候会执行一个Runnable,与thenAccept不同，Runnable并不使用CompletableFuture计算的结果。
        // public CompletableFuture<Void> thenRun(Runnable action)
        // public CompletableFuture<Void> thenRunAsync(Runnable action)
        // public CompletableFuture<Void> thenRunAsync(Runnable action, Executor executor)

        CompletableFuture<Integer> future5 = CompletableFuture.supplyAsync(() -> {
            return 100;
        });
        CompletableFuture<Void> f5 = future5.thenRun(() -> System.out.println("finished"));
        System.out.println(f5.get());

        System.out.println("thenRun end=========");

        // 总结：你可以根据方法的参数的类型来加速你的记忆。
        // Runnable类型的参数会忽略计算的结果，
        // Consumer是纯消费计算结果，BiConsumer会组合另外一个CompletionStage纯消费，
        // Function会对计算结果做转换，BiFunction会组合另外一个CompletionStage的计算结果做转换。

        // 组合
        // 这一组方法接受一个Function作为参数，这个Function的输入是当前的CompletableFuture的计算值，返回结果将是一个新的CompletableFuture，这个新的CompletableFuture会组合原来的CompletableFuture和函数返回的CompletableFuture。
        // public <U> CompletableFuture<U> thenCompose(Function<? super T,? extends CompletionStage<U>> fn)
        // public <U> CompletableFuture<U> thenComposeAsync(Function<? super T,? extends CompletionStage<U>> fn)
        // public <U> CompletableFuture<U> thenComposeAsync(Function<? super T,? extends CompletionStage<U>> fn, Executor executor)

        CompletableFuture<Integer> future6 = CompletableFuture.supplyAsync(() -> {
            return 100;
        });
        CompletableFuture<String> f6 = future6.thenCompose(i -> {
            return CompletableFuture.supplyAsync(() -> {
                return (i * 10) + "LLL";
            });
        });
        System.out.println(f6.get()); // 1000

        System.out.println("thenCompose end=========");

        // 下面的一组方法thenCombine用来复合另外一个CompletionStage的结果
        // 两个CompletionStage是并行执行的，它们之间并没有先后依赖顺序，other并不会等待先前的CompletableFuture执行完毕后再执行。
        // public <U,V> CompletableFuture<V> thenCombine(CompletionStage<? extends U> other, BiFunction<? super T,? super U,? extends V> fn)
        // public <U,V> CompletableFuture<V> thenCombineAsync(CompletionStage<? extends U> other, BiFunction<? super T,? super U,? extends V> fn)
        // public <U,V> CompletableFuture<V> thenCombineAsync(CompletionStage<? extends U> other, BiFunction<? super T,? super U,? extends V> fn, Executor executor)

        CompletableFuture<Integer> future7 = CompletableFuture.supplyAsync(() -> {
            return 100;
        });
        CompletableFuture<String> future8 = CompletableFuture.supplyAsync(() -> {
            return "abc";
        });
        CompletableFuture<String> f8 = future7.thenCombine(future8, (x, y) -> y + "-" + x);
        System.out.println(f8.get()); // abc-100

        System.out.println("thenCombine end=========");

        // acceptEither方法是当任意一个CompletionStage完成的时候，action这个消费者就会被执行。这个方法返回CompletableFuture<Void>
        // applyToEither方法是当任意一个CompletionStage完成的时候，fn会被执行，它的返回值会当作新的CompletableFuture<U>的计算结果。
        // public CompletableFuture<Void> acceptEither(CompletionStage<? extends T> other, Consumer<? super T> action)
        // public CompletableFuture<Void> acceptEitherAsync(CompletionStage<? extends T> other, Consumer<? super T> action)
        // public CompletableFuture<Void> acceptEitherAsync(CompletionStage<? extends T> other, Consumer<? super T> action, Executor executor)
        // public <U> CompletableFuture<U> applyToEither(CompletionStage<? extends T> other, Function<? super T,U> fn)
        // public <U> CompletableFuture<U> applyToEitherAsync(CompletionStage<? extends T> other, Function<? super T,U> fn)
        // public <U> CompletableFuture<U> applyToEitherAsync(CompletionStage<? extends T> other, Function<? super T,U> fn, Executor executor)

        Random rand = new Random();
        CompletableFuture<Integer> future9 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(10000 + rand.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 100;
        });
        CompletableFuture<Integer> future10 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(10000 + rand.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 200;
        });

        CompletableFuture<String> f10 = future9.applyToEither(future10, i -> i.toString());

        System.out.println(f10.get()); // 100 or 200

        System.out.println("applyToEither end=========");

        // 辅助方法 allOf 和 anyOf
        // allOf方法是当所有的CompletableFuture都执行完后执行计算。
        // anyOf方法是当任意一个CompletableFuture执行完后就会执行计算，计算的结果相同。
        // public static CompletableFuture<Void> 	    allOf(CompletableFuture<?>... cfs)
        // public static CompletableFuture<Object> 	anyOf(CompletableFuture<?>... cfs)

        // anyOf返回值的计算结果是参数中其中一个CompletableFuture的计算结果，
        // applyToEither返回值的计算结果却是要经过fn处理的

        CompletableFuture<Integer> future11 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(10000 + rand.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 100;
        });
        CompletableFuture<String> future12 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(10000 + rand.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "abc";
        });
        //CompletableFuture<Void> f12 =  CompletableFuture.allOf(future11,future12);
        CompletableFuture<Object> f12 = CompletableFuture.anyOf(future11, future12);
        System.out.println(f12.get());

        System.out.println("allOf anyOf end=========");

        System.in.read();

    }

    @Test
    public void testthenCompose() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future6 = CompletableFuture.supplyAsync(() -> {

            System.out.println("first start time=" + System.currentTimeMillis());
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("first end time=" + System.currentTimeMillis());

            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return 100;
        });
        CompletableFuture<String> f6 = future6.thenCompose(i -> CompletableFuture.supplyAsync(() -> {

            System.out.println("second start time=" + System.currentTimeMillis());
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("second end time=" + System.currentTimeMillis());

            return (i * 10) + "LLL";
        }));
        System.out.println(f6.get()); // 1000

        System.out.println("thenCompose end=========");
    }

}
