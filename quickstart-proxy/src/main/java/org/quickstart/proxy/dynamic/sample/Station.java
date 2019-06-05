/**
 * 项目名称：quickstart-proxy 
 * 文件名：Station.java
 * 版本信息：
 * 日期：2018年8月11日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.proxy.dynamic.sample;

/**
 * Station 
 *  
 * @author：youngzil@163.com
 * @2018年8月11日 下午11:37:49 
 * @since 1.0
 */

/**
 * 售票服务接口实现类，车站
 * @author louluan
 */
public class Station implements TicketService {
 
    @Override
    public void sellTicket() {
        System.out.println("\n\t售票.....\n");
    }
 
    @Override
    public void inquire() {
        System.out.println("\n\t问询。。。。\n");
    }
 
    @Override
    public void withdraw() {
        System.out.println("\n\t退票......\n");
    }
 
}

