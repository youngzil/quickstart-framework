package org.quickstart.metrics.micrometer.influx;

import com.codahale.metrics.MetricFilter;
import com.codahale.metrics.MetricRegistry;
import com.izettle.metrics.influxdb.InfluxDbHttpSender;
import com.izettle.metrics.influxdb.InfluxDbReporter;
import com.izettle.metrics.influxdb.InfluxDbSender;
import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import io.micrometer.core.instrument.config.NamingConvention;
import io.micrometer.core.instrument.dropwizard.DropwizardConfig;
import io.micrometer.core.instrument.dropwizard.DropwizardMeterRegistry;
import io.micrometer.core.instrument.util.HierarchicalNameMapper;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class DropwizardMetricsTest {

    public static void main(String[] args) throws Exception {

        String reporterHost = "127.0.0.1";
        int reporterPort = 8086;
        String reporterDatabase = "mydb";
        int reportIntervalMs = 5000;

        Map<String, String> tags = new HashMap<>();
        MetricRegistry registry = new MetricRegistry();

        // JmxReporter jmxReporter = JmxReporter.forRegistry(this.registry).build();
        // jmxReporter.start();

        InfluxDbSender influxDbHttpSender =
            new InfluxDbHttpSender("http", reporterHost, reporterPort, reporterDatabase, "", TimeUnit.SECONDS, 5000, 5000,
                generateMeasurementsPrefix());

        Map<String, String> measurementMappings = new HashMap<>();
        measurementMappings.put("Meter", "Meter.+");

        InfluxDbReporter influxDbReporter =
            InfluxDbReporter.forRegistry(registry).convertRatesTo(TimeUnit.SECONDS).convertDurationsTo(TimeUnit.MILLISECONDS).filter(MetricFilter.ALL)
                .skipIdleMetrics(false).withTags(tags)
                // .measurementMappings(measurementMappings)
                // .tagsTransformer(new ComsumerTransformer())
                .build(influxDbHttpSender);
        influxDbReporter.start(reportIntervalMs, TimeUnit.MILLISECONDS);

        DropwizardConfig dropwizardConfig = new DropwizardConfig() {
            @Override
            public String prefix() {
                return null;
            }

            @Override
            public String get(String key) {
                return null;
            }
        };

        MeterRegistry registry2 = new DropwizardMeterRegistry(dropwizardConfig, registry, (id, convention) -> id.getName(), Clock.SYSTEM) {
            @Override
            protected Double nullGaugeValue() {
                return null;
            }
        };

        Timer timer = registry2.timer("Timer");
        timer.count();

        Counter counter = registry2.counter("dropwizard.counter");
        counter.increment();

        System.in.read();

    }

    private static String generateMeasurementsPrefix() {
        return "test-";
    }

}
