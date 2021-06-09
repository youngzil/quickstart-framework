package org.quickstart.metrics.micrometer;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.jvm.ExecutorServiceMetrics;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static java.util.Collections.emptyList;

public class ExecutorServiceSample {
    public static void main(String[] args) {
        MeterRegistry registry = new SimpleMeterRegistry();
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        new ExecutorServiceMetrics(executorService, "executor.sample", emptyList()).bindTo(registry);

        executorService.scheduleWithFixedDelay(() -> Mono.delay(Duration.ofMillis(20)).block(), 0, 10, TimeUnit.MILLISECONDS);

        while (true) {
        }
    }
}
