/**
 * 项目名称：quickstart-javase 
 * 文件名：EncrypMD5.java
 * 版本信息：
 * 日期：2017年7月10日
 * Copyright yangzl Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.utils.encrypt.all;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * EncrypMD5
 * 
 * @author：youngzil@163.com
 * @2017年7月10日 下午5:47:48
 * @version 1.0
 */
public class EncrypMD5 {

    public byte[] eccrypt(String info) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] srcBytes = info.getBytes();
        // 使用srcBytes更新摘要
        md5.update(srcBytes);
        // 完成哈希计算，得到result
        byte[] resultBytes = md5.digest();
        return resultBytes;
    }

    public static void main(String args[]) throws NoSuchAlgorithmException {
        String msg = "郭德纲-精品相声技术";
        EncrypMD5 md5 = new EncrypMD5();
        byte[] resultBytes = md5.eccrypt(msg);

        System.out.println("密文是：" + new String(resultBytes));
        System.out.println("明文是：" + msg);
    }
}
