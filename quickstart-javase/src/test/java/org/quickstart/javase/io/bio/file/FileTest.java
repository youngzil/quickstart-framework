package org.quickstart.javase.io.bio.file;

import org.junit.Test;

import java.io.File;

/**
 * <p>描述: [功能描述] </p >
 *
 * @author yangzl
 * @date 2020/10/12 21:04
 * @version v1.0
 */
public class FileTest {

    @Test
    public void testFileConstructor() {

        // pathname
        File liuBei = new File("D:/三国/刘备.jpg");
        // String parent, String child
        File guanYu = new File("D:/三国", "关羽.jpg");
        // 目录
        File sanGuo = new File("D:/三国");
        // File parent, String child
        File zhangFei = new File(sanGuo, "张飞.txt");
        // 可以声明不存在的文件
        File zhuGeLiang = new File(sanGuo, "诸葛亮.txt");

        File f = new File("D:/bbb.java");
        // D:\bbb.java
        System.out.println(f.getAbsolutePath());

        File f2 = new File("bbb.java");
        // F:\code\ad\bbb.java
        System.out.println(f2.getAbsolutePath());

    }

    @Test
    public void testFileCreate() {

        File shuiHu = new File("D:/四大名著/水浒传");

        //        创建多级目录时，mkdir创建失败，返回false，mkdirs创建成功，返回true（推荐使用mkdirs）
        // 返回false 创建失败
        boolean mkdir = shuiHu.mkdir();
        // 返回true 创建失败
        boolean mkdirs = shuiHu.mkdirs();

        File four = new File("D:/四大名著");
        // 返回false 删除目录时必须目录为空才能删除成功
        boolean delete = four.delete();

        // true 正确删除了水浒传目录
        boolean delete1 = shuiHu.delete();

        File liuBei = new File("D:/三国/刘备.jpg");
        // 返回true 正确删除了刘备.jpg文件
        //        删除目录时，目录内不为空时，删除失败，返回false， 即只能删除文件或者空目录
        boolean delete2 = liuBei.delete();

    }

    @Test
    public void testFileOperation() {

        File xiYou = new File("D:/西游记");
        // 文件或目录不存在时 返回false
        System.out.println(xiYou.isDirectory());

        File shuiHu = new File("D:/水浒传");
        // 0
        System.out.println(shuiHu.length());
        File liuBei = new File("D:/三国/刘备.jpg");
        // 24591
        System.out.println(liuBei.length());

        File f = new File("D:/bbb.java");
        // D:\bbb.java
        System.out.println(f.getPath());

        File f2 = new File("bbb.java");
        // bbb.java
        System.out.println(f2.getPath());

        File sanGuo2 = new File("D:/三国2");
        // 该目录不存在，返回null
        String[] list = sanGuo2.list();

    }

}
