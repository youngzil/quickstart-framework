/**
 * 项目名称：quickstart-javase 
 * 文件名：RSA.java
 * 版本信息：
 * 日期：2018年10月18日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.crypto.sample.twoway.asymmetric;

/**
 * RSA 
 *  
 * @author：youngzil@163.com
 * @2018年10月18日 下午9:16:00 
 * @since 1.0
 */

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;


/**
 * 非对称加密算法
 * @author sunx
 *
 */
public class RSA {


    private static String src = "securtity RSA";

    public static void main(String[] args) {
        jdkRSA();
    }

    public static void jdkRSA(){

        try {
            //1、初始化密钥
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(512);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();// 甲方公钥
            RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();// 甲方私钥

            //2、执行签名
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(rsaPrivateKey.getEncoded());
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);// 生成私钥
            Signature signature = Signature.getInstance("MD5withRSA");
            signature.initSign(privateKey);
            signature.update(src.getBytes());
            byte[] result = signature.sign();// 得到签名
            System.out.println("jdk rsa sign:" + result.toString());

            //对明文加密
            String beforeText = encrypt(src, privateKey);
            System.out.println("解密前内容：" + beforeText);

            //3、验证签名
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(rsaPublicKey.getEncoded());
            keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);// 得到公钥
            signature = Signature.getInstance("MD5withRSA");
            signature.initVerify(publicKey);
            signature.update(src.getBytes());
            boolean bool = signature.verify(result);
            System.out.println(bool);

            //解密密文
            String afterText = decrypt(beforeText, publicKey);
            System.out.println("解密后内容：" + afterText);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 私钥对明文数据进行加密
     * @param plainText
     * @param privateKey
     * @return
     */
    public static String encrypt(String plainText, PrivateKey privateKey){

        String result = null;
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            byte[] output = cipher.doFinal(plainText.getBytes());
            result = Base64.encode(output);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;

    }

    /**
     * 公钥对密文数据进行解密
     * @param cipherText
     * @param PublicKey
     * @return
     */
    public static String decrypt(String cipherText, PublicKey publicKey){

        String result = null;
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            result = new String(cipher.doFinal(Base64.decode(cipherText)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;

    }


}
