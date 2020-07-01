package org.quickstart.crypto.sample.twoway.symmetric;

import java.security.Security;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019-07-18 19:44
 */
public class AES256 {

  public static final String CIPHER_ALGORITHM = "AES/CBC/PKCS7Padding";// AES/CBC/PKCS5PADDING
  // public static final String CIPHER_ALGORITHM = "AES/ECB/PKCS7Padding";// AES/CBC/PKCS5PADDING
  public static final String secretKeyFactory = "PBKDF2WithHmacSHA256";

  private static final byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

  private static String secretKey = "boooooooooom!!!!";
  private static String salt = "ssshhhhhhhhhhh!!!!";

  static {
    if (Security.getProvider("BC") == null) {
      Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
    } else {
      Security.removeProvider("BC");
      Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
    }
  }

  public static String encrypt(String strToEncrypt, String secret) {
    try {

      IvParameterSpec ivspec = new IvParameterSpec(iv);

      SecretKeyFactory factory = SecretKeyFactory.getInstance(secretKeyFactory);
      KeySpec spec = new PBEKeySpec(secret.toCharArray(), salt.getBytes(), 65536, 256);
      SecretKey tmp = factory.generateSecret(spec);
      SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

      Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
      cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
      return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Error while encrypting: " + e.toString());
    }
    return null;
  }

  public static String decrypt(String strToDecrypt, String secret) {
    try {
      IvParameterSpec ivspec = new IvParameterSpec(iv);
      SecretKeyFactory factory = SecretKeyFactory.getInstance(secretKeyFactory);
      KeySpec spec = new PBEKeySpec(secret.toCharArray(), salt.getBytes(), 65536, 256);
      SecretKey tmp = factory.generateSecret(spec);
      SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

      Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
      cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
      return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Error while decrypting: " + e.toString());
    }
    return null;
  }

  public static void main(String[] args) {
    String originalString = "howtodoinjava.com";

    String encryptedString = AES256.encrypt(originalString, secretKey);
    String decryptedString = AES256.decrypt(encryptedString, secretKey);

    System.out.println(originalString);
    System.out.println(encryptedString);
    System.out.println(decryptedString);
  }

}
