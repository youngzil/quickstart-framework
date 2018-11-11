/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：WomanBuilder.java
 * 版本信息：
 * 日期：2018年1月26日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.builder;

/**
 * WomanBuilder
 * 
 * @author：youngzil@163.com
 * @2018年1月26日 下午5:04:14
 * @since 1.0
 */
public class WomanBuilder implements PersonBuilder {
    Person person;

    public WomanBuilder() {
        person = new Woman();
    }

    @Override
    public void buildHead() {
        person.setHead("建造女人的头");
    }

    @Override
    public void buildBody() {
        person.setBody("建造女人的身体");
    }

    @Override
    public void buildFoot() {
        person.setFoot("建造女人的脚");
    }

    @Override
    public Person buildPerson() {
        return person;
    }
}
