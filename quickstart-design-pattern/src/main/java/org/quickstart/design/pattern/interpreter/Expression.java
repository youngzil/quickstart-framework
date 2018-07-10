/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：Expression.java
 * 版本信息：
 * 日期：2018年1月27日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.interpreter;

/**
 * Expression
 * 
 * @author：yangzl@asiainfo.com
 * @2018年1月27日 下午1:38:49
 * @since 1.0
 */
public interface Expression {
    public int interpret(Context context);
}
