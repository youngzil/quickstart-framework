package org.quickstart.proxy.statics.jdk.sample;

/**
 * <p>描述: [功能描述] </p >
 *
 * @author yangzl
 * @date 2020/8/27 17:14
 * @version v1.0
 */
public class XiaoHongSellProxy implements SellPerfume {
    private SellPerfume sellPerfumeFactory;
    public XiaoHongSellProxy(SellPerfume sellPerfumeFactory) {
        this.sellPerfumeFactory = sellPerfumeFactory;
    }
    @Override
    public void sellPerfume(double price) {
        doSomethingBeforeSell(); // 前置增强
        sellPerfumeFactory.sellPerfume(price);
        doSomethingAfterSell(); // 后置增强
    }
    private void doSomethingBeforeSell() {
        System.out.println("小红代理购买香水前的额外操作...");
    }
    private void doSomethingAfterSell() {
        System.out.println("小红代理购买香水后的额外操作...");
    }
}
