/**
 * 项目名称：quickstart-xml 
 * 文件名：Address.java
 * 版本信息：
 * 日期：2018年5月20日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package com.quickstart.xml.xstream.example;

import lombok.Getter;
import lombok.Setter;

/**
 * Address 
 *
 * @author：youngzil@163.com
 * @2018年5月20日 下午11:18:32 
 * @since 1.0
 */
@Getter
@Setter
public class Address {
    //国
    private String country;
    //省  
    private String province;
    //市  
    private String city;
    //县  
    private String county;
    //镇  
    private String town;
}
