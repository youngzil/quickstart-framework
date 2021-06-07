package org.quickstart.metrics.micrometer.meter;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.FunctionCounter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import reactor.core.publisher.Flux;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import static io.netty.buffer.Unpooled.wrappedBuffer;

public class FunctionCounterTest {

    public static void main(String[] args) {
        MeterRegistry registry = new SimpleMeterRegistry();
        AtomicInteger n = new AtomicInteger();

        // 构造器创建
        FunctionCounter.builder("my.fcounter", n, AtomicInteger::get)//
            .baseUnit("happiness")//
            .description("A counter derived from a monotonically increasing value")//
            .register(registry);

        Counter counter = Counter.builder("my.counter")//
            .baseUnit("happiness")//
            .description("A normal counter")//
            .register(registry);

        Flux.interval(Duration.ofMillis(10)).doOnEach(i -> {
            n.incrementAndGet();
            counter.increment();
        }).blockLast();

        // 直接从Registry创建

        Cache<String, Integer> guavaCache = CacheBuilder.newBuilder().maximumSize(1000)//
            .recordStats() // required
            .build();
        // suppose we have a Guava cache with stats recording on
        registry.more().counter("evictions", Tags.empty(), guavaCache, c -> c.stats().evictionCount());

        // read all of Frankenstein
        HttpClient.create()//
            .baseUrl("www.gutenberg.org")//
            .doOnRequest((req, conn) -> conn.addHandler(wordDecoder()))//
            .get()//
            .uri("/files/84/84-0.txt")//
            .responseContent()//
            .asString()//
            .delayElements(Duration.ofMillis(10)) // one word per 10 ms
            .filter(word -> !word.isEmpty())//
            .doOnNext(word -> {
                if (guavaCache.getIfPresent(word) == null) {
                    guavaCache.put(word, 1);
                }
            }).blockLast();

    }

    // skip things that aren't words, roughly
    private static DelimiterBasedFrameDecoder wordDecoder() {
        return new DelimiterBasedFrameDecoder(256,//
            IntStream.of('\r', '\n', ' ', '\t', '.', ',', ';', ':', '-')//
                .mapToObj(delim -> wrappedBuffer(new byte[] {(byte)delim}))//
                .toArray(ByteBuf[]::new));
    }
}
