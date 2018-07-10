/**
 * 项目名称：quickstart-spring-framework 
 * 文件名：MyMergedBeanDefinitionPostProcessor.java
 * 版本信息：
 * 日期：2018年4月17日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.spring.framework.factory.bean.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.MergedBeanDefinitionPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * MyMergedBeanDefinitionPostProcessor
 * 
 * @author：yangzl@asiainfo.com
 * @2018年4月17日 下午3:24:59
 * @since 1.0
 */
public class MyMergedBeanDefinitionPostProcessor implements MergedBeanDefinitionPostProcessor {
    
    public MyMergedBeanDefinitionPostProcessor() {
        super();
        System.out.println("这是MergedBeanDefinitionPostProcessor实现类构造器！！");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("MergedBeanDefinitionPostProcessor接口方法postProcessBeforeInitialization对属性进行更改！");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("MergedBeanDefinitionPostProcessor接口方法postProcessAfterInitialization对属性进行更改！");
        return bean;
    }

    @Override
    public void postProcessMergedBeanDefinition(RootBeanDefinition beanDefinition, Class<?> beanType, String beanName) {
        System.out.println("MergedBeanDefinitionPostProcessor接口方法postProcessMergedBeanDefinition！");
    }

}
