/**
 * 项目名称：quickstart-spring-framework 
 * 文件名：BeanNameAwareTest.java
 * 版本信息：
 * 日期：2018年1月16日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.spring.framework.interfaces;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

/**
 * BeanNameAwareTest
 * 
 * @author：yangzl@asiainfo.com
 * @2018年1月16日 下午10:17:30
 * @since 1.0
 */
// 如果Bean想知道在BeanFactory中设置的名字时可以实现该接口
// 通过实现BeanNameAware接口可以知道在BeanFactory中设置的名字时可以实现该接口。
@Component
public class BeanNameAwareTest implements BeanNameAware {

    private Log log = LogFactory.getLog(getClass());

    private String beanName;

    public void setBeanName(String beanName) {
        log.info("--------setBeanName:" + beanName);
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }
}
