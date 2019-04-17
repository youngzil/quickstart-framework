package org.quickstart.crypto.utils;

import java.io.IOException;
import java.util.Properties;

public class ZJWGSDKConfig {
    
    public static void main(String[] args) {
        System.out.println(System.nanoTime());
        System.out.println(System.currentTimeMillis());
    }

    private static Properties props = new Properties();

    public static String getValue(String key) {
        return props.getProperty(key);
    }

    static {
        try {
            props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("zjwg.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getSignMethod() {
        return props.getProperty("sign_method");
    }

    public static String getAppKey() {
        return props.getProperty("app_key");
    }

    public static String getRsaKey() {
        return props.getProperty("rsa_key");
    }

    public static String getRsaEncryptType() {
        return props.getProperty("rsa_encrypt_type", "Public");
    }

    public static String getKeyAcquireType() {
        return props.getProperty("key_acquire_type", "local");
    }

    public static String getDecodeCharset() {
        return props.getProperty("decode_charset", "UTF-8");
    }

}
