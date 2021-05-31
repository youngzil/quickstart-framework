package org.quickstart.metrics.alibaba;

import com.alibaba.metrics.Meter;
import com.alibaba.metrics.MetricManager;
import com.alibaba.metrics.MetricName;

public class Test {

    public static void main(String[] args) {
        Meter methodMeter = MetricManager.getMeter("test", MetricName.build("myapp.mybiz.hello"));

        // your logic
        methodMeter.mark();

    }
}
