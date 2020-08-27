package org.quickstart.proxy.statics.jdk.sample;

/**
 * <p>描述: [功能描述] </p >
 *
 * @author yangzl
 * @date 2020/8/27 17:14
 * @version v1.0
 */
public class ClientTest {

    public static void main(String[] args) {
        ChanelFactory factory = new ChanelFactory();
        XiaoHongSellProxy proxy = new XiaoHongSellProxy(factory);
        proxy.sellPerfume(1999.99);
    }
}
