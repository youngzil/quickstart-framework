/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：User1.java
 * 版本信息：
 * 日期：2018年1月27日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.mediator.example;

/**
 * User1
 * 
 * @author：yangzl@asiainfo.com
 * @2018年1月27日 下午1:36:03
 * @since 1.0
 */
public class User1 extends User {

    public User1(Mediator mediator) {
        super(mediator);
    }

    @Override
    public void work() {
        System.out.println("user1 exe!");
    }
}
