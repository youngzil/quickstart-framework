package org.quickstart.metrics.micrometer.meter;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class TimerTest {

    public static void main(String[] args) {
        MeterRegistry registry = new SimpleMeterRegistry();

        // 构造器创建
        Timer timer = Timer.builder("my.timer")//
            .description("a description of what this timer does") // optional
            .tags("region", "test") // optional
            .register(registry);

        // 直接从Registry创建
        registry.timer("my.timer", "region", "test");

        // record directly
        timer.record(Duration.of(60L, ChronoUnit.SECONDS));
        // record function
        timer.record(() -> {
            // some operation ...
        });
        // wrap function
        timer.wrap(() -> {
            // some operation ...
        });
        // use sample
        Timer.Sample sample = Timer.start();
        // do something
        sample.stop(timer);
    }
}
