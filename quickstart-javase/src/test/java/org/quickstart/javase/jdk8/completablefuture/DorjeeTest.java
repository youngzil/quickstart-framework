package org.quickstart.javase.jdk8.completablefuture;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import org.junit.Test;

import com.google.common.collect.Lists;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019-07-25 21:25
 */
public class DorjeeTest {

  public static CompletableFuture<List<String>> getCompetableFutureResult() {
    return CompletableFuture.supplyAsync(() -> getResult());
  }

  public static List<String> getResult() {
    return Lists.newArrayList("one", "two", "three");
  }

  public static void testFutures() {
    int x = 3;
    List<CompletableFuture<List<String>>> futureResultList = Lists.newArrayList();
    for (int i = 0; i < x; i++) {
      futureResultList.add(getCompetableFutureResult());
    }

    CompletableFuture[] futureResultArray = futureResultList.toArray(new CompletableFuture[futureResultList.size()]);

    CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(futureResultArray);

    CompletableFuture<List<List<String>>> finalResults =
        combinedFuture.thenApply(voidd -> futureResultList.stream().map(future -> future.join()).collect(Collectors.toList()));

    finalResults.thenAccept(result -> System.out.println(result));
  }

  public static void main(String[] args) {
    testFutures();
    System.out.println("put debug break point on this line...");

  }

  @Test
  public void testGetVSJoin() {

    List<String> messages = Arrays.asList("Msg1", "Msg2", "Msg3", "Msg4", "Msg5", "Msg6", "Msg7", "Msg8", "Msg9", "Msg10", "Msg11", "Msg12");
    ExecutorService executor = Executors.newFixedThreadPool(4);

    List<String> mapResult = new ArrayList<>();

    CompletableFuture<?>[] fanoutRequestList = new CompletableFuture[messages.size()];
    int count = 0;
    for (String msg : messages) {
      CompletableFuture<?> future = CompletableFuture.supplyAsync(() -> msg, executor).exceptionally(ex -> "Error").thenAccept(mapResult::add);

      fanoutRequestList[count++] = future;
    }

    CompletableFuture.allOf(fanoutRequestList).join();

    /*
     * try { CompletableFuture.allOf(fanoutRequestList).get(); } catch (InterruptedException | ExecutionException e) { e.printStackTrace(); }
     */

    List<String> strList = mapResult.stream().filter(s -> !s.equalsIgnoreCase("Error")).collect(Collectors.toList());

    System.out.println(strList);

  }

}
