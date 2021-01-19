package org.quickstart.dropwizard.metrics;

import com.codahale.metrics.MetricRegistry;

public class MetricMain {

    /**
     * Uasge: java Metric method
     */
    public static void main(String[] args) throws InterruptedException {
        String user_choice = "";

        System.out.println("Welcome use metrics example!");
        // GaugeExample ge = new GaugeExample();
        // ge.run();

        if (args.length < 1) {
            System.out.println("Usage: Metric [method: ccounter|meter|gauge|histogram");
            System.exit(1);
        } else {
            user_choice = args[0];
            System.out.println("User input : " + args[0]);
        }

        switch (user_choice) {
            case "counter":
                CounterTest ce = new CounterTest();
                ce.main(null);
                break;
            case "meter":
                MeterTest me = new MeterTest();
                me.main(null);
                break;
            case "gauge":
                // Command
                // java -cp lib/metrics-core-4.0.5.jar:lib/slf4j-api-1.7.2.jar:target/learn-metrics-1.0.jar:lib/log4j-1.2.12.jar:./lib/slf4j-simple-1.7.28.jar cn.edulinks.Metric gauge
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

                // CodaGaugeExample cg = new CodaGaugeExample();
                // cg.run(new MetricRegistry(), "stringList");
                break;
            case "ccounter":
                // Command
                // java -cp lib/metrics-core-4.0.5.jar:lib/slf4j-api-1.7.2.jar:target/learn-metrics-1.0.jar:lib/log4j-1.2.12.jar:./lib/slf4j-simple-1.7.28.jar cn.edulinks.Metric ccounter
                CounterTest cc = new CounterTest();
                cc.main(null);
                break;
            case "histogram":
                HistogramsTest ch = new HistogramsTest();
                ch.run();
                break;
            case "timer":
                TimerTest ct = new TimerTest();
                ct.run();
                break;
            default:
                System.out.println("No option are avaliable for your input.");
                break;
        }

    }
}
