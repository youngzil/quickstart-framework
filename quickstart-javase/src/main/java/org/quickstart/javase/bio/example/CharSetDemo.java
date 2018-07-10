/**
 * 项目名称：quickstart-javase 
 * 文件名：CharSetDemo.java
 * 版本信息：
 * 日期：2018年5月10日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.bio.example;

/**
 * CharSetDemo 
 *  
 * @author：yangzl@asiainfo.com
 * @2018年5月10日 下午7:33:39 
 * @since 1.0
 */
import java.io.UnsupportedEncodingException;

public class CharSetDemo {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String str = "名字";
        /* 
         * unicode字符用utf-8方式编码 
         */
        byte[] utf_8Array = str.getBytes("UTF-8");
        System.out.println(utf_8Array.length);
        /* 
         * unicode字符采用GBK方式编码 
         */
        byte[] gbkArray = str.getBytes("GBK");
        System.out.println(gbkArray.length);
        /* 
         * unicode解码utf-8和gbk 
         */
        System.out.println(new String(utf_8Array));
        System.out.println(new String(gbkArray));
        System.out.println(new String(gbkArray, "GBK"));
    }
    /* 
     * 输出结果： 
     * 6 
     * 4 
     * 名字 
     * ���� 
     */
}
