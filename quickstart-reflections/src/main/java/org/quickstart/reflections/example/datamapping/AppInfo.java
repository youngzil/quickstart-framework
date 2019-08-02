package org.quickstart.reflections.example.datamapping;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * AOP_API_APP 应用基本信息表
 */
@Setter
@Getter
@ToString
public class AppInfo implements Serializable {

  private static final long serialVersionUID = 1L;

  private String id;

  private String appCode;
  private String appName;
  private String appDesc;

  /**
   * '状态枚举值 0：待审核 1：上线 2：订阅者下线。订阅者自己操作的下线，订阅者和运维者都可以在操作再次上线。 3：运维者下线。运维者操作的下线，运维者可以操作再次上线，订阅者不可以操作再次上线。 4：审核不通过';
   */
  private String status;

  /******************************* 身份认证start **************************/

  /**
   * 授权方式枚举值 1：客户端模式 2：授权码模式'
   *
   * 授权方式 authorization_code 授权码模式 client_credentials客户端模式 implicit 简化模式';
   */
  private String grantType;

  /******************************* 加解密start **************************/

  /**
   * 参数加解密类型：单个字段分别加解密 or 整体加解密
   */
  // private String encryptType;

  /**
   * 加解密算法：AES、RSA
   */
  private String encryptMethod;

  /******************************* 数字签名start **************************/

  /**
   * '枚举值: HmacSHA256 or RSAWithMD5
   *
   */
  private String signMethod;

  /**
   * AES对称加密秘钥
   */
  private String appSecretKey;

  /**
   * Public加密Private解密 or Private加密Public解密
   */
  private String rsaPublic;
  private String rsaPrivate;

  private String redirectUri;

}
