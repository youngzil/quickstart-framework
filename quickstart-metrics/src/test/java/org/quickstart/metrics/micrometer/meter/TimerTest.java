package org.quickstart.metrics.micrometer.meter;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import io.micrometer.core.instrument.Timer;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

public class TimerTest {

    public static void main(String[] args) throws InterruptedException {
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
        // Timer.Sample sample = Timer.start();
        Timer.Sample sample = Timer.start(registry);
        // do something
        sample.stop(timer);

        Timer.Sample sample2 = Timer.start(registry);
        try {
            TimeUnit.SECONDS.sleep(5);
        } finally {
            sample2.stop(Timer.builder("timer.sample")//
                .description("timer sample description")//
                .tags(Tags.empty())//
                .publishPercentileHistogram(true)//
                .publishPercentiles(null)//
                .register(registry));
        }

    }

}
