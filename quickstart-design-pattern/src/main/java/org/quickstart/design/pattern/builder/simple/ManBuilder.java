/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：ManBuilder.java
 * 版本信息：
 * 日期：2018年1月26日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.builder.simple;

/**
 * ManBuilder
 * 
 * @author：youngzil@163.com
 * @2018年1月26日 下午5:07:40
 * @since 1.0
 */
public class ManBuilder {

    Man man;

    public ManBuilder() {
        man = new Man();
    }

    public void buildBody() {
        man.setBody("建造男人的身体--simple");
    }

    public void buildFoot() {
        man.setFoot("建造男人的脚--simple");
    }

    public void buildHead() {
        man.setHead("建造男人的头--simple");
    }

    public Man builderMan() {
        buildHead();
        buildBody();
        buildFoot();
        return man;
    }

}
