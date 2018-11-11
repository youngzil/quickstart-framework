/**
 * 项目名称：quickstart-web 
 * 文件名：SerializableUtil.java
 * 版本信息：
 * 日期：2016年12月29日
 * Copyright asiainfo Corporation 2016
 * 版权所有 *
 */
package com.quickstart.test.socket.nio;

/**
 * SerializableUtil 
 *  
 * @author：youngzil@163.com
 * @2016年12月29日 下午3:58:29 
 * @version 1.0
 */
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializableUtil {

    public static byte[] toBytes(Object object) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        } finally {
            try {
                oos.close();
            } catch (Exception e) {
            }
        }
    }

    public static Object toObject(byte[] bytes) {
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(bais);
            Object object = ois.readObject();
            return object;
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        } finally {
            try {
                ois.close();
            } catch (Exception e) {
            }
        }
    }
}
