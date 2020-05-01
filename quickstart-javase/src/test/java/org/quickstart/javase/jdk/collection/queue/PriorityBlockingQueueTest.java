package org.quickstart.javase.jdk.collection.queue;

import com.google.common.collect.Lists;
import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import org.junit.Test;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019/11/30 10:41
 */
public class PriorityBlockingQueueTest {

  private static PriorityBlockingQueue<Integer> queue = new PriorityBlockingQueue<>();


  public static void main(String[] args) throws InterruptedException {

    new Thread(() -> {
      System.out.println("Polling...");

      try {
        Integer poll = queue.take();
        System.out.println("Polled: " + poll);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }).start();

    Thread.sleep(TimeUnit.SECONDS.toMillis(5));
    System.out.println("Adding to queue");
    queue.add(1);

  }

  @Test
  public void test() throws InterruptedException {
    Thread thread = new Thread(() -> {
      System.out.println("Polling...");
      while (true) {
        try {
          Integer poll = queue.take();
          System.out.println("Polled: " + poll);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });

    thread.start();

    Thread.sleep(TimeUnit.SECONDS.toMillis(5));
    System.out.println("Adding to queue");

    queue.addAll(Lists.newArrayList(1, 5, 6, 1, 2, 6, 7));
    Thread.sleep(TimeUnit.SECONDS.toMillis(1));

  }

  @Test
  public void test2() {
    PriorityBlockingQueue<User> queue = new PriorityBlockingQueue<User>();
    for (int i = 0; i < 12; i++) {
      User user = new User();
      int max = 20;
      int min = 10;
      Random random = new Random();

      int n = random.nextInt(max) % (max - min + 1) + min;

      user.setPriority(n);
      user.setUsername("李艳第" + i + "天");

      queue.add(user);
    }

    for (int i = 0; i < 12; i++) {
      User u = queue.poll();
      System.out.println("优先级是：" + u.getPriority() + "," + u.getUsername());
    }
  }

  class User implements Comparable<User> {

    private Integer priority;
    private String username;

    public Integer getPriority() {
      return priority;
    }

    public void setPriority(Integer priority) {
      this.priority = priority;
    }

    public String getUsername() {
      return username;
    }

    public void setUsername(String username) {
      this.username = username;
    }

    /**
     * *   * @Description:当前对象和其他对象做比较，当前优先级大就返回-1，优先级小就返回1   * 值越小优先级越高   * @param TODO   * @author yaomingyang   * @date 2017年8月27日 上午11:28:10
     */
    @Override
    public int compareTo(User user) {
      //  System.out.println("比较结果"+this.priority.compareTo(user.getPriority()));
      return this.priority.compareTo(user.getPriority());
    }
  }


}
