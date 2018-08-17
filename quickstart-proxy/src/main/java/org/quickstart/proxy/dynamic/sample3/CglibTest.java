/**
 * 项目名称：quickstart-proxy 
 * 文件名：CglibTest.java
 * 版本信息：
 * 日期：2018年8月12日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.proxy.dynamic.sample3;

import org.quickstart.proxy.clazz.loader.Programmer;

import net.sf.cglib.proxy.Enhancer;

/**
 * CglibTest
 * 
 * @author：yangzl@asiainfo.com
 * @2018年8月12日 上午12:11:17
 * @since 1.0
 */
public class CglibTest {
    public static void main(String[] args) {
        Programmer progammer = new Programmer();

        Hacker hacker = new Hacker();
        // cglib 中加强器，用来创建动态代理
        Enhancer enhancer = new Enhancer();
        // 设置要创建动态代理的类
        enhancer.setSuperclass(progammer.getClass());
        // 设置回调，这里相当于是对于代理类上所有方法的调用，都会调用CallBack，而Callback则需要实行intercept()方法进行拦截
        enhancer.setCallback(hacker);
        Programmer proxy = (Programmer) enhancer.create();
        proxy.code();

    }

}
