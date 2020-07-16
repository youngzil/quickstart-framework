package com.quickstart.xml.xstream.example;

/**
 * @description TODO
 *
 * @author yangzl
 * @createTime 2020/7/16 15:28
 */
import lombok.Data;

import java.util.List;

/**
 * This is the User class
 *
 * @author: lvxiaobu
 * @createDate: 2018-09-18 14:44
 */
@Data
public class User {
    private String name;
    private String age;
    private List<Customer> customer;
}
