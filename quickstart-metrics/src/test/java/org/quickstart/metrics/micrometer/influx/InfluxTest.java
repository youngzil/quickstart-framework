package org.quickstart.metrics.micrometer.influx;

import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.influx.InfluxConfig;
import io.micrometer.influx.InfluxMeterRegistry;

import java.io.IOException;
import java.time.Duration;

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

}
