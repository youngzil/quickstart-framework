/**
 * 项目名称：quickstart-spring-framework 
 * 文件名：DatasourceNamespaceHandlerSupport.java
 * 版本信息：
 * 日期：2018年4月16日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.spring.framework.beans.factory.xml;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * DatasourceNamespaceHandlerSupport
 * 
 * @author：yangzl@asiainfo.com
 * @2018年4月16日 下午3:58:45
 * @since 1.0
 */
public class DatasourceNamespaceHandlerSupport extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("datasource", new DatasourceBeanDefinitionParser());
    }
}
