/**
 * 项目名称：quickstart-javase 
 * 文件名：DataInOutStreamDemo.java
 * 版本信息：
 * 日期：2018年5月10日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.bio.example;

/**
 * DataInOutStreamDemo 
 *  
 * @author：youngzil@163.com
 * @2018年5月10日 下午7:30:04 
 * @since 1.0
 */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataInOutStreamDemo {
    public void writeBaseDataType() {
        DataOutputStream dos = null;
        try {
            dos = new DataOutputStream(new FileOutputStream(new File("D:" + File.separator + "demo" + File.separator + "baseDataType.txt")));
            dos.writeInt(4);
            dos.writeBoolean(true);
            dos.writeFloat(0.15f);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void readBaseDataType() {
        DataInputStream dis = null;
        try {
            dis = new DataInputStream(new FileInputStream(new File("D:" + File.separator + "demo" + File.separator + "baseDataType.txt")));
            System.out.println(dis.readInt());
            System.out.println(dis.readBoolean());
            System.out.println(dis.readFloat());
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        DataInOutStreamDemo demo = new DataInOutStreamDemo();
        demo.writeBaseDataType();
        demo.readBaseDataType();
    }
}
