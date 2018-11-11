/**
 * 项目名称：quickstart-javase 
 * 文件名：MyBufferedReader.java
 * 版本信息：
 * 日期：2018年5月10日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.bio.example;

/**
 * MyBufferedReader 
 *  
 *  字符缓冲输入流的readLine方法原理：利用FileReader逐字符的读取数据，当读到"\r\n"的时候表示读到行尾，则返回数据。自定义实现readLine方法
 *  
 * @author：youngzil@163.com
 * @2018年5月10日 下午6:56:09 
 * @since 1.0
 */
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MyBufferedReader {
    private FileReader reader;

    public MyBufferedReader(FileReader reader) {
        // TODO Auto-generated constructor stub
        this.reader = reader;
    }

    /* 
     * 自定义readLine 
     */
    public String readLine() {
        StringBuilder sb = new StringBuilder();
        int hasRead = -1;
        try {
            while ((hasRead = reader.read()) != -1) {
                if ((char) hasRead == '\r')
                    continue;
                if ((char) hasRead == '\n')
                    return sb.toString();
                sb.append((char) hasRead);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public void close() {
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        FileReader reader = new FileReader(new File("D:" + File.separator + "demo" + File.separator + "mybuffer.txt"));
        MyBufferedReader myReader = new MyBufferedReader(reader);
        String hasRead = null;
        while ((hasRead = myReader.readLine()) != null) {
            System.out.println(hasRead);
        }
        myReader.close();
    }
}
