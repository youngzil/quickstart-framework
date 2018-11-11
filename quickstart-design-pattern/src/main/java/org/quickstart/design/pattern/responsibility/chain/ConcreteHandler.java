/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：ConcreteHandler.java
 * 版本信息：
 * 日期：2018年1月27日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.responsibility.chain;

/**
 * ConcreteHandler
 * 
 * @author：youngzil@163.com
 * @2018年1月27日 上午9:51:27
 * @since 1.0
 */
public class ConcreteHandler extends Handler {
    /**
     * 处理方法，调用此方法处理请求
     */
    @Override
    public void handleRequest() {
        /**
         * 判断是否有后继的责任对象 如果有，就转发请求给后继的责任对象 如果没有，则处理请求
         */
        if (getSuccessor() != null) {
            System.out.println("放过请求");
            getSuccessor().handleRequest();
        } else {
            System.out.println("处理请求");
        }
    }

    /* (non-Javadoc)
     * @see org.quickstart.design.pattern.responsibility.chain.Handler#handleFeeRequest(java.lang.String, double)
     */
    @Override
    public String handleFeeRequest(String user, double fee) {
        // TODO Auto-generated method stub
        return null;
    }

}
