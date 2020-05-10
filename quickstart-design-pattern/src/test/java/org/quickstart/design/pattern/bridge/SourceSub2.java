/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：SourceSub2.java
 * 版本信息：
 * 日期：2018年1月26日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.bridge;

/**
 * SourceSub2
 * 
 * @author：youngzil@163.com
 * @2018年1月26日 下午8:44:38
 * @since 1.0
 */
public class SourceSub2 implements Sourceable {

    @Override
    public void method() {
        System.out.println("this is the second sub!");
    }
}
