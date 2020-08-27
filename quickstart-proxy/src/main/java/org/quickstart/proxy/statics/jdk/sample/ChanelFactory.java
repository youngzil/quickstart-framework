package org.quickstart.proxy.statics.jdk.sample;

/**
 * <p>描述: [功能描述] </p >
 *
 * @author yangzl
 * @date 2020/8/27 17:13
 * @version v1.0
 */
public class ChanelFactory implements SellPerfume {
    @Override
    public void sellPerfume(double price) {
        System.out.println("成功购买香奈儿品牌的香水，价格是：" + price + "元");
    }
}
