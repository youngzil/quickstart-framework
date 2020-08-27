package org.quickstart.proxy.dynamic.jdk.sample;

/**
 * <p>描述: [功能描述] </p >
 *
 * @author yangzl
 * @date 2020/8/27 17:38
 * @version v1.0
 */
/** 红酒供应商 */
public class RedWineFactory implements SellWine {

    @Override
    public void sellWine(double price) {
        System.out.println("成功售卖一瓶红酒，价格：" + price + "元");
    }

}
