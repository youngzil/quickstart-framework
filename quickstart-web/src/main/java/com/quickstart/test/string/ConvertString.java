/**
 * 项目名称：quickstart-web 
 * 文件名：ConvertString.java
 * 版本信息：
 * 日期：2017年1月5日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package com.quickstart.test.string;

import java.math.BigDecimal;

/**
 * ConvertString
 * 
 * @author：youngzil@163.com
 * @2017年1月5日 上午9:23:03
 * @version 1.0
 */
public class ConvertString {

    public static void main(String[] args) {
        /*由数字字符串构造BigDecimal的方法 
        *设置BigDecimal的小数位数的方法 
        */
        // 数字字符串
        String StrBd = "1048576.1024";
        // 构造以字符串内容为值的BigDecimal类型的变量bd
        BigDecimal bd = new BigDecimal(StrBd);
        // 设置小数位数，第一个变量是小数位数，第二个变量是取舍方法(四舍五入)
        bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
        // 转化为字符串输出
        String OutString = bd.toString();
    }

}
