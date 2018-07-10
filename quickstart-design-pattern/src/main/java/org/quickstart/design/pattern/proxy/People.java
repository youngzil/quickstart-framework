/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：People.java
 * 版本信息：
 * 日期：2018年1月26日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.proxy;

/**
 * People
 * 
 * @author：yangzl@asiainfo.com
 * @2018年1月26日 下午8:13:18
 * @since 1.0
 */
public class People implements BuyCar {

    private int cash;
    private String vip;
    private String username;

    @Override
    public void buyMyCar() {
        // TODO Auto-generated method stub
        System.out.print(username + "是vip 客户，可以直接购买新车！");
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getVip() {
        return vip;
    }

    public void setVip(String vip) {
        this.vip = vip;
    }

}
