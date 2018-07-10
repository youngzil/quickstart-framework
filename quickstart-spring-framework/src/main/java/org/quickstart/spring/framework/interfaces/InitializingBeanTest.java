/**
 * 项目名称：quickstart-spring-framework 
 * 文件名：InitializingBeanTest.java
 * 版本信息：
 * 日期：2018年1月16日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.spring.framework.interfaces;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * InitializingBeanTest
 * 
 * @author：yangzl@asiainfo.com
 * @2018年1月16日 下午10:18:26
 * @since 1.0
 */
// 如果期望在BeanFactory 设置所有的属性后作出进一步的反应可以实现该接口
// 通过实现InitializingBean接口可以在BeanFactory 设置所有的属性后作出进一步的反应可以实现该接口。
@Component
public class InitializingBeanTest implements InitializingBean, BeanNameAware {

    private Log log = LogFactory.getLog(getClass());

    private String beanName;

    public void setBeanName(String beanName) {
        log.info("--------setBeanName:" + beanName);
        this.beanName = beanName;
    }

    public void afterPropertiesSet() throws Exception {
        log.info("--------Bean的属性都被设置完成:" + beanName);
    }
}
