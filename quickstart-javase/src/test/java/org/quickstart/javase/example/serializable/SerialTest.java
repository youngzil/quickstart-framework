/**
 * 项目名称：quickstart-javase 
 * 文件名：SerialTest.java
 * 版本信息：
 * 日期：2017年7月26日
 * Copyright yangzl Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.example.serializable;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * SerialTest
 * 
 * @author：youngzil@163.com
 * @2017年7月26日 上午11:23:44
 * @version 2.0
 */
public class SerialTest {

    public static void main(String[] args) throws IOException {
        Person person = new Person(1234, "wang", "2");
        System.out.println("Person Serial" + person);
        FileOutputStream fos = new FileOutputStream("Person.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(person);
        oos.flush();
        oos.close();
    }
}
