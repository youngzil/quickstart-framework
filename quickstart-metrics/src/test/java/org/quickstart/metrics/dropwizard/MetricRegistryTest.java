package org.quickstart.metrics.dropwizard;

import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.SharedMetricRegistries;

public class MetricRegistryTest {

    public static void main(String[] args) {
        MetricRegistry metricRegistry = new MetricRegistry();

        Meter meter1 = new Meter();
        metricRegistry.register("meter1", meter1);

        Meter meter2 = metricRegistry.meter("meter2");

        String name1 = MetricRegistry.name(MetricRegistryTest.class, "request", "count");
        String name2 = MetricRegistry.name("CustomFilter", "response", "count");

        Meter requestMeterTps = metricRegistry.meter(name1);
        Meter responseMeterTps = metricRegistry.meter(name1);

        SharedMetricRegistries.add("default", metricRegistry);
        MetricRegistry retrievedMetricRegistry = SharedMetricRegistries.getOrCreate("default");
        SharedMetricRegistries.remove("default");

    }
}
