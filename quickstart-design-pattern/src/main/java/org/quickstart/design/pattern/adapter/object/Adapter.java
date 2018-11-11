/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：Adapter.java
 * 版本信息：
 * 日期：2018年1月26日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.adapter.object;

import org.quickstart.design.pattern.adapter.clazz.Adaptee;
import org.quickstart.design.pattern.adapter.clazz.Target;

/**
 * Adapter
 * 
 * @author：youngzil@163.com
 * @2018年1月26日 下午7:49:35
 * @since 1.0
 */
// 适配器类，直接关联被适配类，同时实现标准接口
public class Adapter implements Target {
    // 直接关联被适配类
    private Adaptee adaptee;

    // 可以通过构造函数传入具体需要适配的被适配类对象
    public Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    public void request() {
        // 这里是使用委托的方式完成特殊功能
        this.adaptee.specificRequest();
    }
}
