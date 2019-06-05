/**
 * 项目名称：quickstart-javase 
 * 文件名：CompletableFutureTest.java
 * 版本信息：
 * 日期：2018年8月28日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.jdk8;

import java.util.concurrent.CompletableFuture;

import org.junit.Test;

/**
 * CompletableFutureTest
 * 
 * https://www.jianshu.com/p/6f3ee90ab7d3
 * 
 * @author：youngzil@163.com
 * @2018年8月28日 下午11:35:30
 * @since 1.0
 */
public class CompletableFutureTest implements Runnable {
    CompletableFuture<Integer> re = null;

    public CompletableFutureTest(CompletableFuture<Integer> re) {
        this.re = re;
    }

    @Override
    public void run() {
        int myRe = 0;
        try {
            myRe = re.get() * re.get();
        } catch (Exception e) {
        }
        System.out.println(myRe);
    }

    public static void main(String[] args) throws InterruptedException {
        final CompletableFuture<Integer> future = new CompletableFuture<Integer>();
        new Thread(new CompletableFutureTest(future)).start();
        // 模拟长时间的计算过程
        Thread.sleep(1000);
        // 告知完成结果
        future.complete(60);
    }

    // 已Async结尾的方法都是可以异步执行的，如果指定了线程池，会在指定的线程池中执行，
    // 如果没有指定，默认会在ForkJoinPool.commonPool()中执行，
    // 下文中将会有好多类似的，都不详细解释了。
    // 关键的入参只有一个Function，它是函数式接口，所以使用Lambda表示起来会更加优雅。

    // 它的入参是上一个阶段计算后的结果，返回值是经过转化后结果。
    @Test
    public void thenApply() {
        String result = CompletableFuture.supplyAsync(() -> "hello").thenApply(s -> s + " world").join();
        System.out.println(result);
    }

    // thenAccept是针对结果进行消耗，因为他的入参是Consumer，有入参无返回值。
    @Test
    public void thenAccept() {
        CompletableFuture.supplyAsync(() -> "hello").thenAccept(s -> System.out.println(s + " world"));
    }

    // thenRun它的入参是一个Runnable的实例，表示当得到上一步的结果时的操作。
    @Test
    public void thenRun() {
        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        }).thenRun(() -> System.out.println("hello world"));
        while (true) {
        }
    }

    // 结合两个CompletionStage的结果，进行转化后返回
    // 它需要原来的处理返回值，并且other代表的CompletionStage也要返回值之后，利用这两个返回值，进行转换后返回指定类型的值。
    @Test
    public void thenCombine() {
        String result = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "world";
        }), (s1, s2) -> s1 + " " + s2).join();
        System.out.println(result);
    }

    // 结合两个CompletionStage的结果，进行消耗
    // 它需要原来的处理返回值，并且other代表的CompletionStage也要返回值之后，利用这两个返回值，进行消耗。
    @Test
    public void thenAcceptBoth() {
        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        }).thenAcceptBoth(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "world";
        }), (s1, s2) -> System.out.println(s1 + " " + s2));
        while (true) {
        }
    }

    // 在两个CompletionStage都运行完执行。
    // 不关心这两个CompletionStage的结果，只关心这两个CompletionStage执行完毕，之后在进行操作（Runnable）。
    @Test
    public void runAfterBoth() {
        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "s1";
        }).runAfterBothAsync(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "s2";
        }), () -> System.out.println("hello world"));
        while (true) {
        }
    }

    // 两个CompletionStage，谁计算的快，我就用那个CompletionStage的结果进行下一步的转化操作。
    // 我们现实开发场景中，总会碰到有两种渠道完成同一个事情，所以就可以调用这个方法，找一个最快的结果进行处理。

    @Test
    public void applyToEither() {
        String result = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "s1";
        }).applyToEither(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello world";
        }), s -> s).join();
        System.out.println(result);
    }

    // 两个CompletionStage，谁计算的快，我就用那个CompletionStage的结果进行下一步的消耗操作。
    @Test
    public void acceptEither() {
        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "s1";
        }).acceptEither(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello world";
        }), System.out::println);
        while (true) {
        }
    }

    // 两个CompletionStage，任何一个完成了都会执行下一步的操作（Runnable）。
    @Test
    public void runAfterEither() {
        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "s1";
        }).runAfterEither(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "s2";
        }), () -> System.out.println("hello world"));
        while (true) {
        }
    }

    // 当运行时出现了异常，可以通过exceptionally进行补偿。
    // public CompletionStage<T> exceptionally(Function<Throwable, ? extends T> fn);
    @Test
    public void exceptionally() {
        String result = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (1 == 1) {
                throw new RuntimeException("测试一下异常情况");
            }
            return "s1";
        }).exceptionally(e -> {
            System.out.println(e.getMessage());
            return "hello world";
        }).join();
        System.out.println(result);
    }

    // 当运行完成时，对结果的记录。这里的完成时有两种情况，一种是正常执行，返回值。
    // 另外一种是遇到异常抛出造成程序的中断。这里为什么要说成记录，
    // 因为这几个方法都会返回CompletableFuture，
    // 当Action执行完毕后它的结果返回原始的CompletableFuture的计算结果或者返回异常。所以不会对结果产生任何的作用。
    @Test
    public void whenComplete() {
        String result = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (1 == 1) {
                throw new RuntimeException("测试一下异常情况");
            }
            return "s1";
        }).whenComplete((s, t) -> {
            System.out.println(s);
            System.out.println(t.getMessage());
        }).exceptionally(e -> {
            System.out.println(e.getMessage());
            return "hello world";
        }).join();
        System.out.println(result);
    }

    // 这里也可以看出，如果使用了exceptionally，就会对最终的结果产生影响，它没有口子返回如果没有异常时的正确的值，
    // 这也就引出下面我们要介绍的handle。
    //
    // 运行完成时，对结果的处理。这里的完成时有两种情况，一种是正常执行，返回值。另外一种是遇到异常抛出造成程序的中断。
    // 出现异常时
    @Test
    public void testHandle() {
        String result = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 出现异常
            if (1 == 1) {
                throw new RuntimeException("测试一下异常情况");
            }
            return "s1";
        }).handle((s, t) -> {
            if (t != null) {
                return "hello world";
            }
            return s;
        }).join();
        System.out.println(result);
    }

    // 未出现异常时
    @Test
    public void handle() {
        String result = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "s1";
        }).handle((s, t) -> {
            if (t != null) {
                return "hello world";
            }
            return s;
        }).join();
        System.out.println(result);
    }

    // 上面就是CompletionStage接口中方法的使用实例，
    // CompletableFuture同样也同样实现了Future，所以也同样可以使用get进行阻塞获取值，
    // 总的来说，CompletableFuture使用起来还是比较爽的，看起来也比较优雅一点。

}
