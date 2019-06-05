/**
 * 项目名称：quickstart-proxy 
 * 文件名：TicketService.java
 * 版本信息：
 * 日期：2018年8月11日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.proxy.dynamic.sample;

/**
 * TicketService 
 * 
 * 售票服务接口 
 *  
 * @author：youngzil@163.com
 * @2018年8月11日 下午11:37:12 
 * @since 1.0
 */
public interface TicketService {
 
    //售票
    public void sellTicket();
    
    //问询
    public void inquire();
    
    //退票
    public void withdraw();
    
}

