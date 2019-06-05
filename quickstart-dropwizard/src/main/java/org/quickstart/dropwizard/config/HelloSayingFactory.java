/**
 * 项目名称：quickstart-dropwizard 
 * 文件名：HelloSayingFactory.java
 * 版本信息：
 * 日期：2018年10月22日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.dropwizard.config;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * HelloSayingFactory 
 *  
 * @author：youngzil@163.com
 * @2018年10月22日 下午5:25:08 
 * @since 1.0
 */
public class HelloSayingFactory {
    @NotEmpty
    private String saying;

    @JsonProperty
    public String getSaying() {
        return saying;
    }

    @JsonProperty
    public void setSaying(String saying) {
        this.saying = saying;
    }

}
