package org.quickstart.metrics.micrometer;

import com.sun.net.httpserver.HttpServer;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.Timer;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import io.micrometer.prometheus.PrometheusConfig;
import io.micrometer.prometheus.PrometheusMeterRegistry;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class PrometheusTest {

    private static final Random R = new Random();

    static {
        Metrics.addRegistry(new SimpleMeterRegistry());
    }

    public static void main(String[] args) {

        // 启动后访问http://localhost:8090/prometheus

        // [Micrometer Prometheus](https://micrometer.io/docs/registry/prometheus)

        // 创建各类Meter的方式：
        // 1. Metrics
        // 2. MeterRegistry方式
        // 3. 构建方法Counter.builder

        Timer timer2 = Metrics.timer("timer2", "createOrder", "cost");
        timer2.record(() -> createOrder("test2"));

        PrometheusMeterRegistry prometheusRegistry = new PrometheusMeterRegistry(PrometheusConfig.DEFAULT);
        prometheusRegistry.config().commonTags("stack", "prod", "ip", "localhost");
        // prometheusRegistry.config().commonTags(Arrays.asList(Tag.of("stack", "prod"), Tag.of("region", "us-east-1"))); // equivalently

        Timer timer3 = prometheusRegistry.timer("timer3", "createOrder", "cost");
        timer3.record(() -> createOrder("test3"));

        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8090), 0);
            server.createContext("/prometheus", httpExchange -> {
                String response = prometheusRegistry.scrape();
                httpExchange.sendResponseHeaders(200, response.getBytes().length);
                try (OutputStream os = httpExchange.getResponseBody()) {
                    os.write(response.getBytes());
                }
            });

            Counter sendFaileCounter = Counter.builder("send_faile").description("send faile total").register(prometheusRegistry);
            Counter sendSuccessCounter = Counter.builder("send_success").description("send success total").register(prometheusRegistry);

            Timer timer = Timer//
                .builder("send_timer")//
                .description("send request time")// 可选
                .tags("region", "test") // 可选
                .register(prometheusRegistry);

            for (int i = 0; i < 100; i++) {
                sendSuccessCounter.increment();

                if (i % 3 == 0) {
                    sendFaileCounter.increment();
                }

                TimeUnit.SECONDS.sleep(2);


            }

            // Timer的使用还可以基于它的内部类Timer.Sample，通过start和stop两个方法记录两者之间的逻辑的执行耗时。
            // Timer.Sample sample = Timer.start(registry);
            // 这里做业务逻辑
            // Response response = ...
            // sample.stop(registry.timer("my.timer", "response", response.status()));


            new Thread(server::start).start();

            System.out.println("PrometheusTest started");

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    private static void createOrder(String param) {
        try {
            TimeUnit.SECONDS.sleep(R.nextInt(5)); //模拟方法耗时
        } catch (InterruptedException e) {
            //no-op
        }
    }

}
