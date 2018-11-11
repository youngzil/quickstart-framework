/**
 * 项目名称：quickstart-proxy 
 * 文件名：InvocationHandlerImpl.java
 * 版本信息：
 * 日期：2018年8月11日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.proxy.dynamic.sample2;

/**
 * InvocationHandlerImpl 
 *  
 * @author：youngzil@163.com
 * @2018年8月11日 下午11:55:36 
 * @since 1.0
 */

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class InvocationHandlerImpl implements InvocationHandler {

    private ElectricCar car;

    public InvocationHandlerImpl(ElectricCar car) {
        this.car = car;
    }

    @Override
    public Object invoke(Object paramObject, Method paramMethod, Object[] paramArrayOfObject) throws Throwable {
        System.out.println("You are going to invoke " + paramMethod.getName() + " ...");
        paramMethod.invoke(car, null);
        System.out.println(paramMethod.getName() + " invocation Has Been finished...");
        return null;
    }

}
