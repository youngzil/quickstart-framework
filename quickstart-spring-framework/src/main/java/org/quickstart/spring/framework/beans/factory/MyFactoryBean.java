/**
 * 项目名称：quickstart-spring-framework 
 * 文件名：MyFactoryBean.java
 * 版本信息：
 * 日期：2018年4月16日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.spring.framework.beans.factory;

import java.util.Date;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.FactoryBean;

/**
 * MyFactoryBean
 * 
 * @author：yangzl@asiainfo.com
 * @2018年4月16日 下午5:57:18
 * @since 1.0
 */
public class MyFactoryBean implements FactoryBean<Date>, BeanNameAware {
    private String name;

    @Override
    public Date getObject() throws Exception {
        return new Date();
    }

    @Override
    public Class<?> getObjectType() {
        return Date.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    public void sayName() {
        System.out.println("My name is " + this.name);
    }

    @Override
    public void setBeanName(String name) {
        this.name = name;
    }
}
