package org.quickstart.influxdb;

import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricFilter;
import com.codahale.metrics.MetricRegistry;
import com.izettle.metrics.influxdb.InfluxDbHttpSender;
import com.izettle.metrics.influxdb.InfluxDbReporter;
import com.izettle.metrics.influxdb.InfluxDbSender;
import org.junit.Test;
import org.quickstart.dropwizard.metrics.MeterTest;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class IzettleInfluxdbTest {

    @Test
    public void testIzettleInfluxdb() throws Exception {

        /**
         * 实例化一个MetricRegistry，是一个metrics的容器，维护一个MAP
         */
        final MetricRegistry registry = new MetricRegistry();
        //因为该类的一个属性final ConcurrentMap<String, Metric> metrics，在实际使用中做成单例就好
        /**
         * 实例化ConsoleReporter，输出
         */

        String reporterHost = "127.0.0.1";
        int reporterPort = 8086;
        String reporterDatabase = "testMetric";

        Map<String, String> tags = new HashMap<String, String>();
        tags.put("host", "localhost");
        tags.put("region", "mycluster");

        InfluxDbSender influxDbHttpSender =
            new InfluxDbHttpSender("http", reporterHost, reporterPort, reporterDatabase, "", TimeUnit.SECONDS, 5000,
                5000, generateMeasurementsPrefix());
        InfluxDbReporter reporter = InfluxDbReporter.forRegistry(registry).convertRatesTo(TimeUnit.SECONDS)
            .convertDurationsTo(TimeUnit.MILLISECONDS).filter(MetricFilter.ALL).skipIdleMetrics(false).withTags(tags)
            .build(influxDbHttpSender);

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

    private String generateMeasurementsPrefix() {
        return "test-";
    }
}
