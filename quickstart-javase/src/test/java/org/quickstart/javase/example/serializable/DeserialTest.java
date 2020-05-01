/**
 * 项目名称：quickstart-javase 
 * 文件名：DeserialTest.java
 * 版本信息：
 * 日期：2017年7月26日
 * Copyright yangzl Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.example.serializable;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * DeserialTest
 * 
 * @author：youngzil@163.com
 * @2017年7月26日 上午11:24:16
 * @version 2.0
 */
public class DeserialTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Person person;

        FileInputStream fis = new FileInputStream("Person.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        person = (Person) ois.readObject();
        ois.close();
        System.out.println("Person Deserial" + person);
    }

}
