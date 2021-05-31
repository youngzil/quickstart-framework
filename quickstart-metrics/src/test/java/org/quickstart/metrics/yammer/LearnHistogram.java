package org.quickstart.metrics.yammer;

import com.yammer.metrics.Metrics;
import com.yammer.metrics.core.Histogram;
import com.yammer.metrics.reporting.ConsoleReporter;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class LearnHistogram {
    private List<String> stringList = new LinkedList<String>();

    private Histogram histogram = Metrics.newHistogram(LearnHistogram.class, "size-histogram");

    public void push(String input) {
        stringList.add(input);
    }

    public void pop(String output) {
        stringList.remove(output);
    }

    public void updateHisto() {
        histogram.update(stringList.size());
    }

    public static void main(String[] args) throws InterruptedException {
        ConsoleReporter.enable(1, TimeUnit.SECONDS);
        LearnHistogram learnHistogram = new LearnHistogram();

        for (int time = 0; time < 100000; time++) {
            learnHistogram.push(String.valueOf(time));

            if (time % 10 == 0) {
                learnHistogram.updateHisto();
            }

            if (time % 2 == 2) {
                learnHistogram.pop(String.valueOf(time));
            }
            Thread.sleep(1);

        }
    }

}
