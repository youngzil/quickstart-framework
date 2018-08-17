/**
 * 项目名称：quickstart-proxy 
 * 文件名：ElectricCar.java
 * 版本信息：
 * 日期：2018年8月11日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.proxy.dynamic.sample2;

/**
 * ElectricCar 
 *  
 * @author：yangzl@asiainfo.com
 * @2018年8月11日 下午11:55:09 
 * @since 1.0
 */
/**
 * 电能车类，实现Rechargable，Vehicle接口
 * @author louluan
 */
public class ElectricCar implements Rechargable, Vehicle {
 
    @Override
    public void drive() {
        System.out.println("Electric Car is Moving silently...");
    }
 
    @Override
    public void recharge() {
        System.out.println("Electric Car is Recharging...");
    }
 
}

