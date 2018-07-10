/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：Adapter.java
 * 版本信息：
 * 日期：2018年1月26日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.adapter.clazz;

/**
 * Adapter
 * 
 * @author：yangzl@asiainfo.com
 * @2018年1月26日 下午7:45:41
 * @since 1.0
 */
// 适配器类，继承了被适配类，同时实现标准接口
public class Adapter extends Adaptee implements Target {
    public void request() {
        super.specificRequest();
    }
}
