/**
 * 项目名称：quickstart-yaml 
 * 文件名：TestYamlbeans.java
 * 版本信息：
 * 日期：2017年11月22日
 * Copyright yangzl Corporation 2017
 * 版权所有 *
 */
package org.quickstart.yaml.yamlbeans;

import java.io.FileNotFoundException;
import java.io.FileReader;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;

/**
 * TestYamlbeans
 * 
 * @author：youngzil@163.com
 * @2017年11月22日 下午7:46:07
 * @since 1.0
 */
public class TestYamlbeans {

    public static void main(String[] args) throws FileNotFoundException, YamlException {

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = classLoader.getResource("yamlbeans.yaml").getPath();
        YamlReader reader = new YamlReader(new FileReader(path));
        Demo demo = reader.read(Demo.class);

        System.out.println(demo);
    }

}
