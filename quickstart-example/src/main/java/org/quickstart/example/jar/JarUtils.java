/**
 * 项目名称：quickstart-example 
 * 文件名：Test.java
 * 版本信息：
 * 日期：2017年2月17日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.example.jar;

import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

/**
 * Test
 * 
 * @author：youngzil@163.com
 * @2017年2月17日 下午2:46:03
 * @version 1.0
 */
public class JarUtils {

    public static String getJarImplementationVersion(String jar) throws java.io.IOException {
        JarFile jarfile = new JarFile(jar);
        Manifest manifest = jarfile.getManifest();
        Attributes att = manifest.getMainAttributes();
        return att.getValue("Implementation-Version");
    }

    public static String getJarSpecificationVersion(String jar) throws java.io.IOException {
        JarFile jarfile = new JarFile(jar);
        Manifest manifest = jarfile.getManifest();
        Attributes att = manifest.getMainAttributes();
        return att.getValue("Specification-Version");
    }

    public static void main(String[] args) throws java.io.IOException {

        /**
         * Manifest-Version: 1.0 Implementation-Title: ActiveMQ :: All JAR bundle Implementation-Version: 5.14.3 Archiver-Version: Plexus Archiver Built-By: cshannon Specification-Vendor: The Apache
         * Software Foundation Specification-Title: ActiveMQ :: All JAR bundle Implementation-Vendor-Id: org.apache.activemq Implementation-Vendor: The Apache Software Foundation Main-Class:
         * org.apache.activemq.console.command.ShellCommand Created-By: Apache Maven 3.3.9 Build-Jdk: 1.8.0_112 Specification-Version: 5.14.3
         */
        String jarPath = "/Users/yangzl/Desktop/framework/activemq/apache-activemq-5.14.3/activemq-all-5.14.3.jar";

        JarFile jarfile = new JarFile(jarPath);
        Manifest manifest = jarfile.getManifest();
        Attributes att = manifest.getMainAttributes();
        System.out.println(att.getValue("Implementation-Version"));
        System.out.println(att.getValue("Specification-Version"));
        System.out.println(att.getValue("Manifest-Version"));
        System.out.println(att.getValue("Main-Class"));
        System.out.println(att.getValue("Created-By"));

    }

}
