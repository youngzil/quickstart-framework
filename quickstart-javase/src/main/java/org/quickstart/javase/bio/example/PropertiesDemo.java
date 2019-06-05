/**
 * 项目名称：quickstart-javase 
 * 文件名：PropertiesDemo.java
 * 版本信息：
 * 日期：2018年5月10日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.bio.example;

/**
 * PropertiesDemo 
 *  
 * @author：youngzil@163.com
 * @2018年5月10日 下午7:32:39 
 * @since 1.0
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

/* 
 * Properties加载流 
 */
public class PropertiesDemo {
    public Properties loadInputStream() {
        Properties pro = new Properties();
        try {
            /* 
             * 加载流 
             */
            pro.load(new FileInputStream(new File("d:" + File.separator + "demo" + File.separator + "info.properties")));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Set<String> names = pro.stringPropertyNames();
        for (String name : names)
            System.out.println(name + " : " + pro.getProperty(name));

        return pro;
    }

    /* 
     * 将Properties对象存储到磁盘 
     */
    public void storeOutputStream(Properties pro) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("d:" + File.separator + "demo" + File.separator + "xx.properties");
            if (fos != null)
                pro.store(fos, "");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        PropertiesDemo demo = new PropertiesDemo();
        demo.storeOutputStream(demo.loadInputStream());
    }
}
