/**
 * 项目名称：quickstart-yaml 
 * 文件名：TestJyaml.java
 * 版本信息：
 * 日期：2017年11月22日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.yaml.jyaml;

import java.io.File;
import java.io.FileNotFoundException;

import org.ho.yaml.Yaml;

/**
 * TestJyaml
 * 
 * @author：youngzil@163.com
 * @2017年11月22日 下午7:14:38
 * @since 1.0
 */
public class TestJyaml {

    public static void main(String[] args) {

        /* Initialize data. */
        Person michael = new Person();
        Person floveria = new Person();
        Person[] children = new Person[2];
        children[0] = new Person();
        children[1] = new Person();

        michael.setName("Michael Corleone");
        michael.setAge(24);
        floveria.setName("Floveria Edie");
        floveria.setAge(24);
        children[0].setName("boy");
        children[0].setAge(3);
        children[1].setName("girl");
        children[1].setAge(1);

        michael.setSpouse(floveria);
        floveria.setSpouse(michael);

        michael.setChildren(children);
        floveria.setChildren(children);

        /* Export data to a YAML file. */
        File dumpFile = new File("testYaml.yaml");
        try {
            Yaml.dump(michael, dumpFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
