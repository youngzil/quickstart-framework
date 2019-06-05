/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：GeneralManager.java
 * 版本信息：
 * 日期：2018年1月27日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.responsibility.chain;

/**
 * GeneralManager
 * 
 * @author：youngzil@163.com
 * @2018年1月27日 上午9:58:23
 * @since 1.0
 */
public class GeneralManager extends Handler {

    @Override
    public String handleFeeRequest(String user, double fee) {

        String str = "";
        // 总经理的权限很大，只要请求到了这里，他都可以处理
        if (fee >= 1000) {
            // 为了测试，简单点，只同意张三的请求
            if ("张三".equals(user)) {
                str = "成功：总经理同意【" + user + "】的聚餐费用，金额为" + fee + "元";
            } else {
                // 其他人一律不同意
                str = "失败：总经理不同意【" + user + "】的聚餐费用，金额为" + fee + "元";
            }
        } else {
            // 如果还有后继的处理对象，继续传递
            if (getSuccessor() != null) {
                return getSuccessor().handleFeeRequest(user, fee);
            }
        }
        return str;
    }

    /* (non-Javadoc)
     * @see org.quickstart.design.pattern.responsibility.chain.Handler#handleRequest()
     */
    @Override
    public void handleRequest() {
        // TODO Auto-generated method stub

    }

}
