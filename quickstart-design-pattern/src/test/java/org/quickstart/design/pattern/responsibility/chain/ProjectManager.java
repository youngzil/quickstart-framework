/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：ProjectManager.java
 * 版本信息：
 * 日期：2018年1月27日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.responsibility.chain;

/**
 * ProjectManager
 * 
 * @author：youngzil@163.com
 * @2018年1月27日 上午9:57:05
 * @since 1.0
 */
public class ProjectManager extends Handler {

    @Override
    public String handleFeeRequest(String user, double fee) {

        String str = "";
        // 项目经理权限比较小，只能在500以内
        if (fee < 500) {
            // 为了测试，简单点，只同意张三的请求
            if ("张三".equals(user)) {
                str = "成功：项目经理同意【" + user + "】的聚餐费用，金额为" + fee + "元";
            } else {
                // 其他人一律不同意
                str = "失败：项目经理不同意【" + user + "】的聚餐费用，金额为" + fee + "元";
            }
        } else {
            // 超过500，继续传递给级别更高的人处理
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
