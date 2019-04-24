/**
 * 项目名称：quickstart-javase 
 * 文件名：Constants.java
 * 版本信息：
 * 日期：2019年4月24日
 * Copyright asiainfo Corporation 2019
 * 版权所有 *
 */
package org.quickstart.javase;

/**
 * Constants
 * 
 * @author：yangzl@asiainfo.com
 * @2019年4月24日 下午3:02:22
 * @since 1.0
 */
public final class Constants {

    private Constants() {}

    public static final String SUCCESS = "查询成功";
    public static final String FAILURE = "查询失败";
    public static final String EXCEPTION = "查询异常";

    // class的类型为final，表示该类是不可以继承的；
    // 定义了一个私有的构造函数，避免实例化该类；
    // 常量的类型为public final static；

    // 常量优先使用枚举，然后是final常量类，接口不推荐

}
