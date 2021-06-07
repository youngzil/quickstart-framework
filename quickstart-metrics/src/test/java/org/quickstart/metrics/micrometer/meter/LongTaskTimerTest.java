package org.quickstart.metrics.micrometer.meter;

import io.micrometer.core.instrument.LongTaskTimer;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

public class LongTaskTimerTest {

    public static void main(String[] args) {
        MeterRegistry registry = new SimpleMeterRegistry();

        // 构造器创建
        LongTaskTimer longTaskTimer = LongTaskTimer
            .builder("long.task.timer")//
            .description("a description of what this timer does") // optional
            .tags("region", "test") // optional
            .register(registry);

        // 直接从Registry创建
        LongTaskTimer scrapeTimer = registry.more().longTaskTimer("scrape");

        // record function
        scrapeTimer.record(() -> {
            // some operation ...
        });

        // record by sample
        LongTaskTimer.Sample start = scrapeTimer.start();
        // do something
        start.stop();
    }
}
