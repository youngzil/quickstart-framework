package org.quickstart.reactor.sample;

import java.time.Duration;
import java.util.Optional;

import org.junit.Test;

import reactor.core.Disposable;
import reactor.core.publisher.Mono;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019-07-07 08:53
 */
public class MonoTest {

  @Test
  public void testMono() {
    /*
     * Tuple2<Long, Long> nowAndLater = Mono.zip( Mono.just(System.currentTimeMillis()), Flux.just(1).delay(1).map(i -> System.currentTimeMillis())) .block();
     * 
     * Mono.fromCallable(System::currentTimeMillis) .flatMap(time -> Mono.first(serviceA.findRecent(time), serviceB.findRecent(time))) .timeout(Duration.ofSeconds(3), errorHandler::fallback)
     * .doOnSuccess(r -> serviceM.incrementSuccess()) .subscribe(System.out::println);
     */

  }

  @Test
  public void testMonoStaticMethod() {

    // 静态方法示例
    // 1、empty()：创建一个不包含任何元素，只发布结束消息的序列：
    Mono.empty().subscribe(System.out::println);

    // 2、just()：可以指定序列中包含的全部元素。创建出来的 Mono序列在发布这些元素之后会自动结束。
    Mono.just("foo").subscribe(System.out::println);

    // 3、justOrEmpty()：从一个 Optional 对象或可能为 null 的对象中创建 Mono。只有 Optional 对象中包含值或对象不为 null 时，Mono 序列才产生对应的元素。
    Mono.justOrEmpty(null).subscribe(System.out::println);
    Mono.justOrEmpty("justOrEmpty1").subscribe(System.out::println);
    Mono.justOrEmpty(Optional.of("justOrEmpty2")).subscribe(System.out::println);

    // 4、error(Throwable error)：创建一个只包含错误消息的序列。
    Mono.error(new RuntimeException("error")).subscribe(System.out::println, System.err::println);

    // 5、never()：创建一个不包含任何消息通知的序列。
    Mono.never().subscribe(System.out::println);

    // 6、fromCallable()、fromCompletionStage()、fromFuture()、fromRunnable()和 fromSupplier()：分别从 Callable、CompletionStage、CompletableFuture、Runnable 和 Supplier 中创建 Mono。
    // 通过fromRunnable创建，并实现异常处理
    Mono.fromRunnable(() -> {
      System.out.println("thread run");
      throw new RuntimeException("thread run error");
    }).subscribe(System.out::println, System.err::println);

    // 通过fromCallable创建
    Mono.fromCallable(() -> "callable run ").subscribe(System.out::println);

    // 通过fromSupplier创建
    Mono.fromSupplier(() -> "create from supplier").subscribe(System.out::println);
    // 7、delay(Duration duration)和 delayMillis(long duration)：创建一个 Mono 序列，在指定的延迟时间之后，产生数字 0 作为唯一值。
    long start = System.currentTimeMillis();
    Disposable disposable = Mono.delay(Duration.ofSeconds(2))//
        .subscribe(n -> {
          System.out.println("生产数据源：" + n);
          System.out.println("当前线程ID：" + Thread.currentThread().getId() + ",生产到消费耗时：" + (System.currentTimeMillis() - start));
        });

    System.out.println("主线程" + Thread.currentThread().getId() + "耗时：" + (System.currentTimeMillis() - start));

    while (!disposable.isDisposed()) {
    }

  }

  @Test
  public void testMonoDynamicMethod() {

    // 动态方法示例
    // 1、通过 create()方法来使用 MonoSink 来创建 Mono。
    Mono.create(sink -> sink.success("create MonoSink")).subscribe(System.out::println);
  }

}
