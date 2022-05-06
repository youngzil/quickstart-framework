package org.quickstart.apache.commons.codec;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.net.URLCodec;
import org.junit.jupiter.api.Test;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019/11/9 11:04
 */
public class ExampleTest {

  @Test
  public  void test() throws UnsupportedEncodingException, DecoderException {
    // Base64编码
    System.out.println("===============base64======================");
    Base64 base64 = new Base64();
    String s = base64.encodeToString("测试22222222222".getBytes());
    System.out.println(s);
    String s1 = new String(base64.decode(s));
    System.out.println(s1);

    // MD5摘要运算
    System.out.println("===============MD5======================");
    String result = DigestUtils.md5Hex("测试");
    System.out.println(result);

    // SHA运算和MD5是使用的同一个工具类。
    System.out.println("===============MD5======================");
    URLCodec codec = new URLCodec();
    String s3 = codec.encode("测试", "utf-8");
    System.out.println(s3);

    String s4 = codec.decode(s3, "utf-8");
    System.out.println(s4);


  }

  @Test
  public void testBase64(){
    System.out.println("==========Base64==========");
    //假设密码为123456abc+
    byte[] passwd = "123456abc+".getBytes();
    Base64 base64 = new Base64();
    //加密
    String enPasswd = base64.encodeAsString(passwd);
    System.out.println("加密后：" + enPasswd);
    //解密
    String dePasswd = new String(base64.decode(enPasswd));
    System.out.println("解密后：" + dePasswd);
  }

  @Test
  public void testMD5(){
    System.out.println("==========MD5==========");
    String enPasswd = DigestUtils.md5Hex("123456abc+");
    System.out.println("加密后：" + enPasswd);
  }

  @Test
  public void testURLCodec(){
    System.out.println("==========URLCodec==========");
    URLCodec urlCodec = new URLCodec();
    String data = "陈某某abc+";
    try {
      String encode = urlCodec.encode(data, "UTF-8");
      System.out.println("加密后：" + encode);
      String decode = urlCodec.decode(encode, "UTF-8");
      System.out.println("解密后：" + decode);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
