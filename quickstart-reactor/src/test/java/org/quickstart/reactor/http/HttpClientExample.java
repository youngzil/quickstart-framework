package org.quickstart.reactor.http;

import static reactor.netty.ConnectionObserver.State.CONFIGURED;
import static reactor.netty.ConnectionObserver.State.DISCONNECTING;
import static reactor.netty.ConnectionObserver.State.RELEASED;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.quickstart.reactor.http.commons.JsonUtil;
import org.quickstart.reactor.http.commons.PooledHttpClientConfiguration;

import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelOption;
import io.netty.channel.WriteBufferWaterMark;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import reactor.core.publisher.Mono;
import reactor.netty.ByteBufFlux;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019-08-18 20:41
 */
public class HttpClientExample {

  public static void main(String[] args) {

    final AtomicInteger total = new AtomicInteger(0);
    final AtomicInteger inUse = new AtomicInteger(0);

    PooledHttpClientConfiguration configuration = new PooledHttpClientConfiguration();

    String clientPoolName = "test-pool";
    final ConnectionProvider pooledConnectionProvider = ConnectionProvider.fixed(clientPoolName, configuration.getMaxConnectionsPerHost());

    String url = "http://localhost:8888/";
    URL endPoint = null;
    try {
      endPoint = new URL(url);
    } catch (MalformedURLException e) {
      e.getStackTrace();
    }
    URL finalEndPoint = endPoint;

    HttpMethod httpMethod = HttpMethod.POST;

    HttpHeaders httpHeaders = new DefaultHttpHeaders();
    httpHeaders.set("Authorization", "sss");

    Map<String, String> formData = new HashMap<>();
    formData.put("token", "accessToken");
    String httpBody = JsonUtil.getJsonFromMap(formData);

    int soTimeoutMills = 3000;

    HttpClient.create(pooledConnectionProvider)//

        .tcpConfiguration(tcpClient -> tcpClient//
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, configuration.getConnectTimeout())//
            .option(ChannelOption.WRITE_BUFFER_WATER_MARK,
                new WriteBufferWaterMark(configuration.getWriteBufferLowWaterMark(), configuration.getWriteBufferHighWaterMark()))//
            .option(ChannelOption.SO_KEEPALIVE, configuration.isSocketKeepAlive())//
            .doOnConnected(connection -> {//
              connection.addHandlerLast(new ReadTimeoutHandler(soTimeoutMills, TimeUnit.MILLISECONDS))//
                  .addHandlerLast(new WriteTimeoutHandler(soTimeoutMills, TimeUnit.MILLISECONDS));//
              total.incrementAndGet();//
            })//
            .doOnDisconnected(connection -> total.decrementAndGet())//
            .host(finalEndPoint.getHost())//
            .port(finalEndPoint.getPort()))// tcpConfiguration end

        .observe((connection, state) -> {
          // logger.debug("ClientPool[{}] Connection[{}] state has been changed to {}", pooledConnectionProvider, connection, state);
          if (state == CONFIGURED) {
            inUse.incrementAndGet();
          } else if (state == RELEASED || state == DISCONNECTING) {
            inUse.decrementAndGet();
          }
        })

        .wiretap(true)//
        .headers(h -> h.set(httpHeaders).set(HttpHeaderNames.USER_AGENT, "AiGw(Asiainfo PRD TA)/1.0"))//
        .request(httpMethod)//
        .uri(finalEndPoint.getPath())//
        .send(ByteBufFlux.fromString(Mono.just(httpBody), configuration.getCharset(), ByteBufAllocator.DEFAULT))//
        .responseSingle((httpClientResponse, byteBufMono) -> Mono.zip(Mono.just(httpClientResponse.status()),
            Mono.just(httpClientResponse.responseHeaders()), byteBufMono.asString(configuration.getCharset())))

        .toFuture();

  }

}
