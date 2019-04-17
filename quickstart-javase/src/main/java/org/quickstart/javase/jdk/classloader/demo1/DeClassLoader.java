/**
 * 项目名称：quickstart-javase 
 * 文件名：DeClassLoader.java
 * 版本信息：
 * 日期：2017年7月30日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.jdk.classloader.demo1;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * DeClassLoader
 * 
 * @author：youngzil@163.com
 * @2017年7月30日 下午10:19:22
 * @version 2.0
 */
public class DeClassLoader extends ClassLoader {
    
//    （1）继承ClassLoader    （2）重写findClass（）方法   （3）调用defineClass（）方法

    private String mLibPath;

    public DeClassLoader(String path) {
        // TODO Auto-generated constructor stub
        mLibPath = path;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        // TODO Auto-generated method stub

        String fileName = getFileName(name);

        File file = new File(mLibPath, fileName);

        try {
            FileInputStream is = new FileInputStream(file);

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            int len = 0;
            byte b = 0;
            try {
                while ((len = is.read()) != -1) {
                    // 将数据异或一个数字2进行解密
                    b = (byte) (len ^ 2);
                    bos.write(b);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            byte[] data = bos.toByteArray();
            is.close();
            bos.close();

            return defineClass(name, data, 0, data.length);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return super.findClass(name);
    }

    // 获取要加载 的class文件名
    private String getFileName(String name) {
        // TODO Auto-generated method stub
        int index = name.lastIndexOf('.');
        if (index == -1) {
            return name + ".classen";
        } else {
            return name.substring(index + 1) + ".classen";
        }
    }

}
