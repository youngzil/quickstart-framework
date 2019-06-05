/**
 * 项目名称：quickstart-javase 
 * 文件名：TestDynamic.java
 * 版本信息：
 * 日期：2018年6月11日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.jmx.example;

/**
 * TestDynamic 
 *  
 * @author：youngzil@163.com
 * @2018年6月11日 下午8:33:19 
 * @since 1.0
 */
import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.AttributeNotFoundException;
import javax.management.DynamicMBean;
import javax.management.InvalidAttributeValueException;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanException;
import javax.management.MBeanInfo;
import javax.management.MBeanOperationInfo;
import javax.management.ReflectionException;
public class TestDynamic implements DynamicMBean {
    private String name = "iamzhongyong";
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public void printName(){
        System.out.println(name);
    }
    public Object getAttribute(String attribute)
            throws AttributeNotFoundException, MBeanException,
            ReflectionException {
        if(attribute==null){
            throw new AttributeNotFoundException();
        }
        if("name".equalsIgnoreCase(attribute)){
            return getName();
        }
        throw new AttributeNotFoundException();
    }
 
    public void setAttribute(Attribute attribute)
            throws AttributeNotFoundException, InvalidAttributeValueException,
            MBeanException, ReflectionException {
        String name = attribute.getName();
        Object value = attribute.getValue();
        if("name".equalsIgnoreCase(name)){
            this.setName(String.valueOf(value));
            return;
        }
        throw new AttributeNotFoundException();
    }
 
    public AttributeList getAttributes(String[] attributes) {
        return null;
    }
 
    public AttributeList setAttributes(AttributeList attributes) {
        return null;
    }
 
    public Object invoke(String actionName, Object[] params, String[] signature)
            throws MBeanException, ReflectionException {
        if("printName".equals(actionName)){
            printName();
        }
        return null;
    }
 
    public MBeanInfo getMBeanInfo() {
        MBeanAttributeInfo[] dAttributes = new MBeanAttributeInfo[] {
                new MBeanAttributeInfo("name", "String", "缓存名称", true,  true, false)};
        MBeanOperationInfo opers[] = {
                new MBeanOperationInfo("printName","print",null,"void",MBeanOperationInfo.ACTION)};
 
        MBeanInfo info = new MBeanInfo(this.getClass().getName(),
                                        "TestDynamic",
                                        dAttributes,
                                        null,
                                        opers,
                                        null);
        return info;
    }
}
