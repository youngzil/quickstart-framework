package org.quickstart.metrics.micrometer.meter;

import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

public class DistributionSummaryTest {

    public static void main(String[] args) {
        MeterRegistry registry = new SimpleMeterRegistry();

        // 构造器创建
        DistributionSummary summary = DistributionSummary
            .builder("response.size")//
            .description("a description of what this summary does") // optional
            .baseUnit("bytes") // optional (1)
            .tags("region", "test") // optional
            .scale(100) // optional (2)
            .register(registry);

        // 直接从registry创建
        DistributionSummary summary2 = registry.summary("response.size");
    }
}
