/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：GivenGreenLight.java
 * 版本信息：
 * 日期：2018年1月26日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.strategy;

/**
 * GivenGreenLight
 * 
 * @author：youngzil@163.com
 * @2018年1月26日 下午11:39:14
 * @since 1.0
 */
public class GivenGreenLight implements Strategy {
    @Override
    public void operate() {
        System.out.println("求吴国太开个绿灯，放行");
    }
}
