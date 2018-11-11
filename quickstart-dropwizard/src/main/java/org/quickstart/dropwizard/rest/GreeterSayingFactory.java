/**
 * 项目名称：quickstart-dropwizard 
 * 文件名：GreeterSayingFactory.java
 * 版本信息：
 * 日期：2018年10月22日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.dropwizard.rest;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.client.JerseyClientConfiguration;

/**
 * GreeterSayingFactory
 * 
 * @author：youngzil@163.com
 * @2018年10月22日 下午7:21:56
 * @since 1.0
 */
public class GreeterSayingFactory {
    @NotEmpty
    private String saying;
    @NotEmpty
    private String host;
    @NotEmpty
    private int port;
    private JerseyClientConfiguration jerseyClientConfig = new JerseyClientConfiguration();

    @JsonProperty("jerseyClient")
    public JerseyClientConfiguration getJerseyClientConfig() {
        return jerseyClientConfig;
    }

    public String getSaying() {
        return saying;
    }

    public void setSaying(String saying) {
        this.saying = saying;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;

    }

    public void setPort(int port) {
        this.port = port;
    }
}
