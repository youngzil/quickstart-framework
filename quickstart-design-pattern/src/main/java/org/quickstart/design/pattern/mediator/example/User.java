/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：User.java
 * 版本信息：
 * 日期：2018年1月27日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.mediator.example;

/**
 * User
 * 
 * @author：yangzl@asiainfo.com
 * @2018年1月27日 下午1:35:36
 * @since 1.0
 */
public abstract class User {

    private Mediator mediator;

    public Mediator getMediator() {
        return mediator;
    }

    public User(Mediator mediator) {
        this.mediator = mediator;
    }

    public abstract void work();
}
