/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：MyMediator.java
 * 版本信息：
 * 日期：2018年1月27日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.mediator.example;

/**
 * MyMediator
 * 
 * @author：youngzil@163.com
 * @2018年1月27日 下午1:35:19
 * @since 1.0
 */
public class MyMediator implements Mediator {

    private User user1;
    private User user2;

    public User getUser1() {
        return user1;
    }

    public User getUser2() {
        return user2;
    }

    @Override
    public void createMediator() {
        user1 = new User1(this);
        user2 = new User2(this);
    }

    @Override
    public void workAll() {
        user1.work();
        user2.work();
    }
}
