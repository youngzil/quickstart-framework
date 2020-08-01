/**
 * 项目名称：quickstart-crypto 
 * 文件名：DES.java
 * 版本信息：
 * 日期：2019年6月6日
 * Copyright yangzl Corporation 2019
 * 版权所有 *
 */
package org.quickstart.crypto.utils;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Security;
import java.util.Objects;

/**
 * DES 
 *
 * @author：youngzil@163.com
 * @2019年6月6日 上午11:15:12 
 * @version 2.0
 */
public class DES {

    public static int _DES = 1;
    public static int _DESede = 2;
    public static int _Blowfish = 3;

    private Cipher p_Cipher;
    private SecretKey p_Key;
    private String p_Algorithm;
    private static DES _instance;
    private static String hexKey = "B5584A5D9B61C23BE52CA1168C9110894C4FE9ABC8E9F251";// ��Կ

    private void selectAlgorithm(int al) {
        switch (al) {
            default:
            case 1:
                this.p_Algorithm = "DES";
                break;
            case 2:
                this.p_Algorithm = "DESede";
                break;
            case 3:
                this.p_Algorithm = "Blowfish";
                break;
        }
    }

    public DES(int algorithm) throws Exception {
        this.selectAlgorithm(algorithm);
        Security.addProvider(new com.sun.crypto.provider.SunJCE());
        this.p_Cipher = Cipher.getInstance(this.p_Algorithm);
    }

    public byte[] getKey() {
        return this.checkKey().getEncoded();
    }

    private SecretKey checkKey() {
        try {
            if (this.p_Key == null) {
                KeyGenerator keygen = KeyGenerator.getInstance(this.p_Algorithm);
                /*
                 SecureRandom sr = new SecureRandom(key.getBytes());
                 keygen.init(168, sr);*/
                this.p_Key = keygen.generateKey();
            }
        } catch (Exception nsae) {
        }
        return this.p_Key;
    }

    public void setKey(byte[] enckey) {
        this.p_Key = new SecretKeySpec(enckey, this.p_Algorithm);
    }

    public byte[] encode(byte[] data) throws Exception {
        this.p_Cipher.init(Cipher.ENCRYPT_MODE, this.checkKey());
        return this.p_Cipher.doFinal(data);
    }

    public byte[] decode(byte[] encdata, byte[] enckey) throws Exception {
        this.setKey(enckey);
        this.p_Cipher.init(Cipher.DECRYPT_MODE, this.p_Key);
        return this.p_Cipher.doFinal(encdata);
    }

    public String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int i = 0; i < b.length; i++) {
            stmp = Integer.toHexString(b[i] & 0xFF);
            if (stmp.length() == 1) {
                hs += "0" + stmp;
            } else {
                hs += stmp;
            }
        }
        return hs.toUpperCase();
    }

    public byte[] hex2byte(String hex) throws IllegalArgumentException {
        if (hex.length() % 2 != 0) {
            System.out.println("hex:" + hex + "\nlength:" + hex.length());
            throw new IllegalArgumentException();
        }
        char[] arr = hex.toCharArray();
        byte[] b = new byte[hex.length() / 2];
        for (int i = 0, j = 0, l = hex.length(); i < l; i++, j++) {
            String swap = "" + arr[i++] + arr[i];
            int byteint = Integer.parseInt(swap, 16) & 0xFF;
            b[j] = new Integer(byteint).byteValue();
        }
        return b;
    }

    public static String encrypt(String s) throws Exception {
        // byte[] key; //��Կ�ļ�(byte)
        if (null == _instance) {
            _instance = new DES(DES._DESede);
        }
        // key = _instance.getKey();
        _instance.setKey(_instance.hex2byte(_instance.hexKey));
        // String hexkey = _instance.byte2hex(key); //���ʮ�������Կ
        byte[] enc = _instance.encode(s.getBytes()); // ��ɼ����ļ�(byte)
        String hexenc = _instance.byte2hex(enc);
        // System.out.println("hexkey:"+hexkey);
        // System.out.println("hexenc:"+hexenc);
        return hexenc;
    }

    public static String decrypt(String s) throws Exception {
        if (null == _instance) {
            _instance = new DES(DES._DESede);
        }
        return new String(_instance.decode(_instance.hex2byte(s), _instance.hex2byte(_instance.hexKey)));
    }

    // public static void main(String[] args)
    // throws Exception
    // {
    // //String passwd = DES.encrypt("aaaabbccaaaabbccaaaabbccaaaaddde");
    // //System.out.println(passwd);
    // System.out.println(DES.encrypt("ai4a"));
    //
    // }

    /**
     * ʹ��BASE64���н������
     *
     * @param strTemp String
     * @return String
     */
    public static String useHexDecoder(String strTemp) {
        Hex hex = new Hex();
        byte[] bytes = null;
        String strReturn = "";
        if (Objects.nonNull(strTemp) && !strTemp.isEmpty()) {
            try {
                bytes = hex.decode(strTemp.getBytes());
            } catch (DecoderException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            // new sun.misc.BASE64Decoder().decodeBuffer(strTemp);
            strReturn = new String(bytes);
        }
        return strReturn;
    }

    /**
     * ʹ��BASE64���м��ܲ���
     *
     * @param strTemp String
     * @return String
     */
    public static String useHexEncode(String strTemp) {
        Hex hex = new Hex();
        byte[] bytes = null;
        String strReturn = "";
        if (Objects.nonNull(strTemp) && !strTemp.isEmpty()) {

            bytes = hex.encode(strTemp.getBytes());
            strReturn = new String(bytes);
        }
        return strReturn;

    }

    public static void main(String[] args) {

        String ff = "            ";
        System.out.println(ff.trim());
        System.out.println(ff.trim().length());
        System.out.println(ff.trim().isEmpty());
        try {
            System.out.println(DES.encrypt("zhangjiaqi1"));
            System.out.println(DES.encrypt("hwYBw5a2"));
            System.out.println(DES.encrypt("1qaz2WSX"));
            System.out.println(DES.decrypt("237AF1E81BB892448E636BD3468BFF36"));
            System.out.println(DES.decrypt("23AE716AF1169CD4D8AF9B27947F5CC8"));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // System.out.println(useHexDecoder(useHexEncode("abcd_123%")));
    }

}
