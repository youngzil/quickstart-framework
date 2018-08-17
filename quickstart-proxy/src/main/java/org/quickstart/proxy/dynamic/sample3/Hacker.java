/**
 * 项目名称：quickstart-proxy 
 * 文件名：Hacker.java
 * 版本信息：
 * 日期：2018年8月12日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.proxy.dynamic.sample3;

/**
 * Hacker 
 *  
 * @author：yangzl@asiainfo.com
 * @2018年8月12日 上午12:10:38 
 * @since 1.0
 */
import java.lang.reflect.Method;
 
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
/*
 * 实现了方法拦截器接口
 */
public class Hacker implements MethodInterceptor {
    @Override
    public Object intercept(Object obj, Method method, Object[] args,
            MethodProxy proxy) throws Throwable {
        System.out.println("**** I am a hacker,Let's see what the poor programmer is doing Now...");
        proxy.invokeSuper(obj, args);
        System.out.println("****  Oh,what a poor programmer.....");
        return null;
    }
 
}
