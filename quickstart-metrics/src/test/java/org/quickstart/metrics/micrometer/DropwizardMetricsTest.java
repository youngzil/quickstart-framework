package org.quickstart.metrics.micrometer;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricFilter;
import com.codahale.metrics.MetricRegistry;
import com.izettle.metrics.influxdb.InfluxDbHttpSender;
import com.izettle.metrics.influxdb.InfluxDbReporter;
import com.izettle.metrics.influxdb.InfluxDbSender;
import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import io.micrometer.core.instrument.dropwizard.DropwizardConfig;
import io.micrometer.core.instrument.dropwizard.DropwizardMeterRegistry;
import io.micrometer.core.instrument.util.HierarchicalNameMapper;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class DropwizardMetricsTest {

    public static void main(String[] args) throws Exception {

        // [Passing through to Dropwizard's console reporter](https://micrometer.io/docs/guide/consoleReporter)

        String reporterHost = "127.0.0.1";
        int reporterPort = 8086;
        String reporterDatabase = "mydb";
        int reportIntervalMs = 5000;

        Map<String, String> tags = new HashMap<>();
        MetricRegistry dropwizardRegistry = new MetricRegistry();

        ConsoleReporter reporter = ConsoleReporter.forRegistry(dropwizardRegistry)//
            .convertRatesTo(TimeUnit.SECONDS)//
            .convertDurationsTo(TimeUnit.MILLISECONDS)//
            .build();
        reporter.start(1, TimeUnit.SECONDS);

        // JmxReporter jmxReporter = JmxReporter.forRegistry(this.registry).build();
        // jmxReporter.start();

        InfluxDbSender influxDbHttpSender =
            new InfluxDbHttpSender("http", reporterHost, reporterPort, reporterDatabase, "", TimeUnit.SECONDS, 5000, 5000,
                generateMeasurementsPrefix());

        Map<String, String> measurementMappings = new HashMap<>();
        measurementMappings.put("Meter", "Meter.+");

        InfluxDbReporter influxDbReporter =
            InfluxDbReporter.forRegistry(dropwizardRegistry).convertRatesTo(TimeUnit.SECONDS).convertDurationsTo(TimeUnit.MILLISECONDS)
                .filter(MetricFilter.ALL).skipIdleMetrics(false).withTags(tags)
                // .measurementMappings(measurementMappings)
                // .tagsTransformer(new ComsumerTransformer())
                .build(influxDbHttpSender);
        influxDbReporter.start(reportIntervalMs, TimeUnit.MILLISECONDS);

        DropwizardConfig dropwizardConfig = new DropwizardConfig() {
            @Override
            public String prefix() {
                return "console";
            }

            @Override
            public String get(String key) {
                return null;
            }
        };

        MeterRegistry registry = new DropwizardMeterRegistry(dropwizardConfig, dropwizardRegistry, (id, convention) -> id.getName(), Clock.SYSTEM) {
            @Override
            protected Double nullGaugeValue() {
                return null;
            }
        };

        MeterRegistry registry2 = new DropwizardMeterRegistry(dropwizardConfig, dropwizardRegistry, HierarchicalNameMapper.DEFAULT, Clock.SYSTEM) {
            @Override
            protected Double nullGaugeValue() {
                return null;
            }
        };

        registry.timer("my.latency", "input", "lowCardinalityInput").record(() -> {
            // do work
        });

        Timer timer = registry.timer("Timer");
        timer.count();

        Counter counter = registry.counter("dropwizard.counter");
        counter.increment();

        System.in.read();

    }

    private static String generateMeasurementsPrefix() {
        return "test-";
    }

}
