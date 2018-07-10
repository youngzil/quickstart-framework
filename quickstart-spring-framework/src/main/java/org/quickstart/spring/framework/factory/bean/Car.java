/**
 * 项目名称：quickstart-spring-framework 
 * 文件名：Car.java
 * 版本信息：
 * 日期：2018年1月17日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.spring.framework.factory.bean;

/**
 * Car
 * 
 * @author：yangzl@asiainfo.com
 * @2018年1月17日 上午9:49:31
 * @since 1.0
 */
public class Car {

    private String brand;

    private double price;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car [brand=" + brand + ", price=" + price + "]";
    }

    public Car(String brand, double price) {
        super();
        this.brand = brand;
        this.price = price;
    }

}
