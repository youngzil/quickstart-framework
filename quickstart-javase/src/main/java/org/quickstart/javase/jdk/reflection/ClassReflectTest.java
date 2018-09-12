/**
 * 项目名称：quickstart-javase 
 * 文件名：ClassReflectTest.java
 * 版本信息：
 * 日期：2018年8月24日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.jdk.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ClassReflectTest
 * 
 * @author：yangzl@asiainfo.com
 * @2018年8月24日 上午10:51:09
 * @since 1.0
 */
public class ClassReflectTest {

    public static void main(String[] args) {
        List<Field> fieldList = new ArrayList<>();
        Class tempClass = EsConMessage.class;
        while (tempClass != null) {// 当父类为null的时候说明到达了最上层的父类(Object类).

            System.out.println("tempClass.getName()=" + tempClass.getName());

            for (Field field : tempClass.getDeclaredFields()) {
                System.out.println(Modifier.toString(field.getModifiers()) +"--->" +field.getName() + "--->" + field.getType().getName());
            }

            fieldList.addAll(Arrays.asList(tempClass.getDeclaredFields()));
            tempClass = tempClass.getSuperclass(); // 得到父类,然后赋给自己
        }

        for (Field f : fieldList) {
            // System.out.println("getAllFields,getFields---" + f.getName());
        }

    }

}
