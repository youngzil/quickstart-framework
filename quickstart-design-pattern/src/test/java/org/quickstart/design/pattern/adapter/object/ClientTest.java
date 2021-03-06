/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：ClientTest.java
 * 版本信息：
 * 日期：2018年1月26日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.adapter.object;

import org.quickstart.design.pattern.adapter.clazz.Adaptee;
import org.quickstart.design.pattern.adapter.clazz.ConcreteTarget;
import org.quickstart.design.pattern.adapter.clazz.Target;

/**
 * ClientTest
 * 
 * @author：youngzil@163.com
 * @2018年1月26日 下午7:50:27
 * @since 1.0
 */
public class ClientTest {

    public static void main(String[] args) {
        // 使用普通功能类
        Target concreteTarget = new ConcreteTarget();
        concreteTarget.request();

        // 使用特殊功能类，即适配类，
        // 需要先创建一个被适配类的对象作为参数
        Target adapter = new Adapter(new Adaptee());
        adapter.request();
    }

}
