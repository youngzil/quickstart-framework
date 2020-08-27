package org.quickstart.proxy.dynamic.cglib.sample;

import org.quickstart.proxy.statics.jdk.sample.ChanelFactory;
import org.quickstart.proxy.statics.jdk.sample.SellPerfume;

/**
 * <p>描述: [功能描述] </p >
 *
 * @author yangzl
 * @date 2020/8/27 17:51
 * @version v1.0
 */
public class CglibProxyTest {

    public static void main(String[] args) {
        SellProxyFactory sellProxyFactory = new SellProxyFactory();
        // 获取一个代理实例
        SellPerfume proxyInstance =
            (SellPerfume) sellProxyFactory.getProxyInstance(new ChanelFactory());
        // 创建代理类
        proxyInstance.sellPerfume(1999.99);
    }

}
