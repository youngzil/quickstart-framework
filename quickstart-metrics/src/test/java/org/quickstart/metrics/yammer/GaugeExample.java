package org.quickstart.metrics.yammer;

import com.yammer.metrics.Metrics;
import com.yammer.metrics.core.Gauge;
import com.yammer.metrics.reporting.ConsoleReporter;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GaugeExample {
    private List<String> stringList = new LinkedList<String>();
    Gauge<Integer> gauge = Metrics.newGauge(GaugeExample.class, "list-size-gauge", new Gauge<Integer>(){
        @Override
        public Integer value(){
            return stringList.size();
        }
    });

    public void inputElement(String input){
        stringList.add(input);
    }

    public static void run(){
        System.out.println("GaugeExample running...");
        ConsoleReporter.enable(1, TimeUnit.SECONDS);
        GaugeExample ge = new GaugeExample();
        for(int i = 0; i < 10; i++){
            try {
                ge.inputElement(String.valueOf(i));
                // System.out.println(String.valueOf(i));
                // System.out.println(ge.stringList.size());
                Thread.sleep(1000);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
