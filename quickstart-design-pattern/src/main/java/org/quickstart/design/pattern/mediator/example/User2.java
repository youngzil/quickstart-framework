/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：User2.java
 * 版本信息：
 * 日期：2018年1月27日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.mediator.example;

/**
 * User2
 * 
 * @author：yangzl@asiainfo.com
 * @2018年1月27日 下午1:36:22
 * @since 1.0
 */
public class User2 extends User {

    public User2(Mediator mediator) {
        super(mediator);
    }

    @Override
    public void work() {
        System.out.println("user2 exe!");
    }
}
