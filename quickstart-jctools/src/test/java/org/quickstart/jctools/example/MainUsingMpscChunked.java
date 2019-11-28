package org.quickstart.jctools.example;

import java.util.Queue;

import org.jctools.queues.MpscChunkedArrayQueue;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019/11/28 10:49
 */
public class MainUsingMpscChunked {

  public static void main(String[] args) {
    // Queue<Integer> q = new MpscChunkedArrayQueue<>(1024, 8*1024, true);
    Queue<Integer> q = new MpscChunkedArrayQueue<>(1024, 8 * 1024);
    // fill up the queue
    int i = 0;
    while (q.offer(i))
      i++;
    System.out.println("Added " + i);
    // empty it
    i = 0;
    while (q.poll() != null)
      i++;
    System.out.println("Removed " + i);
  }

}
