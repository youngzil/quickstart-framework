package com.quickstart.test.thread;

public class ThreadDemo3 {

    private static Index num = new Index();
    // 创建一个Index类型的本地变量
    private static ThreadLocal<Integer> local = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return num.yang;
        }
    };

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[5];
        for (int j = 0; j < 5; j++) {
            threads[j] = new Thread(new Runnable() {
                @Override
                public void run() {
                    // 取出当前线程的本地变量，并累加1000次
                    Integer index = local.get();
                    for (int i = 0; i < 1000; i++) {
                        index++;
                    }
                    System.out.println(Thread.currentThread().getName() + " : " + index);

                }
            }, "Thread-" + j);
        }
        for (Thread thread : threads) {
            thread.start();
        }
    }

    static class Index {
        int yang;

        public void increase() {
            yang++;
        }
    }

}
