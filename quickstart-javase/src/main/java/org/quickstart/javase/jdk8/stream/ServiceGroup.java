package org.quickstart.javase.jdk8.stream;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * AOP_API_ROUTE服务路由表
 */
@Setter
@Getter
@ToString
public class ServiceGroup implements Serializable {

  private static final long serialVersionUID = 1L;

  private String id;

  private String apiCode;

  private String apiVersion;

  /**
   * '枚举值 E：失效 U：有效';
   */
  private String status;

  private String labelCode;

  /**
   * 存储采取模式 type1|key1|equals1|value1;type2|key2|equals2|value2
   *
   * type:是特征值类型，0：普通 1：正则表达式 equals:是等号，0：不等于 1：等于';
   *
   * 判断： 多个记录使用分号分割，使用&&判断，全部匹配
   */
  private String featureValue;

  private String groupCode;

}
