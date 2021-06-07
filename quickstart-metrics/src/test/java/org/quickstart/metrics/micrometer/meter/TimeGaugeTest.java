package org.quickstart.metrics.micrometer.meter;

import cern.jet.random.Normal;
import cern.jet.random.engine.MersenneTwister64;
import cern.jet.random.engine.RandomEngine;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import io.micrometer.core.instrument.TimeGauge;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class TimeGaugeTest {

    public static void main(String[] args) {
        MeterRegistry registry = new SimpleMeterRegistry();

        AtomicInteger count = new AtomicInteger();
        // 构造器创建
        TimeGauge timeGauge = TimeGauge.builder("timeGauge", count, TimeUnit.SECONDS, AtomicInteger::get)//
            .tag("tagkey", "tagVal")//
            .register(registry);

        // 直接从registry创建
        TimeGauge g = registry.more().timeGauge("my.time.gauge", Tags.empty(), count, TimeUnit.SECONDS, AtomicInteger::doubleValue);

        System.out.println(g.getId().getBaseUnit());

        RandomEngine r = new MersenneTwister64(0);
        Normal dist = new Normal(0, 10, r);

        Flux.interval(Duration.ofSeconds(5))//
            .doOnEach(d -> count.set(Math.abs(dist.nextInt())))//
            .blockLast();

    }
}
