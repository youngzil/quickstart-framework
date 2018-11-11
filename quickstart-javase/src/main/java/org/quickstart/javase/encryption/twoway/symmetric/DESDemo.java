/**
 * 项目名称：quickstart-javase 
 * 文件名：DESDemo.java
 * 版本信息：
 * 日期：2018年10月18日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.encryption.twoway.symmetric;

/**
 * DESDemo 
 *  
 * @author：youngzil@163.com
 * @2018年10月18日 下午9:16:47 
 * @since 1.0
 */

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

public class DESDemo {
    
    private static String src = "TestDES";
    
    
    public static void jdkDES () {
        
        try {
            //生成密钥Key
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
            keyGenerator.init(56);
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] bytesKey = secretKey.getEncoded();
        
            
            //KEY转换
            DESKeySpec deSedeKeySpec = new DESKeySpec(bytesKey);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
            Key convertSecretKey = factory.generateSecret(deSedeKeySpec);
            
            //加密
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, convertSecretKey);
            byte[] encodeResult = cipher.doFinal(DESDemo.src.getBytes());
            System.out.println("DESEncode :" + Hex.toHexString(encodeResult));
            
            
            //解密
            cipher.init(Cipher.DECRYPT_MODE,convertSecretKey);
            byte[] DecodeResult = cipher.doFinal(encodeResult);
            System.out.println("DESDncode :" + new String (DecodeResult));
            
            
            
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        } catch (BadPaddingException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
    
    }
    
    
    
    public static void bcDES (){
        try {
            
            
            //使用BouncyCastle 的DES加密
            Security.addProvider(new BouncyCastleProvider());
            
            
            //生成密钥Key
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DES","BC");
            keyGenerator.init(56);
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] bytesKey = secretKey.getEncoded();
        
            
            //KEY转换
            DESKeySpec deSedeKeySpec = new DESKeySpec(bytesKey);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
            Key convertSecretKey = factory.generateSecret(deSedeKeySpec);
            
            //加密
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, convertSecretKey);
            byte[] encodeResult = cipher.doFinal(DESDemo.src.getBytes());
            System.out.println("DESEncode :" + Hex.toHexString(encodeResult));
            
            
            //解密
            cipher.init(Cipher.DECRYPT_MODE,convertSecretKey);
            byte[] DecodeResult = cipher.doFinal(encodeResult);
            System.out.println("DESDncode :" + new String (DecodeResult));
            
            
            
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        } catch (BadPaddingException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
    }
    
    
    public static void main(String[] args) {
        DESDemo.jdkDES ();
        DESDemo.bcDES();
    }

}
