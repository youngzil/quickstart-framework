/**
 * 项目名称：quickstart-javase 
 * 文件名：EncrypSHA.java
 * 版本信息：
 * 日期：2017年7月10日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.utils.encrypt.all;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * EncrypSHA
 * 
 * @author：yangzl@asiainfo.com
 * @2017年7月10日 下午5:51:31
 * @version 1.0
 */
public class EncrypSHA {

    public byte[] eccrypt(String info) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("SHA");
        byte[] srcBytes = info.getBytes();
        // 使用srcBytes更新摘要
        md5.update(srcBytes);
        // 完成哈希计算，得到result
        byte[] resultBytes = md5.digest();
        return resultBytes;
    }

    /**
     * @param args
     * @throws NoSuchAlgorithmException
     */
    public static void main(String[] args) throws NoSuchAlgorithmException {
        String msg = "郭德纲-精品相声技术";
        EncrypSHA sha = new EncrypSHA();
        byte[] resultBytes = sha.eccrypt(msg);
        System.out.println("明文是：" + msg);
        System.out.println("密文是：" + new String(resultBytes));

    }

}
