package org.quickstart.dropwizard.metrics;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Histogram;
import com.codahale.metrics.MetricRegistry;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class HistogramsTest {

    // 产生一个随机数
    public Integer getRandNumber() {
        Random r = new Random();
        return r.nextInt(1000);
    }

    public void run() {
        System.out.println("Histograms Example running...");
        final MetricRegistry metrics = new MetricRegistry();
        final Histogram randomNumbers = metrics.histogram(MetricRegistry.name(HistogramsTest.class, "randomNumbers"));

        ConsoleReporter reporter =
            ConsoleReporter.forRegistry(metrics).convertRatesTo(TimeUnit.SECONDS).convertDurationsTo(TimeUnit.MICROSECONDS).build();
        reporter.start(1, TimeUnit.SECONDS);

        for (int i = 0; i < 100000; i++) {
            try {
                randomNumbers.update(this.getRandNumber());
                Thread.sleep(10);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        HistogramsTest ch = new HistogramsTest();
        ch.run();

        /*21-4-1 15:01:14 ================================================================

        -- Histograms ------------------------------------------------------------------
        org.quickstart.dropwizard.metrics.HistogramsTest.randomNumbers
            count = 3920
        min = 2
        max = 998
        mean = 514.96
        stddev = 288.36
        median = 529.00
        75% <= 771.00
        95% <= 952.00
        98% <= 977.00
        99% <= 987.00
        99.9% <= 998.00*/
    }
}
