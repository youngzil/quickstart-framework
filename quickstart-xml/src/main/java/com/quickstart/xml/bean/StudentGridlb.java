package com.quickstart.xml.bean;

import lombok.Data;

import java.util.Date;

/**
 * @description TODO
 *
 * @author yangzl
 * @createTime 2020/7/14 16:55
 */
@Data
public class StudentGridlb {
    private String stu_id;
    private int stu_age;
    private String stu_name;
    private Date stu_birthday;
}
