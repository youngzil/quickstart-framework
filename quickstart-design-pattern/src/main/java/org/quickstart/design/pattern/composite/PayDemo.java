/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：PayDemo.java
 * 版本信息：
 * 日期：2018年1月26日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * PayDemo
 * 
 * @author：youngzil@163.com
 * @2018年1月26日 下午11:00:07
 * @since 1.0
 */
public class PayDemo {

    public abstract class Market {
        String name;

        public abstract void add(Market m);

        public abstract void remove(Market m);

        public abstract void PayByCard();
    }

    // 分店 下面可以有加盟店
    public class MarketBranch extends Market {
        // 加盟店列表
        List<Market> list = new ArrayList<PayDemo.Market>();

        public MarketBranch(String s) {
            this.name = s;
        }

        @Override
        public void add(Market m) {
            // TODO Auto-generated method stub
            list.add(m);
        }

        @Override
        public void remove(Market m) {
            // TODO Auto-generated method stub
            list.remove(m);
        }

        // 消费之后，该分店下的加盟店自动累加积分
        @Override
        public void PayByCard() {
            // TODO Auto-generated method stub
            System.out.println(name + "消费,积分已累加入该会员卡");
            for (Market m : list) {
                m.PayByCard();
            }
        }
    }

    // 加盟店 下面不在有分店和加盟店，最底层
    public class MarketJoin extends Market {
        public MarketJoin(String s) {
            this.name = s;

        }

        @Override
        public void add(Market m) {
            // TODO Auto-generated method stub

        }

        @Override
        public void remove(Market m) {
            // TODO Auto-generated method stub

        }

        @Override
        public void PayByCard() {
            // TODO Auto-generated method stub
            System.out.println(name + "消费,积分已累加入该会员卡");
        }
    }

    public static void main(String[] args) {
        PayDemo demo = new PayDemo();

        MarketBranch rootBranch = demo.new MarketBranch("总店");
        MarketBranch qhdBranch = demo.new MarketBranch("秦皇岛分店");
        MarketJoin hgqJoin = demo.new MarketJoin("秦皇岛分店一海港区加盟店");
        MarketJoin btlJoin = demo.new MarketJoin("秦皇岛分店二白塔岭加盟店");

        qhdBranch.add(hgqJoin);
        qhdBranch.add(btlJoin);
        rootBranch.add(qhdBranch);
        rootBranch.PayByCard();
    }
}
