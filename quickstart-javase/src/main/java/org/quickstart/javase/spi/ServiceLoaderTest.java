/**
 * 项目名称：quickstart-javase 
 * 文件名：ServiceLoaderTest.java
 * 版本信息：
 * 日期：2017年8月22日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.spi;

import java.util.ServiceLoader;

/**
 * ServiceLoaderTest
 * 
 * @author：youngzil@163.com
 * @2017年8月22日 下午4:00:28
 * @since 1.0
 */
public class ServiceLoaderTest {

    /* 1、还有为什么要用spi机制，只是为了方便吗？
    感觉是为了面向接口编程，接口与实现完全分离，不需要Class.forName(“XXX”) 代码(个人理解)
    2、spi加载的类通过Class.forName()是不是同样可以加载？
    当然可以
    3、spi所用的classLoader与Class.forName()所用的是不是同一个loader？
    spi所用的classLoader 是当前类的类加载器，是AppClassLoader，通过Thread.currentThread().getContextClassLoader() 得到
    Class.forName()使用的是BootStrapClassLoader*/

    public static void main(String[] args) {
        ServiceLoader<IService> serviceLoader = ServiceLoader.load(IService.class);
        for (IService service : serviceLoader) {
            System.out.println(service.getScheme() + "=" + service.sayHello());
        }
    }

}
