/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：ColleagueA.java
 * 版本信息：
 * 日期：2018年1月27日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.mediator;

/**
 * ColleagueA
 * 
 * @author：youngzil@163.com
 * @2018年1月27日 下午1:30:52
 * @since 1.0
 */
public class ColleagueA extends AbstractColleague {

    public void setNumber(int number, AbstractMediator am) {
        this.number = number;
        am.AaffectB();
    }
}
