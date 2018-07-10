/**
 * 项目名称：quickstart-spring-framework 
 * 文件名：ApplicationContextAwareTest.java
 * 版本信息：
 * 日期：2018年1月16日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.spring.framework.interfaces;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * ApplicationContextAwareTest
 * 
 * @author：yangzl@asiainfo.com
 * @2018年1月16日 下午10:16:38
 * @since 1.0
 */
// 实现该接口,在ApplicationContext运行的时候被通知并注入ApplicationContext
// 通过实现ApplicationContextAware接口，在ApplicationContext运行的时候被通知并注入ApplicationContext上下文。
@Component
public class ApplicationContextAwareTest implements ApplicationContextAware {

    private Log log = LogFactory.getLog(getClass());

    public void setApplicationContext(ApplicationContext context) throws BeansException {
        log.info("-----------ApplicationContext is runing");
    }
}
