/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：ManBuilder.java
 * 版本信息：
 * 日期：2018年1月26日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.builder;

/**
 * ManBuilder
 * 
 * @author：youngzil@163.com
 * @2018年1月26日 下午5:02:35
 * @since 1.0
 */
public class ManBuilder implements PersonBuilder {
    Person person;

    public ManBuilder() {
        person = new Man();
    }

    @Override
    public void buildHead() {
        person.setHead("建造男人的头");
    }

    @Override
    public void buildBody() {
        person.setBody("建造男人的身体");
    }

    @Override
    public void buildFoot() {
        person.setFoot("建造男人的脚");
    }

    @Override
    public Person buildPerson() {
        return person;
    }
}
