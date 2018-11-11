/**
 * 项目名称：quickstart-dropwizard 
 * 文件名：HelloWorldResource.java
 * 版本信息：
 * 日期：2018年10月22日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.dropwizard.resources;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.quickstart.dropwizard.model.Saying;

import com.codahale.metrics.annotation.Timed;

/**
 * HelloWorldResource
 * 
 * @author：youngzil@163.com
 * @2018年10月22日 上午11:28:41
 * @since 1.0
 */
@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource {
    private final String template;
    private final String defaultName;
    private final AtomicLong counter;

    public HelloWorldResource(String template, String defaultName) {
        this.template = template;
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
    }

    @GET
    @Timed//增加注解@Timed，它将跟踪该服务的调用耗时与次数等信息，当然还有其他的Metrics组件可供选择。
    public Saying sayHello(@QueryParam("name") Optional<String> name) {
        final String value = String.format(template, name.orElse(defaultName));
        return new Saying(counter.incrementAndGet(), value);
    }
}
