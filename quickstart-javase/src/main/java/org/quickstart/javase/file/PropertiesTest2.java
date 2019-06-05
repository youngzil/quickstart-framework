/**
 * 项目名称：quickstart-javase 
 * 文件名：PropertiesTest.java
 * 版本信息：
 * 日期：2018年9月11日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

import org.junit.Test;

/**
 * PropertiesTest
 * 
 * @author：youngzil@163.com
 * @2018年9月11日 下午2:16:15
 * @since 1.0
 */
public class PropertiesTest2 {

    @Test
    public void testGetPropertiesFile() throws IOException {

        // 2.1 获取当前类所在的包：
        File fileB = new File(this.getClass().getResource("").getPath());
        System.out.println("fileB path: " + fileB);

        // 2.2 获取当前类所在的工程名：
        System.out.println("user.dir path: " + System.getProperty("user.dir"));

//        InputStream in = Object.class.getResourceAsStream("/Users/yangzl/msgframe.properties");

//        InputStream ips1 = Enumeration.class.getClassLoader().getResourceAsStream("cn/zhao/enumStudy/testPropertiesPath1.properties");
//        InputStream ips2 = Enumeration.class.getResourceAsStream("testPropertiesPath1.properties");
//        InputStream ips3 = Enumeration.class.getResourceAsStream("properties/testPropertiesPath2.properties");
        // InputStream inputFile = Thread.currentThread().getContextClassLoader().getResourceAsStream(msgframePropertiesPath);

        
        InputStream in = new FileInputStream("/Users/yangzl/workspace/../msgframe.properties");
        
        if (null == in) {
            System.out.println("未加载到");
            return;
        }

        Properties prop = new Properties();
        prop.load(in);
        System.out.println(prop.getProperty("msg.es.url"));


    }

}
