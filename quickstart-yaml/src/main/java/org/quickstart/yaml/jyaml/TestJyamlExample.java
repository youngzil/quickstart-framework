/**
 * 项目名称：quickstart-yaml 
 * 文件名：TestJyamlExample.java
 * 版本信息：
 * 日期：2017年11月22日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.yaml.jyaml;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.ho.yaml.YamlDecoder;
import org.ho.yaml.YamlEncoder;

/**
 * TestJyamlExample
 * 
 * @author：youngzil@163.com
 * @2017年11月22日 下午5:31:39
 * @since 1.0
 */
public class TestJyamlExample {

    public static void main(String[] args) {

        System.out.println(TestJyamlExample.class.getResource("").getPath());

        File directory = new File(".");
        String path = null;
        try {
            path = directory.getCanonicalPath();// 获取当前路径
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println(path);

        String dumpFile = "Person.yaml";
        // FileOutputStream 以流的方式，将数据写入到YAML文件中。
        /* Output data structure into a YAML file as a FileOutputStream. */

        try {
            YamlEncoder yEncoder = new YamlEncoder(new FileOutputStream(dumpFile));
            for (int i = 0; i < 3; ++i) {
                Person person = new Person();
                person.setAge(24 + i);

                Person personSon = new Person();
                personSon.setAge(i);
                person.setSpouse(personSon);

                Person personSonSon = new Person();
                personSonSon.setAge(i + 1);
                personSon.setSpouse(personSonSon);

                yEncoder.writeObject(person);
                yEncoder.flush();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // FileInputStream 以流的方式，从YAML文件中将数据读入。
        /* Input a YAML file into data structure as a FileOutputStream. */
        try {
            YamlDecoder yDecoder = new YamlDecoder(new FileInputStream(dumpFile));
            Person[] persons = {new Person(), new Person(), new Person()};
            for (int i = 0; i < 3; ++i) {
                persons[i] = (Person) yDecoder.readObject();
                System.out.println(persons[i]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (EOFException e) {
            e.printStackTrace();
        }

    }

}
