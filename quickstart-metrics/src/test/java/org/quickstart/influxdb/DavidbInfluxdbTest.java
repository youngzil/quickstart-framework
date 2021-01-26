package org.quickstart.influxdb;

import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricFilter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.ScheduledReporter;
import metrics_influxdb.HttpInfluxdbProtocol;
import metrics_influxdb.InfluxdbReporter;
import metrics_influxdb.api.measurements.CategoriesMetricMeasurementTransformer;
import org.junit.Test;
import org.quickstart.dropwizard.metrics.MeterTest;

import java.util.concurrent.TimeUnit;

public class DavidbInfluxdbTest {

    @Test
    public void testInflux() throws InterruptedException {

        /**
         * 实例化一个MetricRegistry，是一个metrics的容器，维护一个MAP
         */
        final MetricRegistry registry = new MetricRegistry();
        //因为该类的一个属性final ConcurrentMap<String, Metric> metrics，在实际使用中做成单例就好
        /**
         * 实例化ConsoleReporter，输出
         */
        //        ScheduledReporter reporter = InfluxdbReporter.forRegistry(registry).build();
        /*
        使用的是默认的配置
        protocol: HTTP
        server: 127.0.0.1
        port: 8086
        authentication: none
        database name: metrics
        rates: converted to TimeUnit.SECONDS
        duration: converted to TimeUnit.MILLISECONDS
        idle metrics: do not report
        influxdb protocol: v09 line protocol*/

        String serverIP = "127.0.0.1";
        final ScheduledReporter reporter = InfluxdbReporter.forRegistry(registry)//
            .protocol(new HttpInfluxdbProtocol("http", "influxdb-server", 8086, "admin", "53CR3TP455W0RD", "metrics"))//
            .convertRatesTo(TimeUnit.SECONDS)//
            .convertDurationsTo(TimeUnit.MILLISECONDS)//
            .filter(MetricFilter.ALL)//
            .skipIdleMetrics(false)//
            .tag("cluster", "CL01")//
            .tag("client", "OurImportantClient")//
            .tag("server", serverIP)//
            .transformer(new CategoriesMetricMeasurementTransformer("module", "artifact"))//
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
