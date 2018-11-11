/**
 * 项目名称：quickstart-javase 
 * 文件名：PrintWriterDemo.java
 * 版本信息：
 * 日期：2018年5月10日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.bio.example;

/**
 * PrintWriterDemo 
 *  
 * @author：youngzil@163.com
 * @2018年5月10日 下午7:24:04 
 * @since 1.0
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class PrintWriterDemo {
    /* 
     * PrintWriter：从键盘读取，输出到文件，自动flush 
     */
    public void autoFlushIntoFile() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                PrintWriter pw = new PrintWriter(new FileOutputStream(new File("D:" + File.separator + "demo" + File.separator + "xx.txt")), true)) {
            String hasRead = null;
            while ((hasRead = br.readLine()) != null) {
                pw.println(hasRead);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
