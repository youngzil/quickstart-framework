/**
 * 项目名称：quickstart-javase 
 * 文件名：SequenceInputStreamDemo.java
 * 版本信息：
 * 日期：2018年5月10日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.bio.example;

/**
 * SequenceInputStreamDemo 
 *  
 * @author：yangzl@asiainfo.com
 * @2018年5月10日 下午7:28:33 
 * @since 1.0
 */
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;

/* 
 * SequenceInputStream：序列流，将多个输入流对象合并成一个输入流 
 */
public class SequenceInputStreamDemo {
    /* 
     * 将一个mp3文件切割成三部分存储到硬盘上 
     */
    public void splitFile() {
        File mp3File = new File("d:" + File.separator + "demo" + File.separator + "angel.mp3");
        if (!mp3File.exists() || !mp3File.isFile())
            return;

        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(mp3File));) {
            int count = 0;
            byte[] bArray = new byte[bis.available() / 3];
            int hasRead = -1;
            while ((hasRead = bis.read(bArray)) != -1) {
                /* 
                 * 采用多个输出流来切割文件 
                 */
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File("d:" + File.separator + "demo" + File.separator + (count++) + ".mp3")));
                bos.write(bArray, 0, hasRead);
                bos.close();
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /* 
     * 利用SequenceInputStream将多个输入流合并成一个输入流 
     */
    public void mergeFiles(Collection<? extends InputStream> c) {
        if (c == null)
            return;

        /* 
         * 建立SequenceInputStream需要传入Enumeration对象，可以通过Collection的迭代器来重写Enum对象中的方法 
         * 从而使得所有的Collection都可以使用 
         */
        Iterator<? extends InputStream> it = c.iterator();
        SequenceInputStream sis = new SequenceInputStream(new Enumeration<InputStream>() {
            @Override
            public boolean hasMoreElements() {
                // TODO Auto-generated method stub
                return it.hasNext();
            }

            @Override
            public InputStream nextElement() {
                // TODO Auto-generated method stub
                return it.next();
            }
        });

        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File("d:" + File.separator + "demo" + File.separator + "副本-angel.mp3")));) {
            byte[] bArray = new byte[sis.available() / 10];
            int hasRead = -1;
            while ((hasRead = sis.read(bArray)) != -1) {
                bos.write(bArray, 0, hasRead);
                bos.flush();
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (sis != null) {
                try {
                    sis.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        SequenceInputStreamDemo demo = new SequenceInputStreamDemo();
        // demo.splitFile();
        ArrayList<FileInputStream> list = new ArrayList<>();
        list.add(new FileInputStream(new File("d:" + File.separator + "demo" + File.separator + "0.mp3")));
        list.add(new FileInputStream(new File("d:" + File.separator + "demo" + File.separator + "1.mp3")));
        list.add(new FileInputStream(new File("d:" + File.separator + "demo" + File.separator + "2.mp3")));
        demo.mergeFiles(list);
    }
}
