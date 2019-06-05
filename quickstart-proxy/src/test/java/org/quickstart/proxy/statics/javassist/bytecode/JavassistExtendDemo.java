/**
 * 项目名称：quickstart-proxy 
 * 文件名：JavassistExtendDemo.java
 * 版本信息：
 * 日期：2018年4月18日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.proxy.statics.javassist.bytecode;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.NotFoundException;

/**
 * JavassistExtendDemo 
 * 
 *指定父类， https://blog.csdn.net/top_code/article/details/51708043
 *  
 * @author：youngzil@163.com
 * @2018年4月18日 上午9:33:41 
 * @since 1.0
 */
public class JavassistExtendDemo {
    
    public static void main(String[] args) throws CannotCompileException, IOException, NotFoundException {

        ClassPool pool = ClassPool.getDefault();

        //定义类
        CtClass stuClass = pool.makeClass("org.quickstart.proxy.statics.javassist.ChildStudent");

        //设置父类
        stuClass.setSuperclass(pool.get("Student"));

        //hobbies属性
        CtField ageField = new CtField(pool.getCtClass("java.util.List"), "hobbies", stuClass);
        stuClass.addField(ageField);

        Class<?> clazz = stuClass.toClass();
        System.out.println("class:"+clazz.getName());

        System.out.println("------------属性列表------------");
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getType()+"\t"+field.getName());
        }

        System.out.println("------------方法列表------------");
        //方法
        Method[] methods = clazz.getMethods();
        for (Method method: methods){
            System.out.println(method.getReturnType()+"\t"+method.getName()+"\t"+Arrays.toString(method.getParameterTypes()));
        }

    }

}
