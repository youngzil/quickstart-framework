/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：ModuleFacade.java
 * 版本信息：
 * 日期：2018年1月26日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.facade.example;

/**
 * ModuleFacade
 * 
 * @author：youngzil@163.com
 * @2018年1月26日 下午8:39:19
 * @since 1.0
 */
public class ModuleFacade {

    ModuleA a = new ModuleA();
    ModuleB b = new ModuleB();
    ModuleC c = new ModuleC();

    /**
     * 下面这些是A、B、C模块对子系统外部提供的方法
     */
    public void a1() {
        a.a1();
    }

    public void b1() {
        b.b1();
    }

    public void c1() {
        c.c1();
    }

}
