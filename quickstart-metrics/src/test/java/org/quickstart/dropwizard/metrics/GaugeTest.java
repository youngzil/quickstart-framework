package org.quickstart.dropwizard.metrics;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.MetricRegistry;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GaugeTest {

    // private final Queue queue;
    // static final MetricRegistry metrics = new MetricRegistry();
    private List<String> stringList = new LinkedList<String>();
    // Gauge<Integer> gauge = Metrics.newGauge(GaugeExample.class, "list-size-gauge", new Gauge<Integer>(){
    //     @Override
    //     public Integer value(){
    //         return stringList.size();
    //     }
    // });
    public GaugeTest(MetricRegistry metrics, String name){
        this.stringList = new LinkedList<>();
        metrics.register(MetricRegistry.name(GaugeTest.class, name, "size"),
            new Gauge<Integer>(){
                @Override
                public Integer getValue(){
                    System.out.println("Call Gauge Internal");
                    return stringList.size();
                }
            });

        ConsoleReporter reporter = ConsoleReporter.forRegistry(metrics).convertRatesTo(TimeUnit.SECONDS).convertDurationsTo(TimeUnit.MICROSECONDS).build();
        reporter.start(1, TimeUnit.SECONDS);
    }

    public void inputElement(String input){
        stringList.add(input);
    }

    public void run(MetricRegistry metrics, String name){
        System.out.println("\nGaugeExample running...");
    }

    public static void main(String[] args) {
        MetricRegistry metrics = new MetricRegistry();
        GaugeTest ge = new GaugeTest(metrics, "stringList");
        for (int i = 0; i < 10; i++) {
            try {
                ge.inputElement(String.valueOf(i));
                ge.run(metrics, "stringList");
                // System.out.println(String.valueOf(i));
                // System.out.println(ge.stringList.size());
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
