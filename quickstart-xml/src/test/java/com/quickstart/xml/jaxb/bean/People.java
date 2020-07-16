package com.quickstart.xml.jaxb.bean;

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
@XmlRootElement(name="employee")
public class People{
    public String id;
    public String name;
    public String sex;
    public String age;

    public String toString(){
        return this.id+","+this.name+","+this.sex+","+this.age;
    }
}
