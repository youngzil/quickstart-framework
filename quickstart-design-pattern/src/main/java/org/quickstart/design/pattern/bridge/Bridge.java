/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：Bridge.java
 * 版本信息：
 * 日期：2018年1月26日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.bridge;

/**
 * Bridge
 * 
 * @author：yangzl@asiainfo.com
 * @2018年1月26日 下午8:44:57
 * @since 1.0
 */
public abstract class Bridge {
    private Sourceable source;

    public void method() {
        source.method();
    }

    public Sourceable getSource() {
        return source;
    }

    public void setSource(Sourceable source) {
        this.source = source;
    }
}
