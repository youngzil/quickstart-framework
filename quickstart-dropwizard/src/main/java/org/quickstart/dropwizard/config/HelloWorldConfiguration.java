/**
 * 项目名称：quickstart-dropwizard 
 * 文件名：HelloWorldConfiguration.java
 * 版本信息：
 * 日期：2018年10月22日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.dropwizard.config;

import org.hibernate.validator.constraints.NotEmpty;
import org.quickstart.dropwizard.rest.GreeterSayingFactory;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;

/**
 * HelloWorldConfiguration
 * 
 * @author：yangzl@asiainfo.com
 * @2018年10月22日 上午11:24:55
 * @since 1.0
 */
public class HelloWorldConfiguration extends Configuration {
    @NotEmpty
    private String template;

    @NotEmpty
    private String defaultName = "Stranger";

    @JsonProperty
    public String getTemplate() {
        return template;
    }

    @JsonProperty
    public void setTemplate(String template) {
        this.template = template;
    }

    @JsonProperty
    public String getDefaultName() {
        return defaultName;
    }

    @JsonProperty
    public void setDefaultName(String name) {
        this.defaultName = name;
    }

    private HelloSayingFactory sayingFactory;

    @JsonProperty("helloapp")
    public HelloSayingFactory getSayingFactory() {
        return sayingFactory;
    }

    @JsonProperty("helloapp")
    public void setSayingFactory(HelloSayingFactory sayingFactory) {
        this.sayingFactory = sayingFactory;
    }
    
    private GreeterSayingFactory greeterSayingFactory;
    
    @JsonProperty("greeter")
    public GreeterSayingFactory getGreeterSayingFactory() {
        return greeterSayingFactory;
    }

    @JsonProperty("greeter")
    public void setGreeterSayingFactory(
            GreeterSayingFactory greeterSayingFactory) {
        this.greeterSayingFactory = greeterSayingFactory;
    }
}
