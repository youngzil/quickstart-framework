package org.quickstart.proxy.statics.javassist.proxy;

import java.lang.reflect.Method;

import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class JavassistProxyFactory<T> {

  private T target;

  public void setTarget(T target) {
    this.target = target;
  }

  @SuppressWarnings("deprecation")
  public T getProxy() throws InstantiationException, IllegalAccessException {
    // 代理工厂
    ProxyFactory proxyFactory = new ProxyFactory();
    // 设置需要创建子类的父类
    proxyFactory.setSuperclass(target.getClass());
    /*
     * 定义一个拦截器。在调用目标方法时，Javassist会回调MethodHandler接口方法拦截，
     * 来实现你自己的代理逻辑，
     * 类似于JDK中的InvocationHandler接口。
     */

    proxyFactory.setHandler(new MethodHandler() {
      /*
       * self为由Javassist动态生成的代理类实例，
       *  thismethod为 当前要调用的方法
       *  proceed 为生成的代理类对方法的代理引用。
       *  Object[]为参数值列表，
       * 返回：从代理实例的方法调用返回的值。
       *
       * 其中，proceed.invoke(self, args);
       *
       * 调用代理类实例上的代理方法的父类方法（即实体类ConcreteClassNoInterface中对应的方法）
       */
      public Object invoke(Object self, Method thismethod, Method proceed, Object[] args)
          throws Throwable {
        System.out.println("--------------------------------");
        System.out.println(self.getClass());
        //class com.javassist.demo.A_$$_javassist_0
        System.out.println("要调用的方法名：" + thismethod.getName());
        System.out.println(proceed.getName());
        System.out.println("开启事务-------");

        Object result = proceed.invoke(self, args);
        //下面的代码效果与上面的相同
        //不过需要传入一个目标对象
        //Object result = thismethod.invoke(target,args);

        System.out.println("提交事务-------");
        return result;
      }
    });

    // 通过字节码技术动态创建子类实例
    return (T) proxyFactory.createClass().newInstance();
  }

  /*public Object getProxy(Class<?> type) throws IllegalAccessException, InstantiationException {
    ProxyFactory f = new ProxyFactory();
    f.setSuperclass(type);
    f.setFilter(new MethodFilter() {
      public boolean isHandled(Method m) {
        // ignore finalize()
        return !m.getName().equals("finalize");
      }
    });

    Class c = f.createClass();
    MethodHandler mi = new MethodHandler() {
      public Object invoke(Object self, Method m, Method proceed,
          Object[] args) throws Throwable {
        System.out.println("method name: " + m.getName()+" exec");
        return proceed.invoke(self, args);  // execute the original method.
      }
    };
    Object proxy = c.newInstance();
    ((Proxy)proxy).setHandler(mi);
    return proxy;
  }*/

}

