/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：BlackEnemy.java
 * 版本信息：
 * 日期：2018年1月26日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.strategy;

/**
 * BlackEnemy
 * 
 * @author：yangzl@asiainfo.com
 * @2018年1月26日 下午11:39:36
 * @since 1.0
 */
public class BlackEnemy implements Strategy {
    @Override
    public void operate() {
        System.out.println("孙夫人断后，挡住追兵");
    }
}
