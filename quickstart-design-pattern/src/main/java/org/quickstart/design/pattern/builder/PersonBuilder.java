/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：PersonBuilder.java
 * 版本信息：
 * 日期：2018年1月26日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.builder;

/**
 * PersonBuilder
 * 
 * @author：youngzil@163.com
 * @2018年1月26日 下午5:02:17
 * @since 1.0
 */
public interface PersonBuilder {

    void buildHead();

    void buildBody();

    void buildFoot();

    Person buildPerson();

}
