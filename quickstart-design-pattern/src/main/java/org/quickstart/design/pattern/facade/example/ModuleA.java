/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：Module.java
 * 版本信息：
 * 日期：2018年1月26日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.facade.example;

/**
 * Module
 * 
 * @author：youngzil@163.com
 * @2018年1月26日 下午8:38:04
 * @since 1.0
 */
public class ModuleA {

    /**
     * 提供给子系统外部使用的方法
     */
    public void a1() {};

    /**
     * 子系统内部模块之间相互调用时使用的方法
     */
    private void a2() {};

    private void a3() {};

}
