package org.quickstart.metrics.micrometer;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.jvm.JvmMemoryMetrics;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import reactor.core.publisher.Flux;

public class JvmMemoryTest {

    public static void main(String[] args) {
        MeterRegistry registry = new SimpleMeterRegistry();
        new JvmMemoryMetrics().bindTo(registry);
        Flux.never().blockLast();
    }
}
