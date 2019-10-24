/**
 * 项目名称：quickstart-guice 文件名：MyAppModule.java 版本信息： 日期：2018年5月21日 Copyright yangzl Corporation 2018 版权所有 *
 */
package org.quickstart.guice.sample;

import org.quickstart.guice.sample.service.Application;
import org.quickstart.guice.sample.service.ApplicationProvider;
import org.quickstart.guice.sample.service.LogService;
import org.quickstart.guice.sample.service.UserService;
import org.quickstart.guice.sample.service.UserServiceProvides;
import org.quickstart.guice.sample.service.impl.AppProvider;
import org.quickstart.guice.sample.service.impl.LogServiceImpl;
import org.quickstart.guice.sample.service.impl.MyApp;
import org.quickstart.guice.sample.service.impl.UserServiceImpl;
import org.quickstart.guice.sample.service.impl.UserServiceImplChild;
import org.quickstart.guice.sample.service.impl.UserServiceImplProvides;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

/**
 * MyAppModule
 * 
 * @author：youngzil@163.com
 * @2018年5月21日 下午5:09:58
 * @since 1.0
 */
public class MyAppModule extends AbstractModule {

  // 首先来配置依赖关系。我们继承AbstractModule类，并重写configure方法即可。
  // 在configure方法中，我们可以调用AbstractModule类提供的一些方法来配置依赖关系。最常用的方式就是bind(接口或父类).to(实现类或子类)的方式来设置依赖关系。
  // 当Guice遇到接口或父类需要注入具体实现的时候，就会使用这里配置的实现类或子类来注入。如果希望在构造器中注入依赖的话，只需要添加@Inject注解即可。

  @Override
  protected void configure() {
    bind(LogService.class).to(LogServiceImpl.class);

    // 在绑定依赖的时候不仅可以将父类和子类绑定，还可以将子类和更具体的子类绑定，返回的是最后的子类

    bind(UserService.class).to(UserServiceImpl.class);
    bind(UserServiceImpl.class).to(UserServiceImplChild.class);
    bind(Application.class).to(MyApp.class);
    bind(ApplicationProvider.class).toProvider(AppProvider.class);
  }

  // 当一个对象很复杂，无法使用简单的构造器来生成的时候，我们可以使用@Provides方法，也就是在配置类中生成一个注解了@Provides的方法。在该方法中我们可以编写任意代码来构造对象。
  @Provides
  UserServiceProvides provideTransactionLog() {
    return new UserServiceImplProvides();
  }

}
