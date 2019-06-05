/**
 * 项目名称：quickstart-json 
 * 文件名：XmlEntity.java
 * 版本信息：
 * 日期：2017年12月14日
 * Copyright yangzl Corporation 2017
 * 版权所有 *
 */
package org.quickstart.json.jackson.v2;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * XmlEntity
 * 
 * @author：youngzil@163.com
 * @2017年12月14日 下午6:12:05
 * @since 1.0
 */
@JacksonXmlRootElement(localName = "root")
public class XmlEntity {

    @JacksonXmlProperty(localName = "name", isAttribute = true)
    @JacksonXmlCData(value = true)
    private String name;

    @JacksonXmlProperty(localName = "country")
    @JacksonXmlCData(value = true)
    private String country;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
