package com.quickstart.test.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicSubject implements InvocationHandler {

    private Object real;

    public DynamicSubject() {}

    public DynamicSubject(Object obj) {
        real = obj;
    }

    public void bind(Object obj) {
        real = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(" before calling " + method);
        method.invoke(real, args);
        System.out.println(" after calling " + method);
        return null;
    }

}
