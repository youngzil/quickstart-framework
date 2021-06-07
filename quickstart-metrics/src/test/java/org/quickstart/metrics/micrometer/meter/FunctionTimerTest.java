package org.quickstart.metrics.micrometer.meter;

import cern.jet.random.Normal;
import cern.jet.random.engine.MersenneTwister64;
import cern.jet.random.engine.RandomEngine;
import io.micrometer.core.instrument.FunctionTimer;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import io.micrometer.core.instrument.util.TimeUtils;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class FunctionTimerTest {

    public static void main(String[] args) {
        MeterRegistry registry = new SimpleMeterRegistry();

        /*Cache<String, Integer> guavaCache = CacheBuilder.newBuilder().maximumSize(1000)//
            .recordStats() // required
            .build();
        // suppose we have a Guava cache with stats recording on

        // 构造器创建
        FunctionTimer.builder("cache.gets.latency", guavaCache,//
            c -> c.getLocalMapStats().getGetOperationCount(),//计算数量
            c -> c.getLocalMapStats().getTotalGetLatency(),//计算时间
            TimeUnit.NANOSECONDS)//
            .tags("name", "guavaCache")//
            .description("Cache gets")//
            .register(registry);

        // 直接从Registry创建
        registry.more().timer("cache.gets.latency", guavaCache,//
            c -> c.getLocalMapStats().getGetOperationCount(),//计算数量
            c -> c.getLocalMapStats().getTotalGetLatency(),//计算时间
            TimeUnit.NANOSECONDS);*/

        Timer timer = Timer.builder("timer")//
            .publishPercentiles(0.5, 0.95)//
            .register(registry);

        Object placeholder = new Object();
        AtomicLong totalTimeNanos = new AtomicLong();
        AtomicLong totalCount = new AtomicLong();

        FunctionTimer.builder("ftimer", placeholder, p -> totalCount.get(), p -> totalTimeNanos.get(), TimeUnit.NANOSECONDS).register(registry);

        RandomEngine r = new MersenneTwister64(0);
        Normal incomingRequests = new Normal(0, 1, r);
        Normal duration = new Normal(250, 50, r);

        AtomicInteger latencyForThisSecond = new AtomicInteger(duration.nextInt());
        Flux.interval(Duration.ofSeconds(1))//
            .doOnEach(d -> latencyForThisSecond.set(duration.nextInt()))//
            .subscribe();

        // the potential for an "incoming request" every 10 ms
        Flux.interval(Duration.ofMillis(10))//
            .doOnEach(d -> {
                if (incomingRequests.nextDouble() + 0.4 > 0) {
                    // pretend the request took some amount of time, such that the time is
                    // distributed normally with a mean of 250ms
                    timer.record(latencyForThisSecond.get(), TimeUnit.MILLISECONDS);
                    totalCount.incrementAndGet();
                    totalTimeNanos.addAndGet((long)TimeUtils.millisToUnit(latencyForThisSecond.get(), TimeUnit.NANOSECONDS));
                }
            })//
            .blockLast();

    }
}
