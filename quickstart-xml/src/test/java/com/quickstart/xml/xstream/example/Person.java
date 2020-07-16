/**
 * 项目名称：quickstart-xml 
 * 文件名：Person.java
 * 版本信息：
 * 日期：2018年5月20日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package com.quickstart.xml.xstream.example;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Person 
 *  
 * @author：youngzil@163.com
 * @2018年5月20日 下午11:18:52 
 * @since 1.0
 */
@Getter
@Setter
public class Person {  
    //姓名
    private String name;  
    //性别  
    private String sex;  
    //年龄  
    private int age;  
    //地址  
    private List<Address> Address;  
}