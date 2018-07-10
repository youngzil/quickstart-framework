/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：ModuleC.java
 * 版本信息：
 * 日期：2018年1月26日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.facade.example;

/**
 * ModuleC
 * 
 * @author：yangzl@asiainfo.com
 * @2018年1月26日 下午8:38:59
 * @since 1.0
 */
public class ModuleC {

    /**
     * 提供给子系统外部使用的方法
     */
    public void c1() {};

    /**
     * 子系统内部模块之间相互调用时使用的方法
     */
    private void c2() {};

    private void c3() {};

}
