/**
 * 项目名称：quickstart-javase 
 * 文件名：FileUtils.java
 * 版本信息：
 * 日期：2017年7月30日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.jdk.classloader.demo1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * FileUtils
 * 
 * @author：youngzil@163.com
 * @2017年7月30日 下午10:18:38
 * @version 2.0
 */
public class FileUtils {

    public static void test(String path) {
        File file = new File(path);
        try {
            FileInputStream fis = new FileInputStream(file);
            FileOutputStream fos = new FileOutputStream(path + "en");
            int b = 0;
            int b1 = 0;
            try {
                while ((b = fis.read()) != -1) {
                    // 每一个byte异或一个数字2
                    fos.write(b ^ 2);
                }
                fos.close();
                fis.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
