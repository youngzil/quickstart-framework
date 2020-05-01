package org.quickstart.javase.jdk.collection.queue;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019/11/30 10:41
 */
public class DelayQueueTest {

  // 实现getDelay方法
  // if (delay <= 0)//延迟时间到期，获取并删除头部元素。

  private static DelayQueue<DelayTask> delayQueue = new DelayQueue<>();

  static class DelayTask implements Delayed {

    // 延迟时间
    private final long delay;
    // 到期时间
    private final long expire;
    // 数据
    private final String msg;
    // 创建时间
    private final long now;

    /**
     * 初始化 DelayTask 对象
     *
     * @param delay 延迟时间 单位：微妙
     * @param msg 业务信息
     */
    DelayTask(long delay, String msg) {
      this.delay = delay; // 延迟时间
      this.msg = msg; // 业务信息
      this.now = Instant.now().toEpochMilli();
      this.expire = now + delay; // 到期时间 = 当前时间+延迟时间
    }

    /**
     * 获取延迟时间
     *
     * @param unit 单位对象
     */
    @Override
    public long getDelay(TimeUnit unit) {
      return unit.convert(expire - Instant.now().toEpochMilli(), TimeUnit.MILLISECONDS);
    }

    /**
     * 比较器 比较规则：延迟时间越长的对象越靠后
     */
    @Override
    public int compareTo(Delayed o) {
      if (o == this) // compare zero ONLY if same object
      {
        return 0;
      }
      return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
    }

    @Override
    public String toString() {
      return "DelayTask{" +
          "delay=" + delay +
          ", expire=" + expire +
          ", msg='" + msg + '\'' +
          ", now=" + now +
          '}';
    }
  }

  /**
   * 生产者线程
   */
  public static void main(String[] args) {
    initConsumer();
    try {
      // 等待消费者初始化完毕
      TimeUnit.SECONDS.sleep(3);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    delayQueue.add(new DelayTask(1000, "Task1"));
    delayQueue.add(new DelayTask(3000, "Task3"));
    delayQueue.add(new DelayTask(2000, "Task2"));
    delayQueue.add(new DelayTask(5000, "Task5"));
    delayQueue.add(new DelayTask(4000, "Task4"));
  }

  /**
   * 初始化消费者线程
   */
  private static void initConsumer() {
    Runnable task = () -> {
      while (true) {
        try {
          System.out.println("尝试获取延迟队列中的任务。" + LocalDateTime.now());
          System.out.println(delayQueue.take());
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    };
    Thread consumer = new Thread(task);
    consumer.start();
  }
}
