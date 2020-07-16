package com.quickstart.xml.jaxb.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @description TODO
 *
 * @author yangzl
 * @createTime 2020/7/16 15:20
 */
@XmlRootElement(name = "employee")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "emp", namespace = "aa", propOrder = {"id", "name", "sex", "age"})
public class People {
    public String id = "001";
    public String name = "张三";
    public String sex = "男";
    private String age = "30";
}
