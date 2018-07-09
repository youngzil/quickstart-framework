/**
 * 项目名称：quickstart-string-compress 
 * 文件名：LzoCompress.java
 * 版本信息：
 * 日期：2018年5月22日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.string.compress.lzo;

/**
 * LzoCompress 
 *  
 * @author：yangzl@asiainfo.com
 * @2018年5月22日 下午5:45:03 
 * @since 1.0
 */

/**
 * @author HJX
 * @version 1.0,2013-01-16
 * @since JDK1.7,Ubuntu-12.04-64bit
 * 在hadoop环境下运行
 * 将一个String写入到本地lzo文件中（不是hadoop的hdfs上）
 * 再从该lzo文件中读取出来并与原String进行校对
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;

import com.hadoop.compression.lzo.LzopCodec;

public class LzoCompress {

    /**
     * @param args
     */
    public static void main(String[] args) {
        //生成数据
        String dataSource = "abcdefghijklmnopqrstuvwxyz0123456789~!%#^@*#*%$(\n";
        dataSource = dataSource.concat(dataSource);
        dataSource = dataSource.concat(dataSource);
        dataSource = dataSource.concat(dataSource);
/*        System.out.println("dataSource = " + dataSource);*/
        String lzoFilePath = "/home/hadoop/LzoCompressTest.lzo";
        
        //写入到lzo文件,即lzo压缩
        write2LzoFile(lzoFilePath, getDefaultConf(),dataSource.getBytes());
        StringBuilder sb = new StringBuilder();
        
        //读取lzo文件,即lzo解压缩
        List<String> lines = readLzoFile(lzoFilePath, getDefaultConf());
        for(String line : lines) {
            sb.append(line);
            //LINUX/UNIX 下添加一个换行符
            sb.append("\n");            
/*            //Windows 下添加一个换行符
            sb.append("\r\n");*/
        }
        if (sb.toString().equals(dataSource)) {
            System.out.println(sb.toString());
        } else {
            System.err.println("Error line : " + sb.toString());
        }
    }

    private static Configuration getDefaultConf(){
        Configuration conf = new Configuration();
        conf.set("mapred.job.tracker", "local");
        conf.set("fs.default.name", "file:///");
        conf.set("io.compression.codecs", "com.hadoop.compression.lzo.LzoCodec");
        return conf;
    }
    
    /**
     * 写数据到lzo文件,即lzo压缩
     * @param destLzoFilePath
     * @param conf
     * @param datas
     * @return void
     */
    public static void write2LzoFile(String destLzoFilePath,Configuration conf,byte[] datas) {
        LzopCodec lzo = null;
        OutputStream out = null;
        
        try {
/*          System.setProperty("java.library.path", "/usr/local/hadoop/lib/native/Linux-amd64-64/lib");*/
            lzo = new LzopCodec();
            lzo.setConf(conf);
            out = lzo.createOutputStream(new FileOutputStream(destLzoFilePath));
            out.write(datas);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * 从lzo文件中读取数据,即lzo解压缩
     * @param lzoFilePath
     * @param conf
     * @return void
     */
    public static List<String> readLzoFile(String lzoFilePath,Configuration conf) {
        LzopCodec lzo = null;
        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader reader = null;
        List<String> result = null;
        String line = null;
        
        try {
/*          System.setProperty("java.library.path", "/usr/local/hadoop/lib/native/Linux-amd64-64/lib");*/
            lzo = new LzopCodec();
            lzo.setConf(conf);
            is = lzo.createInputStream(new FileInputStream(lzoFilePath));
            isr = new InputStreamReader(is);
            reader = new BufferedReader(isr);
            result = new ArrayList<String>();
            while((line = reader.readLine()) != null) {
                result.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
                if (isr != null) {
                    isr.close();
                }
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        return result;
    }
}
