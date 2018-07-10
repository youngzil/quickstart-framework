/**
 * 项目名称：quickstart-spring-framework 
 * 文件名：BeanPostProcessorTest.java
 * 版本信息：
 * 日期：2018年1月16日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.spring.framework.interfaces;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * BeanPostProcessorTest
 * 
 * @author：yangzl@asiainfo.com
 * @2018年1月16日 下午10:19:09
 * @since 1.0
 */
// BeanPostProcessor是BeanFactory的钩子允许用户对新建的Bean进行修改
// 通过实现BeanPostProcessor接口允许用户对新建的Bean进行修改。
@Component
public class BeanPostProcessorTest implements BeanPostProcessor {

    private Log log = LogFactory.getLog(getClass());

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        log.info("--------初始化之后调用：bean=" + bean + ",beanName=" + beanName);
        return bean;
    }

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        log.info("--------初始化之前调用：bean=" + bean + ",beanName=" + beanName);
        return bean;
    }

}
