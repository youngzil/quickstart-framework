package org.quickstart.metrics.micrometer;

import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.micrometer.core.instrument.Clock;
import io.micrometer.core.ipc.http.HttpUrlConnectionSender;
import io.micrometer.datadog.DatadogConfig;
import io.micrometer.datadog.DatadogMeterRegistry;
import io.vavr.CheckedFunction0;
import io.vavr.control.Try;

import java.time.Duration;

public class HttpSenderResilience4jRetry {

    // [HttpSender with Resilience4j retry](https://micrometer.io/docs/guide/httpSenderResilience4jRetry)

    public static DatadogMeterRegistry datadogMeterRegistry(DatadogConfig datadogConfig, Clock clock) {
        return DatadogMeterRegistry.builder(datadogConfig)//
            .clock(clock)//
            .httpClient(new RetryHttpClient())//
            .build();
    }

    private static class RetryHttpClient extends HttpUrlConnectionSender {

        private final RetryConfig retryConfig = RetryConfig.custom()//
            .maxAttempts(2)//
            .waitDuration(Duration.ofSeconds(5))//
            .build();

        private final Retry retry = Retry.of("datadog-metrics", this.retryConfig);

        @Override
        public Response send(Request request) {
            CheckedFunction0<Response> retryableSupplier = Retry.decorateCheckedSupplier(//
                this.retry,//
                () -> super.send(request));
            return Try.of(retryableSupplier).get();
        }

    }

}
