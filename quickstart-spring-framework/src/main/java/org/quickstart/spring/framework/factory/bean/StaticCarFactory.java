/**
 * 项目名称：quickstart-spring-framework 
 * 文件名：StaticCarFactory.java
 * 版本信息：
 * 日期：2018年1月17日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.spring.framework.factory.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * StaticCarFactory
 * 
 * @author：yangzl@asiainfo.com
 * @2018年1月17日 上午9:49:59
 * @since 1.0
 */
/*静态工厂方法：直接调用某一个类的静态方法就可以返回Bean的实例*/
public class StaticCarFactory {

    private static Map<String, Car> cars = new HashMap<String, Car>();

    static {
        cars.put("audi", new Car("Audi", 300000));
        cars.put("ford", new Car("Ford", 400000));
    }

    // 静态工厂方法，不需要创建StaticCarFactory对象情况下，通过该方法可以得到对应实例。
    public static Car getCar(String brand) {
        return cars.get(brand);
    }
}
