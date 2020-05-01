package org.quickstart.javase.java7.coin.forkjoin;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoiner {
    private ForkJoinPool pool = null;

    private int numberOfTasks = 1;

    private void init() {
        pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
    }

    class AdditionProblemTask extends RecursiveTask<Integer> {
        int seedArray[] = null;
        private static final int seedThreshold = 10;
        private int seedCount = -1;

        public AdditionProblemTask(int s) {
            seedArray = seed(s);
            setSeedCount(seedArray.length);

        }

        public AdditionProblemTask(int start, int end) {
            seedArray = seed(start, end);
        }

        private int[] seed(int s) {
            int seedArray[] = new int[s];

            for (int i = 0; i < seedArray.length; i++) {
                seedArray[i] = i;
            }
            return seedArray;
        }

        private int[] seed(int start, int end) {
            int seedArray[] = new int[(end - start) + 1];

            for (int i = 0; i < seedArray.length; i++) {
                seedArray[i] = start++;
            }
            return seedArray;
        }

        @Override
        protected Integer compute() {
            int result = -1;
            List<RecursiveTask> tasks = new LinkedList<>();
            AdditionProblem problem = null;

            int start = 0;
            int initInc = getSeedCount() / numberOfTasks;
            int inc = 0;

            System.out.println("Initial inc" + initInc);

            for (int i = 0; i < numberOfTasks; i++) {
                inc = inc + initInc;
                System.out.println("manipulated start and inc:" + start + "," + inc);
                problem = new AdditionProblem(start, inc);
                start = inc + 1;

                tasks.add(problem);

                problem.fork();
            }
            for (RecursiveTask<Integer> task : tasks) {
                result = result + task.join();
            }
            System.out.println("Result=" + result);
            return result;
        }

        public int getSeedCount() {
            return seedCount;
        }

        public void setSeedCount(int seedCount) {
            System.out.println("Seedcount is:" + seedCount);
            this.seedCount = seedCount;
        }
    }

    private void additionInParallel() {
        pool.invoke(new AdditionProblemTask(100));
    }

    public static void main(String[] args) {

        ForkJoiner joiner = new ForkJoiner();
        joiner.init();
        joiner.additionInParallel();
    }

}
