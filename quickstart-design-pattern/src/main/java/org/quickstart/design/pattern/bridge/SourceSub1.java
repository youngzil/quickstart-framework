/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：SourceSub1.java
 * 版本信息：
 * 日期：2018年1月26日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.bridge;

/**
 * SourceSub1
 * 
 * @author：yangzl@asiainfo.com
 * @2018年1月26日 下午8:44:22
 * @since 1.0
 */
public class SourceSub1 implements Sourceable {

    @Override
    public void method() {
        System.out.println("this is the first sub!");
    }
}
