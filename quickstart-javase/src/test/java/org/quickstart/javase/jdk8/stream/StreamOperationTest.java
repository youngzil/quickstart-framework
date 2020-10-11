package org.quickstart.javase.jdk8.stream;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * <p>描述: [功能描述] </p >
 *
 * @author yangzl
 * @date 2020/8/17 23:00
 * @version v1.0
 */
public class StreamOperationTest {

    @Test
    public void testOperation() {

        //  Stream中间操作：filter()、distinct()、skip()、limit()、map()、flatMap()、sorted()、peek()）

        Stream.of("1", "2", "3").filter(x -> x.equals("2")).forEach(System.out::println);    // 2

        Stream.of("1", "3", "3").distinct().forEach(System.out::println);    // 1 3

        Stream.of("1", "2", "3").skip(1).forEach(System.out::println);    // 2 3

        Stream.of("1", "2", "3").limit(1).forEach(System.out::println);    // 1

        List<String> strings = new ArrayList<String>() {{
            add("1");
            add("2");
            add("3");
        }};
        strings.stream().map(x -> "map" + x).forEach(System.out::println);    // map1 map2 map3
        strings.stream().forEach(System.out::println);    // 1 2 3

        Stream.of("1,2", "3,4", "5,6").flatMap(x -> Stream.of(x.split(",")))
            .forEach(System.out::println);    // 1 2 3 4 5 6

        Stream.of("3", "1", "2").sorted().forEach(System.out::println);    // 1 2 3

        Stream.of("a", "b", "c").peek(x -> System.out.println(x.toUpperCase()))
            .forEach(System.out::println);    // A a B b C c

    }

    @Test
    public void testMap() throws InterruptedException, IOException {

        StopWatch stopWatch = StopWatch.createStarted();

        List<CompletableFuture<List<String>>> futureResultList = Lists.newArrayList();

        for (int i = 0; i < 4; i++) {
            futureResultList.add(getCompetableFutureResult(i));
        }

        List<String> ipRelationList =
            futureResultList.stream().map(CompletableFuture::join).flatMap(Collection::stream).distinct()
                .collect(toList());

        ipRelationList.forEach(System.out::println);

        System.out.println("time=" + stopWatch.getTime());
        System.in.read();

    }

    private CompletableFuture<List<String>> getCompetableFutureResult(int i) throws InterruptedException {

        List<String> list = new ArrayList<>();
        list.add("str" + i);
        //TimeUnit.SECONDS.sleep(5 + i * 2);

        return CompletableFuture.completedFuture(list);
    }

    @Test
    public void testJoin() throws IOException, InterruptedException {
        /*CompletableFuture<DeliveryResponse> doTransmit(Notification notification, Receiver receiver, ContentMutator contentMutator) {
            //send notification to this receiver
        }

        CompletableFuture<List<DeliveryResponse>> doTransmit(Notification notification, List<Receiver> receivers, ContentMutator contentMutator) {
            List<CompletableFuture<DeliveryResponse>> completableFutures = new ArrayList<>();
            receivers.forEach(receiver -> completableFutures.add(doTransmit(notification.clone(), receiver, contentMutator)));
            CompletableFuture<List<DeliveryResponse>> listCompletableFuture = CompletableFuture.supplyAsync(ArrayList::new);
            completableFutures.forEach(
                completableFuture ->
                    completableFuture.thenCombine(listCompletableFuture,
                        ((deliveryResponse, deliveryResponses) -> deliveryResponses.add(deliveryResponse))
                    )
            );
            return listCompletableFuture;
        }*/

        StopWatch stopWatch = StopWatch.createStarted();

        List<CompletableFuture<List<String>>> futureResultList = Lists.newArrayList();

        for (int i = 0; i < 4; i++) {
            futureResultList.add(getCompetableFutureResult(i));
        }

        CompletableFuture<List<List<String>>> ff = allAsList(futureResultList);
        ff.whenComplete((result, throwable) -> {
            result.forEach(v -> {
                System.out.println(v);
            });
        });

        System.out.println("time=" + stopWatch.getTime());
        System.in.read();

    }

    public <T> CompletableFuture<List<T>> allAsList(final List<CompletableFuture<T>> futures) {
        return CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]))
            .thenApply(ignored -> futures.stream().map(CompletableFuture::join).collect(toList()));
    }

    @Test
    public void testJoin2() {
        List<List<String>> aList = new ArrayList<>();
        aList.add(new ArrayList<>(Arrays.asList("xyz", "abc")));
        aList.add(new ArrayList<>(Arrays.asList("qwe", "poi")));
        System.out.println("helo...");
        ExecutorService executor = Executors.newFixedThreadPool(aList.size());
        //aList.stream().flatMap(List::stream).
        Processor aProcessor = new Processor();
        List<String> tempList = new ArrayList<>();
        CompletableFuture aComFuture = CompletableFuture.supplyAsync(() -> aProcessor.processList2(aList), executor);
        try {
            aComFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testJoin3() {

        List<List<String>> aList = new ArrayList<>();
        aList.add(new ArrayList<>(Arrays.asList("xyz", "abc")));
        aList.add(new ArrayList<>(Arrays.asList("qwe", "poi")));
        System.out.println("helo...");
        ExecutorService executor = Executors.newFixedThreadPool(aList.size());
        //aList.stream().flatMap(List::stream).
        Processor aProcessor = new Processor();

        //Create all CFs
        List<CompletableFuture<Boolean>> futureList = aList.stream()
            .map(strings -> CompletableFuture.supplyAsync(() -> aProcessor.processList(strings), executor))
            .collect(toList());

        //Wait for them all to complete
        CompletableFuture.allOf(futureList.toArray(new CompletableFuture[0])).join();

        //Do processing of the results
        Stream<Boolean> booleanStream = futureList.stream().map(CompletableFuture::join);
        //Do other stuff you need
    }

    @Test
    public void testJoin4() {

        List<List<String>> aList = new ArrayList<>();
        aList.add(new ArrayList<>(Arrays.asList("xyz", "abc")));
        aList.add(new ArrayList<>(Arrays.asList("qwe", "poi")));
        System.out.println("hello...");

        Processor aProcessor = new Processor();
        List<String> tempList = new ArrayList<>();
        CompletableFuture aComFuture = CompletableFuture.supplyAsync(() -> "");

        aList.stream().forEach(list -> aComFuture.thenApply(fn -> aProcessor.processList(list)));

        aComFuture.join();

    }

    @Test
    public void testJoin5() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Stream<CompletableFuture<Integer>> que = IntStream.range(0, 100000).boxed()//
            .map(x -> CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep((long)(Math.random() * 10));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return x;
            }, executorService));
        CompletableFuture<List<Integer>> sequence = sequence2(que.collect(Collectors.toList()), executorService);

    }

    @Test
    public void testJoin6() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<CompletableFuture<Integer>> que =
            IntStream.range(0, 100000).mapToObj(x -> CompletableFuture.supplyAsync(() -> {
                LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos((long)(Math.random() * 10)));
                return x;
            }, executorService)).collect(Collectors.toList());
        CompletableFuture<List<Integer>> sequence = CompletableFuture
            .supplyAsync(() -> que.stream().map(CompletableFuture::join).collect(Collectors.toList()), executorService);
    }

    public static <T> CompletableFuture<List<T>> sequence2(List<CompletableFuture<T>> com, ExecutorService exec) {
        if (com.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Stream<? extends CompletableFuture<T>> stream = com.stream();
        CompletableFuture<List<T>> init = CompletableFuture.completedFuture(new ArrayList<T>());
        return stream.reduce(init, (ls, fut) -> ls.thenComposeAsync(x -> fut.thenApplyAsync(y -> {
            x.add(y);
            return x;
        }, exec), exec), (a, b) -> a.thenCombineAsync(b, (ls1, ls2) -> {
            ls1.addAll(ls2);
            return ls1;
        }, exec));
    }

    static <T> CompletableFuture<List<T>> sequence(List<CompletableFuture<T>> com) {

        CompletableFuture<List<T>> result = CompletableFuture.allOf(com.toArray(new CompletableFuture<?>[0]))//
            .thenApply(v -> com.stream()//
                .map(CompletableFuture::join)//
                .collect(Collectors.toList())//
            );

        com.forEach(f -> f.whenComplete((t, ex) -> {
            if (ex != null) {
                result.completeExceptionally(ex);
            }
        }));

        return result;
    }

    @Test
    public void testJoin7() {
        Stream<CompletableFuture<Integer>> stream = Stream
            .of(CompletableFuture.completedFuture(1), CompletableFuture.completedFuture(2),
                CompletableFuture.completedFuture(3));
        CompletableFuture<List<Integer>> ans = stream.collect(sequenceCollector());
    }

    public static <T> Collector<CompletableFuture<T>, ?, CompletableFuture<List<T>>> sequenceCollector() {
        return Collectors.collectingAndThen(Collectors.toList(), com -> sequence(com));
    }

    public <T> CompletableFuture<List<T>> sequence3(List<CompletableFuture<T>> com) {

        CompletableFuture<List<T>> identity = CompletableFuture.completedFuture(new ArrayList<T>());

        BiFunction<CompletableFuture<List<T>>, CompletableFuture<T>, CompletableFuture<List<T>>> combineToList =
            (acc, next) -> acc.thenCombine(next, (a, b) -> {
                a.add(b);
                return a;
            });

        BinaryOperator<CompletableFuture<List<T>>> combineLists = (a, b) -> a.thenCombine(b, (l1, l2) -> {
            l1.addAll(l2);
            return l1;
        });

        return com.stream().reduce(identity, combineToList, combineLists);

    }


    private <V> CompletableFuture<List<V>> sequence4(List<CompletableFuture<V>> listOfFutures) {
        CompletableFuture<List<V>> identity = CompletableFuture.completedFuture(new ArrayList<>());

        BiFunction<CompletableFuture<List<V>>, CompletableFuture<V>, CompletableFuture<List<V>>> accumulator = (futureList, futureValue) ->
            futureValue.thenCombine(futureList, (value, list) -> {
                List<V> newList = new ArrayList<>(list.size() + 1);
                newList.addAll(list);
                newList.add(value);
                return newList;
            });

        BinaryOperator<CompletableFuture<List<V>>> combiner = (futureList1, futureList2) -> futureList1.thenCombine(futureList2, (list1, list2) -> {
            List<V> newList = new ArrayList<>(list1.size() + list2.size());
            newList.addAll(list1);
            newList.addAll(list2);
            return newList;
        });

        return listOfFutures.stream().reduce(identity, accumulator, combiner);
    }

}

class Processor {
    public boolean processList2(List<List<String>> tempList) {
        for (List<String> string : tempList) {
            for (String str : string) {
                System.out.println("Output: " + str);
            }
        }
        return true;
    }

    public boolean processList(List<String> tempList) {
        for (String string : tempList) {
            System.out.println("Output: " + string);
        }
        return true;
    }
}
