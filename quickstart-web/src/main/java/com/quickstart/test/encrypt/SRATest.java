package com.quickstart.test.encrypt;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;

public class SRATest {
    public static void main(String[] args) throws Exception {

        Map<String, Object> keys = RSA.initKey();
        String publicKey = RSA.getPublicKey(keys);
        String privateKey = RSA.getPrivateKey(keys);
        File file = new File("C:\\console_ch.txt");
        InputStream is = new FileInputStream(file);
        byte[] buffer = new byte[1024];
        int length = 0;
        byte[] data = new byte[] {};
        while ((length = is.read(buffer)) > 0) {

            if (length < 1024) {
                byte[] bs = new byte[length];
                for (int i = 0; i < length; i++) {
                    bs[i] = buffer[i];
                }
                buffer = bs;
            }

            data = ArrayUtils.addAll(data, buffer);
        }
        is.close();
        byte[] encryptData = new byte[] {};
        for (int i = 0; i < data.length; i += 100) {
            byte[] subData = ArrayUtils.subarray(data, i, i + 100);
            byte[] encryptingData = RSA.encryptByPrivateKey(subData, privateKey);
            encryptData = ArrayUtils.addAll(encryptData, encryptingData);
        }
        System.out.println(encryptData.toString());

        byte[] decryptData = new byte[] {};
        for (int i = 0; i < encryptData.length; i += 120) {
            byte[] subData = ArrayUtils.subarray(encryptData, i, i + 120);
            byte[] decryptingData = RSA.decryptByPublicKey(subData, publicKey);// 公钥解密
            decryptData = ArrayUtils.addAll(decryptData, decryptingData);
        }
        System.out.println(new String(decryptData));
    }
}
