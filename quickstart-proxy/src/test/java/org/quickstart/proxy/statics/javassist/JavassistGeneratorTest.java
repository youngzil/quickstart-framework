/**
 * 项目名称：quickstart-proxy 
 * 文件名：JavassistGeneratorTest.java
 * 版本信息：
 * 日期：2018年4月18日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.proxy.statics.javassist;


import java.lang.reflect.Method;  

import javassist.ClassPool;  
import javassist.CtClass;  
import javassist.CtConstructor;  
import javassist.CtField;  
import javassist.CtField.Initializer;  
import javassist.CtNewMethod;  
import javassist.Modifier;  

/**
 * JavassistGeneratorTest 
 *  
 * @author：yangzl@asiainfo.com
 * @2018年4月18日 上午9:17:01 
 * @since 1.0
 */
//http://blog.csdn.net/sadfishsc/article/details/9999169  
public class JavassistGeneratorTest {  

  public static void main(String[] args) throws Exception {  
        
      ClassPool pool = ClassPool.getDefault();  
      //创建类  
      CtClass cls = pool.makeClass("org.quickstart.proxy.statics.javassist.Student");  

      // 添加私有成员name及其getter、setter方法  
      CtField param = new CtField(pool.get("java.lang.String"), "name", cls);  
        
      param.setModifiers(Modifier.PRIVATE);  
        
      cls.addMethod(CtNewMethod.setter("setName", param));  
      cls.addMethod(CtNewMethod.getter("getName", param));  
        
      cls.addField(param, Initializer.constant(""));  

      // 添加无参的构造体  
      CtConstructor cons = new CtConstructor(new CtClass[] {}, cls);  
      cons.setBody("{name = \"Brant\";}");  
      cls.addConstructor(cons);  

      // 添加有参的构造体  
      cons = new CtConstructor(  
              new CtClass[] { pool.get("java.lang.String") }, cls);  
      cons.setBody("{$0.name = $1;}");  
      cls.addConstructor(cons);  

      // 打印创建类的类名  
      System.out.println(cls.toClass());  

      // 通过反射创建无参的实例，并调用getName方法  
      Object o = Class.forName("org.quickstart.proxy.statics.javassist.Student").newInstance();  
      Method getter = o.getClass().getMethod("getName");  
      System.out.println(getter.invoke(o));  

      // 调用其setName方法  
      Method setter = o.getClass().getMethod("setName",  
              new Class[] { String.class });  
      setter.invoke(o, "Adam");  
      System.out.println(getter.invoke(o));  

      // 通过反射创建有参的实例，并调用getName方法  
      o = Class.forName("org.quickstart.proxy.statics.javassist.Student").getConstructor(String.class).newInstance("Liu wjh");  
        
      getter = o.getClass().getMethod("getName");  
      System.out.println(getter.invoke(o));  
  }  

}  