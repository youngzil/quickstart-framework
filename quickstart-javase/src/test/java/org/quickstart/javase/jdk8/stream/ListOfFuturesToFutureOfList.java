package org.quickstart.javase.jdk8.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
/**
 * <p>描述: [功能描述] </p >
 *
 * https://stackoverflow.com/questions/30025428/listfuture-to-futurelist-sequence
 *
 * @author yangzl
 * @date 2020/10/10 23:15
 * @version v1.0
 */
public class ListOfFuturesToFutureOfList {

    public static void main(String[] args) {
        ListOfFuturesToFutureOfList test = new ListOfFuturesToFutureOfList();
        test.load(10);
    }

    public void load(int numThreads) {
        final ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        List<CompletableFuture<String>> listOfFutures = IntStream.range(0, numThreads)
            .mapToObj(i -> loadData(i, executor)).collect(toList());

        CompletableFuture<List<String>> futureList = sequence(listOfFutures);

        System.out.println("Future complete before blocking? " + futureList.isDone());

        // this will block until all futures are completed
        List<String> data = futureList.join();
        System.out.println("Loaded data: " + data);

        System.out.println("Future complete after blocking? " + futureList.isDone());

        executor.shutdown();
    }

    public CompletableFuture<String> loadData(int dataPoint, Executor executor) {
        return CompletableFuture.supplyAsync(() -> {
            ThreadLocalRandom rnd = ThreadLocalRandom.current();

            System.out.println("Starting to load test data " + dataPoint);

            try {
                Thread.sleep(500 + rnd.nextInt(1500));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Successfully loaded test data " + dataPoint);

            return "data " + dataPoint;
        }, executor);
    }

    private <V> CompletableFuture<List<V>> sequence(List<CompletableFuture<V>> listOfFutures) {
        CompletableFuture<List<V>> identity = CompletableFuture.completedFuture(new ArrayList<>());

        BiFunction<CompletableFuture<List<V>>, CompletableFuture<V>, CompletableFuture<List<V>>> accumulator = (futureList, futureValue) ->
            futureValue.thenCombine(futureList, (value, list) -> {
                List<V> newList = new ArrayList<>(list.size() + 1);
                newList.addAll(list);
                newList.add(value);
                return newList;
            });

        BinaryOperator<CompletableFuture<List<V>>>
            combiner = (futureList1, futureList2) -> futureList1.thenCombine(futureList2, (list1, list2) -> {
            List<V> newList = new ArrayList<>(list1.size() + list2.size());
            newList.addAll(list1);
            newList.addAll(list2);
            return newList;
        });

        return listOfFutures.stream().reduce(identity, accumulator, combiner);
    }

}
