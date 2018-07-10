/**
 * 项目名称：quickstart-spring-framework 
 * 文件名：InstanceCarFactory.java
 * 版本信息：
 * 日期：2018年1月17日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.spring.framework.factory.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * InstanceCarFactory
 * 
 * @author：yangzl@asiainfo.com
 * @2018年1月17日 上午9:50:23
 * @since 1.0
 */
/* 
 * 实例工厂方法：实例工厂的方法，即先需要创建工厂bean本身，再调用工厂的实例方法来 
 *       返回bean的实例 
 */
public class InstanceCarFactory {

    private Map<String, Car> cars = null;

    public InstanceCarFactory() {
        cars = new HashMap<String, Car>();
        cars.put("audi", new Car("Audi", 300000));
        cars.put("ford", new Car("Ford", 400000));
    }

    public Car getCar(String brand) {
        return cars.get(brand);
    }
}
