package com.quickstart.test.proxy;

import java.lang.reflect.Proxy;

public class Client {

    public static void main(String[] args) {

        RealSubject rs = new RealSubject();
        DynamicSubject ds = new DynamicSubject(rs);
        Class<? extends RealSubject> clazz = rs.getClass();

        Subject sub = (Subject) Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), ds);

        sub.request();
    }

}
