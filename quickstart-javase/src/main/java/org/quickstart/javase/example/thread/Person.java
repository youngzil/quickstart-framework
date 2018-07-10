/**
 * 项目名称：quickstart-javase 
 * 文件名：Person.java
 * 版本信息：
 * 日期：2018年5月31日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.example.thread;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Person
 * 
 * @author：yangzl@asiainfo.com
 * @2018年5月31日 下午12:26:28
 * @since 1.0
 */
@ToString
@Setter@Getter
public class Person {

    Person(String name) {
        this.name = name;
    }

    private String name;

}
