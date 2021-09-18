package org.quickstart.metrics.dropwizard.influxdb;

import com.codahale.metrics.Counter;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricFilter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import com.izettle.metrics.influxdb.InfluxDbHttpSender;
import com.izettle.metrics.influxdb.InfluxDbReporter;
import com.izettle.metrics.influxdb.InfluxDbSender;
import com.izettle.metrics.influxdb.data.InfluxDbPoint;
import com.izettle.metrics.influxdb.tags.Transformer;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class InfluxDbHttpSenderTest {

    @Test
    public void testSend() throws Exception {
        String reporterHost = "127.0.0.1";
        int reporterPort = 8086;
        String reporterDatabase = "testMetric";

        InfluxDbSender influxDbSender = new InfluxDbHttpSender("http", reporterHost, reporterPort, reporterDatabase, "", TimeUnit.SECONDS, 5000, 5000,
            generateMeasurementsPrefix());

        influxDbSender.flush();

        long now = System.currentTimeMillis();
        Map<String, String> tags = new HashMap<>();
        Map<String, Object> fields = new HashMap<>();

        influxDbSender.appendPoints(new InfluxDbPoint("testmeasurement", tags, now, fields));

        if (influxDbSender.hasSeriesData()) {
            influxDbSender.writeData();
        }

        influxDbSender.flush(); // 不管发送结果如何都需要清空cache的数据，否则内存会溢出
    }

    @Test
    public void testInfluxDbHttpSender() throws Exception {

        String reporterHost = "127.0.0.1";
        int reporterPort = 8086;
        String reporterDatabase = "testMetric";
        int reportIntervalMs = 5000;

        Map<String, String> tags = new HashMap<>();
        MetricRegistry registry = new MetricRegistry();

        // JmxReporter jmxReporter = JmxReporter.forRegistry(this.registry).build();
        // jmxReporter.start();

        InfluxDbSender influxDbHttpSender = new InfluxDbHttpSender("http", //
            reporterHost, //
            reporterPort, //
            reporterDatabase, //
            "", //
            TimeUnit.SECONDS, //
            5000, //
            5000,//
            generateMeasurementsPrefix());

        Map<String, String> measurementMappings = new HashMap<>();
        measurementMappings.put("Meter", "Meter.+");

        InfluxDbReporter influxDbReporter =
            InfluxDbReporter.forRegistry(registry)//
                .convertRatesTo(TimeUnit.SECONDS)//
                .convertDurationsTo(TimeUnit.MILLISECONDS)//
                .filter(MetricFilter.ALL)//
                .skipIdleMetrics(false)//
                .withTags(tags)//
                .measurementMappings(measurementMappings)//measurement修改的
                .tagsTransformer(new ComsumerTransformer())//tag修改的
                .build(influxDbHttpSender);

        influxDbReporter.start(reportIntervalMs, TimeUnit.MILLISECONDS);

        Timer timer = registry.timer("Timer");
        Timer.Context start = timer.time();
        TimeUnit.SECONDS.sleep(10);
        start.stop();

        Meter meter = registry.meter("Meter.app1.topicTest1.group1");
        Meter meter2 = registry.meter("Meter.app2.topicTest2.group2");

        for (int i = 0; i < 50; ++i) {
            meter.mark();
            meter2.mark(10);
        }

        // meter.mark();
        // meter.mark(10);

        Counter counter = registry.counter("Counter");
        counter.inc();
        // counter.inc(10);

        TimeUnit.SECONDS.sleep(100);
    }

    class ComsumerTransformer implements Transformer {

        @Override
        public Map<String, String> getTags(String metricName) {
            Map<String, String> tags = new HashMap<>();
            tags.put("metricName", metricName);
            String[] metrics = metricName.split("\\.");
            if (metrics.length >= 4) {
                tags.put("appName", metrics[1]);
                tags.put("topic", metrics[2]);
                tags.put("groupId", metrics[3]);
            }
            return tags;
        }
    }





    public static void main(String[] args) {
        String metricName = "Meter.app1.topicTest1.group1";
        String[] metrics = metricName.split(".");
        String[] metrics2 = metricName.split("\\.");
        System.out.println(Pattern.compile("test.+").matcher("testddddd").matches());
    }

  /*  private String getMeasurementName(final String name) {
    for (Map.Entry<String, Pattern> entry : measurementMappings.entrySet()) {
      final Pattern pattern = entry.getValue();

      if (pattern.matcher(name).matches()) {
        return entry.getKey();
      }
    }
    return name;
  }*/

    @Test
    public void testIzettleInfluxdb() throws Exception {

        /** 实例化一个MetricRegistry，是一个metrics的容器，维护一个MAP */
        final MetricRegistry registry = new MetricRegistry();
        // 因为该类的一个属性final ConcurrentMap<String, Metric> metrics，在实际使用中做成单例就好
        /** 实例化ConsoleReporter，输出 */
        String reporterHost = "127.0.0.1";
        int reporterPort = 8086;
        String reporterDatabase = "testMetric";

        Map<String, String> tags = new HashMap<String, String>();
        tags.put("host", "localhost");
        tags.put("region", "mycluster");

        InfluxDbSender influxDbHttpSender =
            new InfluxDbHttpSender("http", reporterHost, reporterPort, reporterDatabase, "", TimeUnit.SECONDS, 5000, 5000,
                generateMeasurementsPrefix());
        InfluxDbReporter reporter =
            InfluxDbReporter.forRegistry(registry).convertRatesTo(TimeUnit.SECONDS).convertDurationsTo(TimeUnit.MILLISECONDS).filter(MetricFilter.ALL)
                .skipIdleMetrics(false).withTags(tags).build(influxDbHttpSender);

        reporter.start(3, TimeUnit.SECONDS); // 从启动后的3s后开始（所以通常第一个计数都是不准的，从第二个开始会越来越准），每隔3秒从MetricRegistry钟poll一次数据

        // 实例化一个Meter并注册到容器中去
        Meter meterTps = registry.meter(MetricRegistry.name(InfluxDbHttpSenderTest.class, "request", "tps")); // 使用类的全路径作为measurement
        // Meter meterTps = registry.meter(MetricRegistry.name("testtest", "request", "tps"));

        // 将该Meter类型的指定name的metric加入到MetricsRegistry中去
        System.out.println("执行与业务逻辑");
        // 模拟数据
        while (true) {
            meterTps.mark(); // 总数以及m1,m5,m15的数据都+1
            Thread.sleep(500);
        }
    }

    private String generateMeasurementsPrefix() {
        return "test-";
    }

}
