/**
 * 项目名称：quickstart-yaml 
 * 文件名：TestExampleYaml.java
 * 版本信息：
 * 日期：2017年11月22日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.yaml.snakeyaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

/**
 * TestExampleYaml
 * 
 * @author：yangzl@asiainfo.com
 * @2017年11月22日 下午12:41:47
 * @since 1.0
 */
public class TestExampleYaml {

    // 如果yaml文件中有一个参数没有配置，隐射到JavaBean中的值就是null。
    @Test
    public void testParseMeYaml() throws IOException {

        // https://www.cnblogs.com/allenzhaox/p/3215776.html
        // 这种方式要求properties文件和当前类在同一文件夹下面。如果在不同的包中，必须使用：
        // InputStream in = getClass().getResourceAsStream("资源Name");
        // InputStream ins = this.getClass().getResourceAsStream("/cn/zhao/properties/testPropertiesPath2.properties");

        /*// 获取当前类所在的包：
        File fileB = new File(this.getClass().getResource("").getPath());
        System.out.println("fileB path: " + fileB);
        
        // 获取当前类所在的工程名
        System.out.println("user.dir path: " + System.getProperty("user.dir"));*/

        /* // 为了确保文件一定在之前是存在的，将字符串路径封装成File对象
        File file = new File("example.yaml");
        if (!file.exists()) {
            throw new RuntimeException("要读取的文件不存在");
        }
        
        // 创建文件字节读取流对象时，必须明确与之关联的数据源。
        FileInputStream fis = new FileInputStream(file);*/

        // InputStream in = getClass().getResourceAsStream("example.yaml");
        // InputStream ips1 = TestExampleYaml.class.getClassLoader() .getResourceAsStream("cn/zhao/enumStudy/testPropertiesPath1.properties");
        // InputStream ips2 = TestExampleYaml.class.getResourceAsStream("testPropertiesPath1.properties");

        // 读入文件
        InputStream is = null;
        String path = "example.yaml";
        File file = new File(path);
        if (file.exists()) {
            is = new FileInputStream(file);
        } else {
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
        }
        if (is == null) {
            throw new IllegalArgumentException(path + " file is missing");
        }

        Yaml yaml = new Yaml();
        // 解析配置文件，yaml有很多load方法
        ExampleBean exampleBean = yaml.loadAs(is, ExampleBean.class);
        System.out.println(exampleBean.getClass());
        System.out.println(exampleBean);

        // yaml对象转化为Yaml文档，在Yaml类中也提供了响应的api： dump
        String result = yaml.dumpAsMap(exampleBean);
        System.out.println(result);

        // 生成文件
        List<String> list = new ArrayList<String>();
        list.add("seq1");
        list.add("seq2");
        yaml.dump(list, new FileWriter("list.yaml"));

        // 生成文件
        DumperOptions options = new DumperOptions();
        options.setWidth(1000);
        options.setIndent(5);

        Student stu = new Student();
        stu.setName("路飞");
        stu.setAge(24);
        stu.setId(1);
        Tel t = new Tel();
        t.setName("张三");
        t.setTel("10123041445");
        Tel t1 = new Tel();
        t1.setName("李四");
        t1.setTel("19923041455");
        List<Tel> tels = new ArrayList<Tel>();
        tels.add(t);
        tels.add(t1);
        stu.setTels(tels);
        yaml.dump(stu, new FileWriter("stu.yaml"));

    }

}
