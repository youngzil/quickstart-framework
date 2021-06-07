package org.quickstart.metrics.micrometer.meter;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

public class CounterTest {

    public static void main(String[] args) {

        MeterRegistry registry = new SimpleMeterRegistry();

        // 构造器创建
        Counter counter = Counter.builder("http.request")//
            .baseUnit("num") // optional
            .description("a description of what this counter does") // optional
            .tags("uri", "/order/create") // optional
            .register(registry);
        counter.increment();

        // 直接从Registry创建
        Counter counter2 = registry.counter("http.request", "uri", "/order/create");
        counter2.increment();
    }
}
