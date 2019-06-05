/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：AbstractHandler.java
 * 版本信息：
 * 日期：2018年1月27日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.responsibility.chain.example;

/**
 * AbstractHandler
 * 
 * @author：youngzil@163.com
 * @2018年1月27日 上午10:00:49
 * @since 1.0
 */
public abstract class AbstractHandler {

    private Handler handler;

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

}
