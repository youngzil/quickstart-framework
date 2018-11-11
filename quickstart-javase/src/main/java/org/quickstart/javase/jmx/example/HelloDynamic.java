/**
 * 项目名称：quickstart-javase 
 * 文件名：HelloDynamic.java
 * 版本信息：
 * 日期：2018年6月11日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.jmx.example;

import java.lang.reflect.Constructor;
import java.util.Iterator;

import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.DynamicMBean;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanConstructorInfo;
import javax.management.MBeanException;
import javax.management.MBeanInfo;
import javax.management.MBeanNotificationInfo;
import javax.management.MBeanOperationInfo;
import javax.management.MBeanParameterInfo;
import javax.management.ReflectionException;

/**
 * HelloDynamic
 * 
 * https://blog.csdn.net/drykilllogic/article/details/38384045
 * 
 * https://blog.csdn.net/j43350860/article/details/5885474
 * 
 * @author：youngzil@163.com
 * @2018年6月11日 下午8:30:53
 * @since 1.0
 */
public class HelloDynamic implements DynamicMBean {

    // attributes
    private String name;
    private MBeanInfo mBeanInfo = null;
    private String className;
    private String description;
    private MBeanAttributeInfo[] attributes;
    private MBeanConstructorInfo[] constructors;
    private MBeanOperationInfo[] operations;
    MBeanNotificationInfo[] mBeanNotificationInfoArray;

    public HelloDynamic() {
        init();
        buildDynamicMBean();
    }

    private void init() {
        className = this.getClass().getName();
        description = "Simple implementation of a MBean.";

        // initial attributes
        attributes = new MBeanAttributeInfo[1];
        // initial constructors
        constructors = new MBeanConstructorInfo[1];
        // initial method
        operations = new MBeanOperationInfo[1];

        mBeanNotificationInfoArray = new MBeanNotificationInfo[0];
    }

    private void buildDynamicMBean() {
        // create constructor
        Constructor[] thisconstructors = this.getClass().getConstructors();
        constructors[0] = new MBeanConstructorInfo("HelloDynamic(): Constructs a HelloDynamic object", thisconstructors[0]);

        // create attribute
        attributes[0] = new MBeanAttributeInfo("Name", "java.lang.String", "Name: name string.", true, true, false);

        // create operate method
        MBeanParameterInfo[] params = null;// no parameter
        operations[0] = new MBeanOperationInfo("print", "print(): print the name", params, "void", MBeanOperationInfo.INFO);

        // create mbean
        mBeanInfo = new MBeanInfo(className, description, attributes, constructors, operations, mBeanNotificationInfoArray);
    }

    // dynamically add a print1 method
    private void dynamicAddOperation() {
        init();
        operations = new MBeanOperationInfo[2];
        buildDynamicMBean();
        operations[1] = new MBeanOperationInfo("print1", "print1(): print the name", null, "void", MBeanOperationInfo.INFO);
        mBeanInfo = new MBeanInfo(className, description, attributes, constructors, operations, mBeanNotificationInfoArray);
    }

    @Override
    public Object getAttribute(String attributeName) {
        if (attributeName == null) {
            return null;
        }

        if (attributeName.equals("Name")) {
            return name;
        }

        return null;
    }

    @Override
    public void setAttribute(Attribute attribute) {
        if (attribute == null) {
            return;
        }

        String name = attribute.getName();
        Object value = attribute.getValue();
        try {
            if (name.equals("Name")) {
                // if null value, try and if the setter returns any exception
                if (value == null) {
                    name = null;
                    // if non null value, make sure it is assignable to the attribute
                } else if ((Class.forName("java.lang.String")).isAssignableFrom(value.getClass())) {
                    name = (String) value;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public AttributeList getAttributes(String[] attributeNames) {
        if (attributeNames == null) {
            return null;
        }

        AttributeList resultList = new AttributeList();
        // if attributeNames is empty, return anempty result list
        if (attributeNames.length == 0) {
            return resultList;
        }

        for (int i = 0; i < attributeNames.length; i++) {
            try {
                Object value = getAttribute(attributeNames[i]);
                resultList.add(new Attribute(attributeNames[i], value));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return resultList;
    }

    @Override
    public AttributeList setAttributes(AttributeList attributes) {
        if (attributes == null) {
            return null;
        }

        AttributeList resultList = new AttributeList();
        // if attributeNames is empty, nothing more to do
        if (attributes.isEmpty()) {
            return resultList;
        }

        // for each attribute, try to set it and add to the result list if successfull
        for (Iterator i = attributes.iterator(); i.hasNext();) {
            Attribute attr = (Attribute) i.next();
            try {
                setAttribute(attr);
                String name = attr.getName();
                Object value = getAttribute(name);
                resultList.add(new Attribute(name, value));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return resultList;
    }

    @Override
    public Object invoke(String operationName, Object[] params, String[] signature) throws MBeanException, ReflectionException {
        // Check for a recognized operationname and call the corresponding operation
        if (operationName.equals("print")) {
            System.out.println("Hello, " + name + ", this is HelloDynamic!");

            // dynamic add a method
            dynamicAddOperation();
            return null;
        } else if (operationName.equals("print1")) {
            System.out.println("dynamically add a print1 method");
            return null;
        } else {
            // unrecognized operation name:
            throw new ReflectionException(new NoSuchMethodException(operationName), "Cannot find the operation " + operationName + " in " + className);
        }

    }

    public MBeanInfo getMBeanInfo() {
        return mBeanInfo;
    }
}
