package org.quickstart.crypto.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.Locale;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

public class SecurityUtils {
    private SecurityUtils() {}

    public static final String ENCODE = "UTF-8";
    public static final String CIPHER_ALGORITHM = "AES/ECB/PKCS7Padding";

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * 初始化HmacSHA256的密钥
     *
     * @return byte[] 密钥
     */
    public static byte[] initAES256Key() throws NoSuchAlgorithmException {

        // 初始化KeyGenerator
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        // 192 and 256 bits also available，这一步是否有安装jce unlimited strength jurisdiction policy files 都不会出现异常
        keyGenerator.init(256);
        // 产生密钥
        SecretKey secretKey = keyGenerator.generateKey();
        // 获取密钥
        return secretKey.getEncoded();
    }

    /**
     * 初始化HmacSHA256的密钥
     *
     * @return byte[] 密钥
     */
    public static byte[] initHmacSHA256Key() throws NoSuchAlgorithmException {

        // 初始化KeyGenerator
        KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
        keyGenerator.init(256);
        // 产生密钥
        SecretKey secretKey = keyGenerator.generateKey();
        // 获取密钥
        return secretKey.getEncoded();
    }

    /**
     * HmacSHA256消息摘要
     *
     * @param data 待做摘要处理的数据
     * @param key 密钥
     * @return byte[] 消息摘要
     */
    public static byte[] encodeHmacSHA256(byte[] data, byte[] key) throws NoSuchAlgorithmException, InvalidKeyException {
        // 还原密钥，因为密钥是以byte形式为消息传递算法所拥有
        SecretKey secretKey = new SecretKeySpec(key, "HmacSHA256");
        // 实例化Mac
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        // 初始化Mac
        mac.init(secretKey);
        // 执行消息摘要处理
        return mac.doFinal(data);
    }

    /**
     * @param data
     * @param key
     * @return 为Hex大写字符
     * @throws Exception
     */
    public static String encodeHmacSHA256HexUpper(String data, byte[] key) throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException {

        return bytesToHexString(encodeHmacSHA256(data.getBytes(ENCODE), key)).toUpperCase(Locale.US);
    }

    /**
     * @param data to be encrypted
     * @param key password for encryption
     * @return encrypted data
     */
    public static byte[] encrypt(byte[] data, byte[] key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {

        // Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key, "AES"));
        return cipher.doFinal(data);
    }

    /**
     * @param data to be decrypted
     * @param key password for decryption
     * @return decrypted data
     */
    public static byte[] decrypt(byte[] data, byte[] key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        // Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key, "AES"));
        return cipher.doFinal(data);
    }

    /**
     * @param
     * @return 为Hex大写字符
     * @throws UnsupportedEncodingException
     */
    public static String encodeHexUpper(byte[] data) throws UnsupportedEncodingException {
        return bytesToHexString(data).toUpperCase(Locale.US);
    }

    /**
     * @param str 为Hex大写字符
     * @return 正常字符
     * @throws UnsupportedEncodingException
     */
    public static byte[] decodeHexUpper(String str) throws UnsupportedEncodingException {

        return Hex.decode(str.toLowerCase(Locale.US));
    }

    /**
     * @param str 为Hex大写字符
     * @return 正常字符
     * @throws UnsupportedEncodingException
     */
    public static String decodeHexUpper(String str, String charsetName) throws UnsupportedEncodingException {

        return new String(Hex.decode(str.toLowerCase(Locale.US)), charsetName);
    }

    /**
     * @param data 普通字符
     * @param key
     * @return 为Hex大写字符
     * @throws Exception
     */
    public static String encodeAES256HexUpper(String data, byte[] key)
            throws UnsupportedEncodingException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
        return encodeHexUpper(encrypt(data.getBytes(ENCODE), key));
    }

    /**
     * @param data 为Hex大写字符
     * @param key
     * @return 普通字符
     * @throws Exception
     */
    public static String decodeAES256HexUpper(String data, byte[] key)
            throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException {
        return new String(decrypt(Hex.decode(data.toLowerCase(Locale.US)), key), ENCODE);
    }

    public static String bytesToHexString(byte[] b) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {

        String string = "hehe";
        String key = "keyTest";

        byte[] dd = encrypt(string.getBytes(),key.getBytes());

        byte[] ss = decrypt(dd,key.getBytes());
        System.out.println(new String(ss));


        String s = "130AA05875E3A0644DE1A11B103284B4";
        s = SecurityUtils.decodeAES256HexUpper(s, SecurityUtils.decodeHexUpper("eafbbb3d23b1ec7f0269301dd06fb635"));
        System.out.println(s);
    }
}
