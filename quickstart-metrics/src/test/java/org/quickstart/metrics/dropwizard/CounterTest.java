package org.quickstart.metrics.dropwizard;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Counter;
import com.codahale.metrics.MetricRegistry;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CounterTest {

    private static List<String> stringList = new LinkedList<>();

    public static void inputElement(String input) {
        stringList.add(input);
    }

    public static void main(String[] args) {

        System.out.println("Counter Example running...");
        final MetricRegistry metricRegistry = new MetricRegistry();
        final Counter elementCounters = metricRegistry.counter("element-counter");

        ConsoleReporter reporter = ConsoleReporter.forRegistry(metricRegistry)//
            .convertRatesTo(TimeUnit.SECONDS)//
            .convertDurationsTo(TimeUnit.MICROSECONDS)//
            .build();
        reporter.start(1, TimeUnit.SECONDS);


        for (int i = 0; i < 10; i++) {
            try {
                inputElement(String.valueOf(i));
                elementCounters.inc();
                Thread.sleep(1000);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < 10; i++) {
            try {
                // this.inputElement(String.valueOf(i));
                elementCounters.dec();
                Thread.sleep(1000);

                System.out.println(elementCounters.getCount());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
