package org.quickstart.reactor.sample;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019-07-07 08:53
 */
public class FluxTest {

  private static List<String> words = Arrays.asList("the", "quick", "brown", "fox", "jumped", "over", "the", "lazy", "dog");

  @Test
  public void testFlux() {

    /*
     * Flux.fromIterable(getSomeLongList()) .mergeWith(Flux.interval(100)) .doOnNext(serviceA::someObserver) .map(d -> d * 2) .take(3) .onErrorResume(errorHandler::fallback)
     * .doAfterTerminate(serviceM::incrementTerminate) .subscribe(System.out::println);
     */

  }

  @Test
  public void testFlux1() {

    Flux.just("Ben", "Michael", "Mark").subscribe(new Subscriber<String>() {

      @Override
      public void onSubscribe(Subscription s) {
        s.request(3);
      }

      @Override
      public void onNext(String s) {
        System.out.println("Hello " + s + "!");
      }

      @Override
      public void onError(Throwable t) {

      }

      @Override
      public void onComplete() {
        System.out.println("Completed");
      }
    });

    System.out.println("===============================================");

    Flux.just("Ben", "Michael", "Mark") //
        .doOnNext(s -> System.out.println("Hello " + s + "!"))//
        .doOnComplete(() -> System.out.println("Completed"))//
        .take(2)// take限制处理的items数量，到达指定数量，取消订阅。
        .subscribe();

    System.out.println("===============================================");

    // 调用last()函数可以将异步操作变为同步操作。

    // last为"Mark"
    String last = Flux.just("Ben", "Michael", "Mark").last().block();
    System.out.println("last=" + last);
    // get string list
    List<String> list = Flux.just("Ben", "Michael", "Mark").collectList().block();
    list.forEach(System.out::println);

    System.out.println("===============================================");

    System.out.println("===============================================");

    System.out.println("===============================================");

    System.out.println("===============================================");

    System.out.println("===============================================");

  }

  @Test
  public void simpleCreation() {
    Flux<String> fewWords = Flux.just("Hello", "World");
    Flux<String> manyWords = Flux.fromIterable(words);

    fewWords.subscribe(System.out::println);
    System.out.println();
    manyWords.subscribe(System.out::println);
  }

  @Test
  public void findingMissingLetter() {
    Flux<String> manyLetters = Flux.fromIterable(words)//
        .flatMap(word -> Flux.fromArray(word.split("")))//
        .distinct()//
        .sort()//
        .zipWith(Flux.range(1, Integer.MAX_VALUE), //
            (string, count) -> String.format("%2d. %s", count, string));

    manyLetters.subscribe(System.out::println);
  }

  @Test
  public void restoringMissingLetter() {
    Mono<String> missing = Mono.just("s");
    Flux<String> allLetters = Flux.fromIterable(words)//
        .flatMap(word -> Flux.fromArray(word.split("")))//
        .concatWith(missing)//
        .distinct()//
        .sort()//
        .zipWith(Flux.range(1, Integer.MAX_VALUE), //
            (string, count) -> String.format("%2d. %s", count, string));

    allLetters.subscribe(System.out::println);
  }

  @Test
  public void shortCircuit() {
    Flux<String> helloPauseWorld = Mono.just("Hello")//
        .concatWith(Mono.just("world")//
            .delaySubscription(Duration.ofMillis(500)));
    // .delaySubscriptionMillis(500));

    helloPauseWorld.subscribe(System.out::println);
  }

  @Test
  public void blocks() {
    Flux<String> helloPauseWorld = Mono.just("Hello")//
        .concatWith(Mono.just("world")//
            .delaySubscription(Duration.ofMillis(500)));
    // .delaySubscriptionMillis(500));

    helloPauseWorld.toStream().forEach(System.out::println);
  }

  @Test
  public void firstEmitting() {
    Mono<String> a = Mono.just("oops I'm late").delaySubscription(Duration.ofMillis(500));
    Flux<String> b = Flux.just("let's get", "the party", "started").delaySequence(Duration.ofMillis(600));
    // .delayMillis(400);

    Flux.first(a, b)
        // Flux.firstEmitting(a, b)
        .toIterable().forEach(System.out::println);
  }

}
