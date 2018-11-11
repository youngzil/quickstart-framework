/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：PersonDirector.java
 * 版本信息：
 * 日期：2018年1月26日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.builder;

/**
 * PersonDirector
 * 
 * @author：youngzil@163.com
 * @2018年1月26日 下午5:05:32
 * @since 1.0
 */
public class PersonDirector {

    public Person constructPerson(PersonBuilder pb) {
        pb.buildHead();
        pb.buildBody();
        pb.buildFoot();
        return pb.buildPerson();
    }

}
