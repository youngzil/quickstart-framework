package com.quickstart.test.encrypt;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import javax.crypto.Cipher;

public class Test1 {

    // 公钥加密
    public byte[] PublicEncrypt(KeyPair key, String str) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key.getPublic());
        return cipher.doFinal(str.getBytes("UTF8"));
    }

    // 公钥解密
    public byte[] PublicDECRYPT(KeyPair key, byte[] data) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, key.getPublic());
        return cipher.doFinal(data);
    }

    // 私钥加密
    public byte[] PrivateEncrypt(KeyPair key, String str) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key.getPrivate());
        return cipher.doFinal(str.getBytes("UTF8"));
    }

    // 私钥解密
    public byte[] PrivateDECRYPT(KeyPair key, byte[] data) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, key.getPrivate());
        return cipher.doFinal(data);
    }

    public static void main(String args[]) throws Exception {
        String str = "Hello World!";
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(1024);
        KeyPair key = keyGen.generateKeyPair();
        Test1 t = new Test1();
        System.out.println("加密前原文:" + str);
        byte[] data = t.PublicEncrypt(key, str);
        System.out.println("私钥解密后:" + new String(t.PrivateDECRYPT(key, data)));
        byte[] data1 = t.PrivateEncrypt(key, str);
        System.out.println("公钥解密后:" + new String(t.PublicDECRYPT(key, data1)));
    }
}
