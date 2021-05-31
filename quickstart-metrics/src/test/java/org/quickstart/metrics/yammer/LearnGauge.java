package org.quickstart.metrics.yammer;

import com.yammer.metrics.Metrics;
import com.yammer.metrics.core.Gauge;
import com.yammer.metrics.reporting.ConsoleReporter;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class LearnGauge {

    private List<String> stringList = new LinkedList<String>();

    Gauge<Integer> gauge = Metrics.newGauge(LearnGauge.class, "list-size-gauge", new Gauge<Integer>() {
        @Override
        public Integer value() {
            return stringList.size();
        }
    });

    public void inputElement(String input) {
        stringList.add(input);
    }

    public static void main(String[] args) throws InterruptedException {
        // periodically report all registered metrics to the console
        ConsoleReporter.enable(1, TimeUnit.SECONDS);
        LearnGauge learnGauge = new LearnGauge();

        for (int i = 0; i < 10; i++) {
            learnGauge.inputElement(String.valueOf(i));
            Thread.sleep(1000);
        }

    }

}