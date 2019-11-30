package org.quickstart.javase.jdk.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import org.junit.Test;

public class PropertiesTest {

    private static String config = "src/main/resources/config.properties";

    @Test
    public void testWrite() throws Exception {
        Properties prop = new Properties();
        OutputStream output = null;

        try {

            // 存储properties文件
            output = new FileOutputStream(config);

            // set the properties value
            prop.setProperty("database", "localhost");
            prop.setProperty("dbuser", "mkyong");
            prop.setProperty("dbpassword", "password");

            // save properties to project root folder
            prop.store(output, null);

        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    // Load a properties file from the file system and retrieved the property value.
    // 第一种是文件io流：
    @Test
    public void testLoadFromFileSystem() throws Exception {

        Properties prop = new Properties();
        InputStream input = null;

        try {

            // 文件真实路径
             input = new FileInputStream(new File(config));

//            input = new FileInputStream(config);

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            System.out.println(prop.getProperty("database"));
            System.out.println(prop.getProperty("dbuser"));
            System.out.println(prop.getProperty("dbpassword"));

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    // 3. Load a properties file from classpath
    // 第二种：相对路径：通过ClassLoader加载
    @Test
    public void testLoadFromClasspath() throws Exception {
        Properties prop = new Properties();
        InputStream input = null;

        try {

            String filename = "config.properties";
            // input = PropertiesTest.class.getClassLoader().getResource(filename);
            // input = PropertiesTest.class.getClassLoader().getResourceAsStream(filename);
            // input = PropertiesTest.class.getClassLoader().getSystemResourceAsStream(filename);
            // input = Thread.currentThread().getContextClassLoader().getSystemResourceAsStream(filename);
            input = ClassLoader.getSystemResourceAsStream(filename);
            if (input == null) {
                System.out.println("Sorry, unable to find " + filename);
                return;
            }

            // load a properties file from class path, inside static method
            prop.load(input);

            // get the property value and print it out
            System.out.println(prop.getProperty("database"));
            System.out.println(prop.getProperty("dbuser"));
            System.out.println(prop.getProperty("dbpassword"));

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 4. Prints everything from a properties file
    // 相对于类路径
    @Test
    public void testPropertiesPrints() throws Exception {
        Properties prop = new Properties();
        InputStream input = null;

        try {

            String filename = "config.properties";
            input = getClass().getClassLoader().getResourceAsStream(filename);
            if (input == null) {
                System.out.println("Sorry, unable to find " + filename);
                return;
            }

            prop.load(input);

            Enumeration<?> e = prop.propertyNames();
            while (e.hasMoreElements()) {
                String key = (String) e.nextElement();
                String value = prop.getProperty(key);
                System.out.println("Key : " + key + ", Value : " + value);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 如果我们要获取src（类包）下的db.properties又该怎么处理呢？
    // 相对于类路径 properties文件盒java放在一起
    @Test
    public void testPropertiesfromPackage() throws Exception {
        Properties prop = new Properties();
        InputStream input = null;

        try {

            // ClassLoader相对于根目录
            String filename = "org/quickstart/javase/jdk/file/config.properties";
            input = ClassLoader.getSystemResourceAsStream(filename);

            // 相对于class类的当前路径
            // String filename2 = "../../../../config.properties";
            String filename2 = "config.properties";
            input = PropertiesTest.class.getResourceAsStream(filename2);
            if (input == null) {
                System.out.println("Sorry, unable to find " + filename);
                return;
            }

            prop.load(input);

            System.out.println(prop);

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    
    /**
     * 获取指定.properties配置文件中所以的数据
     * @param propertyName
     *        调用方式：
     *            1.配置文件放在resource源包下，不用加后缀
     *              PropertiesUtil.getAllMessage("message");
     *            2.放在包里面的
     *              PropertiesUtil.getAllMessage("com.test.message");
     *              优点是：可以以完全限定类名的方式加载资源后，直接的读取出来，且可以在非Web应用中读取资源文件。
                    缺点：只能加载类classes下面的资源文件，且只能读取.properties文件。
     * @return
     */
    public static List<String> getAllMessage(String propertyName) {
        // 获得资源包
        ResourceBundle rb = ResourceBundle.getBundle(propertyName.trim());
        // 通过资源包拿到所有的key
        Enumeration<String> allKey = rb.getKeys();
        // 遍历key 得到 value
        List<String> valList = new ArrayList<String>();
        while (allKey.hasMoreElements()) {
            String key = allKey.nextElement();
            String value = (String) rb.getString(key);
            valList.add(value);
        }
        return valList;
    }

}
