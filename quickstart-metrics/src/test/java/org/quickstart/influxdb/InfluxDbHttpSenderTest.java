package org.quickstart.influxdb;

import com.izettle.metrics.influxdb.InfluxDbHttpSender;
import com.izettle.metrics.influxdb.InfluxDbSender;
import com.izettle.metrics.influxdb.data.InfluxDbPoint;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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

        influxDbSender.flush(); //不管发送结果如何都需要清空cache的数据，否则内存会溢出

    }

    private String generateMeasurementsPrefix() {
        return "test-";
    }
}
