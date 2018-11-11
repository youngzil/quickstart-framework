/**
 * 项目名称：quickstart-proxy 
 * 文件名：JavassistDemo.java
 * 版本信息：
 * 日期：2018年4月18日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.proxy.statics.javassist;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.NotFoundException;

/**
 * JavassistDemo
 * 
 * 动态构造Class，https://blog.csdn.net/top_code/article/details/51708043
 * 
 * @author：youngzil@163.com
 * @2018年4月18日 上午9:26:18
 * @since 1.0
 */
public class JavassistDemo {

    public static void main(String[] args) throws CannotCompileException, IOException, NotFoundException {

        ClassPool pool = ClassPool.getDefault();

        // 定义类，创建一个新的Class
        CtClass stuClass = pool.makeClass("org.quickstart.proxy.statics.javassist.Student");

        // 加载类，如果某个类已经存在，可以直接加载它，如下：
        // CtClass cc = pool.get("java.lang.String");

        // 构造类成员变量
        // id属性
        CtField idField = new CtField(CtClass.longType, "id", stuClass);
        stuClass.addField(idField);

        // name属性
        CtField nameField = new CtField(pool.get("java.lang.String"), "name", stuClass);
        stuClass.addField(nameField);

        // age属性
        CtField ageField = new CtField(CtClass.intType, "age", stuClass);
        stuClass.addField(ageField);

        // 构造类方法
        CtMethod getMethod = CtNewMethod.make("public int getAge() { return this.age;}", stuClass);
        CtMethod setMethod = CtNewMethod.make("public void setAge(int age) { this.age = age;}", stuClass);

        stuClass.addMethod(getMethod);
        stuClass.addMethod(setMethod);

        // stuClass.writeFile("F:\\Practice_Demo");

        Class<?> clazz = stuClass.toClass();
        System.out.println("class:" + clazz.getName());

        System.out.println("------------属性列表------------");
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getType() + "\t" + field.getName());
        }

        System.out.println("------------方法列表------------");
        // 方法
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method.getReturnType() + "\t" + method.getName() + "\t" + Arrays.toString(method.getParameterTypes()));
        }

    }

}
