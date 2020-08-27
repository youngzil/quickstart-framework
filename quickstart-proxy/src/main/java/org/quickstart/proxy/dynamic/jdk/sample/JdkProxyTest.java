package org.quickstart.proxy.dynamic.jdk.sample;

import org.quickstart.proxy.statics.jdk.sample.ChanelFactory;
import org.quickstart.proxy.statics.jdk.sample.SellPerfume;

import java.lang.reflect.Proxy;

/**
 * <p>描述: [功能描述] </p >
 *
 * @author yangzl
 * @date 2020/8/27 17:32
 * @version v1.0
 */
public class JdkProxyTest {

    public static void main(String[] args) {
        ChanelFactory chanelFactory = new ChanelFactory();
        SellProxyFactory sellProxyFactory = new SellProxyFactory(chanelFactory);
        SellPerfume sellPerfume = (SellPerfume)Proxy
            .newProxyInstance(chanelFactory.getClass().getClassLoader(), chanelFactory.getClass().getInterfaces(),
                sellProxyFactory);
        sellPerfume.sellPerfume(1999.99);

        // 实例化一个红酒销售商
        RedWineFactory redWineFactory = new RedWineFactory();
        // 实例化代理工厂，传入红酒销售商引用控制对其的访问
        SellProxyFactory redWinesellProxyFactory = new SellProxyFactory(redWineFactory);
        // 实例化代理对象，该对象可以代理售卖红酒
        SellWine sellWineProxy = (SellWine)Proxy
            .newProxyInstance(redWineFactory.getClass().getClassLoader(), redWineFactory.getClass().getInterfaces(),
                redWinesellProxyFactory);
        // 代理售卖红酒
        sellWineProxy.sellWine(1999.99);

    }

}
