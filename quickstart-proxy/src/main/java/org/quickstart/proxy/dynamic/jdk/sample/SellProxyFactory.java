package org.quickstart.proxy.dynamic.jdk.sample;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * <p>描述: [功能描述] </p >
 *
 * @author yangzl
 * @date 2020/8/27 17:30
 * @version v1.0
 */
public class SellProxyFactory implements InvocationHandler {
    /** 代理的真实对象 */
    private Object realObject;

    public SellProxyFactory(Object realObject) {
        this.realObject = realObject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        doSomethingBefore();
        Object obj = method.invoke(realObject, args);
        doSomethingAfter();
        return obj;
    }

    private void doSomethingAfter() {
        System.out.println("执行代理后的额外操作...");
    }

    private void doSomethingBefore() {
        System.out.println("执行代理前的额外操作...");
    }

}
