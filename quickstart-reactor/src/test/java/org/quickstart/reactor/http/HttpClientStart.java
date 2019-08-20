package org.quickstart.reactor.http;

import reactor.core.publisher.Flux;
import reactor.netty.ByteBufFlux;
import reactor.netty.http.client.HttpClient;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019-08-18 20:26
 */
public class HttpClientStart {

  public static void main(String[] args) {
    HttpClient.create() // Prepares an HTTP client ready for configuration
        .port(9001) // Obtains the server's port and provides it as a port to which this
        // client should connect
        .post() // Specifies that POST method will be used
        .uri("/oauth2-server/oauth/check_token?token=1234") // Specifies the path
        .send(ByteBufFlux.fromString(Flux.just("{\"token\": \"appCodeTest\"}"))) // Sends the request body
        .responseContent() // Receives the response body
        .aggregate().asString().log("http-client").block();
  }

}
