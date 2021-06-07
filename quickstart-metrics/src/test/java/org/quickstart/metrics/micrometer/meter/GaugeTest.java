package org.quickstart.metrics.micrometer.meter;

import cern.jet.random.Normal;
import cern.jet.random.engine.MersenneTwister64;
import cern.jet.random.engine.RandomEngine;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class GaugeTest {

    public static void main(String[] args) {
        MeterRegistry registry = new SimpleMeterRegistry();

        AtomicLong n = new AtomicLong();

        // 构造器创建
        Gauge gauge = Gauge.builder("gauge", n, n2 -> n2.get() - 1)//
            .description("a description of what this gauge does") // optional
            .tags("region", "test") // optional
            .register(registry);

        // 直接从registry创建
        registry.gauge("listGauge", Tags.empty(), new ArrayList<>(), List::size);

        registry.gauge("gauge", Tags.of("k", "v"), n);
        registry.gauge("gauge", Tags.of("k", "v1"), n, n2 -> n2.get() - 1);

        RandomEngine r = new MersenneTwister64(0);
        Normal dist = new Normal(0, 10, r);

        Flux.interval(Duration.ofSeconds(5))//
            .doOnEach(d -> n.set(Math.abs(dist.nextInt())))//
            .blockLast();

    }
}
