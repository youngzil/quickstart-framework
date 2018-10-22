/**
 * 项目名称：quickstart-dropwizard 
 * 文件名：Saying.java
 * 版本信息：
 * 日期：2018年10月22日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.dropwizard.model;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Saying
 * 
 * @author：yangzl@asiainfo.com
 * @2018年10月22日 下午3:01:51
 * @since 1.0
 */
public class Saying {
    private long id;

    @Length(max = 3)
    private String content;

    public Saying() {
        // Jackson deserialization
    }

    public Saying(long id, String content) {
        this.id = id;
        this.content = content;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    @JsonProperty
    public String getContent() {
        return content;
    }
}
