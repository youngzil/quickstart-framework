package org.quickstart.javase.java7.coin.forkjoin;

import java.util.concurrent.RecursiveTask;

class AdditionProblem extends RecursiveTask<Integer> {
    int seedArray[] = null;

    private int seedCount = -1;

    public AdditionProblem(int start, int end) {
        System.out.println("I'm created:" + this.hashCode());
        seedArray = seed(start, end);
    }

    public AdditionProblem(int seeds) {
        seedArray = seed(seeds);
        setSeedCount(seedArray.length);
    }

    public Integer compute() {
        int result = 0;
        for (int n : seedArray) {
            result = result + n;
        }
        return result;
    }

    public static void main(String[] args) {

        AdditionProblem problem = new AdditionProblem(0, 10);
        int result = problem.compute();

        System.out.println("Result:" + result);
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

    public int getSeedCount() {
        return seedCount;
    }

    public void setSeedCount(int seedCount) {
        this.seedCount = seedCount;
    }
}
