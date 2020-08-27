package org.quickstart.proxy.dynamic.cglib.sample;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * <p>描述: [功能描述] </p >
 *
 * @author yangzl
 * @date 2020/8/27 17:49
 * @version v1.0
 */
public class SellProxyFactory implements MethodInterceptor {
    // 关联真实对象，控制对真实对象的访问
    private Object realObject;
    /** 从代理工厂中获取一个代理对象实例，等价于创建小红代理 */
    public Object getProxyInstance(Object realObject) {
        this.realObject = realObject;
        Enhancer enhancer = new Enhancer();
        // 设置需要增强类的类加载器
        enhancer.setClassLoader(realObject.getClass().getClassLoader());
        // 设置被代理类，真实对象
        enhancer.setSuperclass(realObject.getClass());
        // 设置方法拦截器，代理工厂
        enhancer.setCallback(this);
        // 创建代理类
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        doSomethingBefore(); // 前置增强
        Object object = methodProxy.invokeSuper(o, objects);
        doSomethingAfter(); // 后置增强
        return object;
    }

    private void doSomethingBefore() {
        System.out.println("执行方法前额外的操作...");
    }

    private void doSomethingAfter() {
        System.out.println("执行方法后额外的操作...");
    }

}
