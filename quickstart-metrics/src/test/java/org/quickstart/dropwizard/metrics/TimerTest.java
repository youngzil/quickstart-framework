package org.quickstart.dropwizard.metrics;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TimerTest {

    // 产生一个随机数
    public Integer getRandNumber() {
        Random r = new Random();
        return r.nextInt(1000);
    }

    public Long getResponseTime() {
        return System.currentTimeMillis();
    }

    public void run() {
        System.out.println("Timer Example running...");
        final MetricRegistry metrics = new MetricRegistry();
        final Timer responseTimes = metrics.timer(MetricRegistry.name(HistogramsTest.class, "process-time"));

        ConsoleReporter reporter = ConsoleReporter.forRegistry(metrics).convertRatesTo(TimeUnit.SECONDS)
            .convertDurationsTo(TimeUnit.MICROSECONDS).build();
        reporter.start(1, TimeUnit.SECONDS);

        try (final Timer.Context context = responseTimes.time()) {
            for (int i = 0; i < 100000; i++) {
                try {
                    Thread.sleep(this.getRandNumber());
                    context.stop();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        TimerTest ct = new TimerTest();
        ct.run();
    }
}
