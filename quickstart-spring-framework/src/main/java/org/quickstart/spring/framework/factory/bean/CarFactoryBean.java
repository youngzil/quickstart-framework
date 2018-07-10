/**
 * 项目名称：quickstart-spring-framework 
 * 文件名：CarFactoryBean.java
 * 版本信息：
 * 日期：2018年1月17日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.spring.framework.factory.bean;

import org.springframework.beans.factory.FactoryBean;

/**
 * CarFactoryBean
 * 
 * @author：yangzl@asiainfo.com
 * @2018年1月17日 上午9:47:41
 * @since 1.0
 */
public class CarFactoryBean implements FactoryBean<Car> {

    private String brand;

    public void setBrand(String brand) {
        this.brand = brand;
    }

    // 返回bean的对象
    @Override
    public Car getObject() throws Exception {
        return new Car(brand, 500000);
    }

    // 返回的bean的类型
    @Override
    public Class<?> getObjectType() {
        // TODO Auto-generated method stub
        return Car.class;
    }

    // 是否是单例的
    @Override
    public boolean isSingleton() {
        // TODO Auto-generated method stub
        return true;
    }

}
