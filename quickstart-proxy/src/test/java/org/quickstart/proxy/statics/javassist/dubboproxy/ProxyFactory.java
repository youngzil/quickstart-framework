package org.quickstart.proxy.statics.javassist.dubboproxy;

import java.lang.reflect.InvocationHandler;


public interface ProxyFactory {

  <T> T getProxy(Object target, InvocationHandler handler) throws Throwable;
}
