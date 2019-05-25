package org.quickstart.proxy.statics.javassist.dubboproxy;

import com.bytebeats.codelab.javassist.proxy.ProxyFactory;
import java.lang.reflect.InvocationHandler;

public class JavassistProxyFactory implements ProxyFactory {

  @Override
  public <T> T getProxy(Object target, InvocationHandler handler) throws Throwable {
    return (T) ProxyGenerator.newProxyInstance(Thread.currentThread().getContextClassLoader(),
        target.getClass(), handler);
  }
}