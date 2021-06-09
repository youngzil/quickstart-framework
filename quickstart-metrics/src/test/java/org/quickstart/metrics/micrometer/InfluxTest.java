package org.quickstart.metrics.micrometer;

import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.influx.InfluxConfig;
import io.micrometer.influx.InfluxMeterRegistry;

import java.io.IOException;
import java.time.Duration;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class InfluxTest {

    // [Micrometer Influx](https://micrometer.io/docs/registry/influx)

    public static void main(String[] args) throws IOException {

        InfluxConfig config = new InfluxConfig() {
            @Override
            public Duration step() {
                return Duration.ofSeconds(10);
            }

            @Override
            public String db() {
                return "mydb";
            }

            @Override
            public String get(String k) {
                return null; // accept the rest of the defaults
            }

            @Override
            public String uri() {
                return "http://localhost:8086";
            }
        };
        MeterRegistry registry = new InfluxMeterRegistry(config, Clock.SYSTEM);

        Counter counter = registry.counter("micrometer.counter", "host", "localhost");

        for (int i = 0; i < 10; i++) {
            counter.increment();
        }

        System.in.read();

    }

    public static void main2(String[] args) throws InterruptedException {
        InfluxMeterRegistry registry = InfluxMeterRegistry.builder(new InfluxConfig() {
            @Override
            public String db() {
                return "db0";
            }

            @Override
            public String userName() {
                return "your user name";
            }

            @Override
            public String password() {
                return "your password";
            }

            @Override
            public String uri() {
                return "http://your influx db http url";
            }

            @Override
            public Duration step() {
                return Duration.ofSeconds(10);
            }

            @Override
            public String get(String key) {
                return null;
            }
        }).build();

        Metrics.addRegistry(registry);

        Counter counter = Metrics.counter("test.random.count", "tag1", "val1", "tag2", "val2");
        Random random = new Random();

        System.out.println("Start run ...");

        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(() -> {
            System.out.println("Add counter ...");
            counter.increment(random.nextInt(100));
        }, 2L, 2L, TimeUnit.SECONDS);

        LockSupport.parkUntil(System.currentTimeMillis() + 101 * 1000);

        TimeUnit.SECONDS.sleep(101 * 1000);

        System.out.println("Run over ...");
        executor.shutdown();

        registry.close();
    }

}
