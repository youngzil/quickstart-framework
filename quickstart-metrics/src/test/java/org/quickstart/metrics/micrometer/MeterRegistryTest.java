package org.quickstart.metrics.micrometer;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.composite.CompositeMeterRegistry;
import io.micrometer.core.instrument.config.NamingConvention;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.junit.Test;

public class MeterRegistryTest {

    @Test
    public void testCompositeMeterRegistry() {
        CompositeMeterRegistry composite = new CompositeMeterRegistry();

        Counter compositeCounter = composite.counter("counter");
        compositeCounter.increment(); // <- 实际上这一步操作是无效的,但是不会报错

        SimpleMeterRegistry simple = new SimpleMeterRegistry();
        composite.add(simple);  // <- 向CompositeMeterRegistry实例中添加SimpleMeterRegistry实例

        compositeCounter.increment();  // <-计数成功

    }

    @Test
    public void testGlobalRegistry() {
        Metrics.addRegistry(new SimpleMeterRegistry());
        Counter counter = Metrics.counter("counter", "tag-1", "tag-2");
        counter.increment();
    }

    @Test
    public void testNamingConvention() {
        // 其实NamingConvention已经提供了5种默认的转换规则：dot、snakeCase、camelCase、upperCamelCase和slashes。

        MeterRegistry registry = new SimpleMeterRegistry();

        registry.config().namingConvention(NamingConvention.identity);

        Counter counter = Metrics.counter("counter", "tag-1", "tag-2");
        counter.increment();
    }

}
