/**
 * 项目名称：quickstart-dropwizard 
 * 文件名：GreeterRestResource.java
 * 版本信息：
 * 日期：2018年10月22日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.dropwizard.rest;

import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.client.Client;

import com.codahale.metrics.annotation.Timed;

/**
 * GreeterRestResource
 * 
 * @author：youngzil@163.com
 * @2018年10月22日 下午7:24:07
 * @since 1.0
 */
@Path("/api")
public class GreeterRestResource {

    // Dropwizard提供了两种REST调用方式：HttpComponents和Jersey/JAX-RS，默认使用的后者，

    private String saying;
    private String backendServiceHost;
    private int backendServicePort;
    private Client client;

    public GreeterRestResource(final String saying, String host, int port, Client client) {
        this.saying = saying;
        this.backendServiceHost = host;
        this.backendServicePort = port;
        this.client = client;
    }

    @Path("/greeting/{bookId}")
    @GET
    @Timed
    public String greeting(@PathParam("bookId") Long bookId) {
        String backendServiceUrl = String.format("http://%s:%d", backendServiceHost, backendServicePort);

        Map map = client.target(backendServiceUrl).path("hola-backend")//
                .path("rest").path("books").path(bookId.toString())//
                .request().accept("application/json").get(Map.class);

        return map.toString();
    }
}
