/**
 * 项目名称：quickstart-dropwizard 
 * 文件名：HelloWorldApplication.java
 * 版本信息：
 * 日期：2018年10月22日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.dropwizard;

import javax.ws.rs.client.Client;

import org.quickstart.dropwizard.config.HelloWorldConfiguration;
import org.quickstart.dropwizard.health.TemplateHealthCheck;
import org.quickstart.dropwizard.resources.HelloWorldResource;
import org.quickstart.dropwizard.resources.HolaRestResourceV1;
import org.quickstart.dropwizard.resources.HolaRestResourceV2;
import org.quickstart.dropwizard.rest.GreeterRestResource;
import org.quickstart.dropwizard.rest.GreeterSayingFactory;
import org.quickstart.dropwizard.servlet.HelloServlet;

import io.dropwizard.Application;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * HelloWorldApplication
 * 
 * @author：youngzil@163.com
 * @2018年10月22日 上午11:27:57
 * @since 1.0
 */
public class HelloWorldApplication extends Application<HelloWorldConfiguration> {
    public static void main(String[] args) throws Exception {
        new HelloWorldApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
        /*helloapp:
            saying: ${HELLOAPP_SAYING:-Guten Tag aus}*/
        // 可以看到对saying的配置首先回去查看环境变量HELLOAPP_SAYING，如果该环境变量不存在，那么就会使用默认的Guten Tag aus，
        // 默认Dropwizard不会从环境变量中获取配置，如果需要让Dropwizard使用环境变量，需要做一些额外改动。打开HolaDropwizardApplication，编辑initialize()方法。
        bootstrap.setConfigurationSourceProvider(//
                new SubstitutingSourceProvider(bootstrap.getConfigurationSourceProvider(), //
                        new EnvironmentVariableSubstitutor(false)));
    }

    @Override
    public void run(HelloWorldConfiguration configuration, Environment environment) {
        // 设置jvm参数server example.yml

        final HelloWorldResource resource = new HelloWorldResource(configuration.getTemplate(), configuration.getDefaultName());
        final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);

        // 默认值：访问http://localhost:8080/hello-world
        // 不使用默认值：访问http://localhost:8080/hello-world?name=jasonlee
        environment.jersey().register(resource);

        // http://localhost:8080/test
        environment.getApplicationContext().addServlet(HelloServlet.class, "/test");

        // http://localhost:8080/api/holaV1
        environment.jersey().register(new HolaRestResourceV1());

        // http://localhost:8080/api/holaV2
        environment.jersey().register(new HolaRestResourceV2(//
                configuration.getSayingFactory().getSaying()));
        // 接下来停止应用，然后导出一个环境变量，再启动它，随后访问原来的页面http://localhost:8080/api/holaV1
        // 1、export HELLOAPP_SAYING="Hello Dropwizard2 @ " && echo $HELLOAPP_SAYING
        // 2、java -jar target/myapp.jar server myapp.yml

        // 验证
        // 默认值：访问http://localhost:8080/hello-world可以访问资源类发布的接口。
        // 不使用默认值：访问http://localhost:8080/hello-world?name=jasonlee可以访问资源类发布的接口。
        // servlet服务：访问http://localhost:8080/test可以访问servlet发布的服务。
        // 管理服务：访问http://localhost:8081可以查看metrics和health check等监控信息。
        // 打开http://localhost:8081/metrics?pretty=true，然后搜索sayHello

        // greeter service
        // Dropwizard提供了两种REST调用方式：HttpComponents和Jersey/JAX-RS，默认使用的后者，
        GreeterSayingFactory greeterSayingFactory = configuration.getGreeterSayingFactory();
        Client greeterClient = new JerseyClientBuilder(environment)//
                .using(greeterSayingFactory.getJerseyClientConfig()).build("greeterClient");

        // http://localhost:8080/api/greeting/1
        environment.jersey().register(new GreeterRestResource(//
                greeterSayingFactory.getSaying(), //
                greeterSayingFactory.getHost(), //
                greeterSayingFactory.getPort(), greeterClient));

    }

}
