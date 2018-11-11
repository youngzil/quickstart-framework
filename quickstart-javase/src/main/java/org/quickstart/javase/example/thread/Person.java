/**
 * 项目名称：quickstart-javase 
 * 文件名：Person.java
 * 版本信息：
 * 日期：2018年5月31日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.example.thread;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j;

/**
 * Person
 * 
 * @author：youngzil@163.com
 * @2018年5月31日 下午12:26:28
 * @since 1.0
 */
@ToString
@Setter@Getter

@Data
@Log4j
//@NoArgsConstructor
//@AllArgsConstructor
public class Person {

    Person(String name) {
        this.name = name;
    }

    private String name;
    
    public static void main(String[] args) {
        Person person = new Person("Java架构沉思录");
        person.setName("hehehe");
        String name = person.getName();
    }

}
