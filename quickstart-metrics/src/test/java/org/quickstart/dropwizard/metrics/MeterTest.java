package org.quickstart.dropwizard.metrics;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;

import java.util.concurrent.TimeUnit;

/**
 * Meter
 * 作用：度量速率(例如，tps)
 * Meters会统计最近1分钟(m1)，5分钟(m5)，15分钟(m15)，还有全部时间的速率（速率就是平均值）。
 */
public class MeterTest {
    public static void main(String[] args) throws InterruptedException {
        /**
         * 实例化一个MetricRegistry，是一个metrics的容器，维护一个MAP
         */
        final MetricRegistry registry = new MetricRegistry();
        //因为该类的一个属性final ConcurrentMap<String, Metric> metrics，在实际使用中做成单例就好
        /**
         * 实例化ConsoleReporter，输出
         */
        ConsoleReporter reporter = ConsoleReporter.forRegistry(registry)//
            .convertRatesTo(TimeUnit.SECONDS)//
            .convertDurationsTo(TimeUnit.MILLISECONDS)//
            .build();
        reporter.start(3, TimeUnit.SECONDS);//从启动后的3s后开始（所以通常第一个计数都是不准的，从第二个开始会越来越准），每隔3秒从MetricRegistry钟poll一次数据

        //实例化一个Meter并注册到容器中去
        Meter meterTps = registry.meter(MetricRegistry.name(MeterTest.class, "request", "tps"));

        //将该Meter类型的指定name的metric加入到MetricsRegistry中去
        System.out.println("执行与业务逻辑");
        //模拟数据
        while (true) {
            meterTps.mark();//总数以及m1,m5,m15的数据都+1
            Thread.sleep(500);
        }

    }
}
