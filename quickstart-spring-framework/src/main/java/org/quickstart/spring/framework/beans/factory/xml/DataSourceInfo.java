/**
 * 项目名称：quickstart-spring-framework 
 * 文件名：DataSourceInfo.java
 * 版本信息：
 * 日期：2018年4月16日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.spring.framework.beans.factory.xml;

/**
 * DataSourceInfo
 * 
 * @author：yangzl@asiainfo.com
 * @2018年4月16日 下午3:48:03
 * @since 1.0
 */
public class DataSourceInfo {

    private String url;
    private String userName;
    private String password;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "{url=" + url + ",userName=" + userName + ",password=" + password + "}";
    }

}
