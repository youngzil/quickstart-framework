package org.quickstart.reactor.http;

import java.io.IOException;

import reactor.netty.http.server.HttpServer;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019-08-18 20:24
 */
public class HttpServerStart {

  public static void main(String[] args) throws IOException {

    HttpServer.create() // Prepares an HTTP server ready for configuration
        // .port(0) // Configures the port number as zero, this will let the system pick up
        .port(8090) // Configures the port number as zero, this will let the system pick up
        // an ephemeral port when binding the server
        .route(routes ->
        // The server will respond only on POST requests
        // where the path starts with /test and then there is path parameter
        routes.post("/test/{param}", (request, response) -> response//
            .sendString(request.receive().asString().map(s -> {//
              return s + ' ' + request.param("param") + '!';
            }).log("http-server"))))
        .bindNow(); // Starts the server in a blocking fashion, and waits for it to finish its initialization

    System.in.read();
  }

}
