/**
 * 项目名称：quickstart-reflect 
 * 文件名：InvokersTest.java
 * 版本信息：
 * 日期：2018年11月12日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.reflect.invokers;

import java.lang.reflect.Method;
import java.util.Date;

import org.junit.Test;

import net.sf.cglib.reflect.FastClass;
import net.sf.cglib.reflect.FastMethod;

/**
 * InvokersTest 
 *  
 * @author：yangzl@asiainfo.com
 * @2018年11月12日 下午10:43:43 
 * @since 1.0
 */
public class InvokersTest {
    
    public static interface Getter {  
        Object get(Object obj);  
    }  
      
    public static interface Setter {  
        void set(Object obj, Object value);  
    }  
      
    @Test  
    public void testInvoke() {  
        try {  
            // 创建getter调用器，用于调用getTime方法  
            Getter getter = Invokers.newInvoker(Getter.class, Date.class,  
                    "getTime", null, Long.TYPE);  
            // 创建setter调用器，用于调用setTime方法  
            Setter setter = Invokers.newInvoker(Setter.class, Date.class,  
                    "setTime", new Class<?>[] { Long.TYPE }, null);  
            Date date = new Date();  
            System.out.println("time=" + getter.get(date));  
            setter.set(date, 33333333L);  
            System.out.println("time1=" + getter.get(date));  
            Method getTime = Date.class.getMethod("getTime");  
            Method setTime = Date.class.getMethod("setTime", Long.TYPE);  
            getTime.setAccessible(true);  
            setTime.setAccessible(true);  
            FastClass fastClass = FastClass.create(Date.class);  
            FastMethod fastGetTime = fastClass.getMethod(getTime);  
            FastMethod fastSetTime = fastClass.getMethod(setTime);  
            System.out.println("time2=" + getTime.invoke(date));  
            long t = System.currentTimeMillis();  
            for (int i = 0; i < 100000000; i++) {  
                date.setTime(33333333L);  
                date.getTime();  
            }  
            long t1 = System.currentTimeMillis();  
            System.out.println("直接调用耗时：" + (t1 - t) + "ms");  
            t1 = System.currentTimeMillis();  
            for (int i = 0; i < 100000000; i++) {  
                setter.set(date, 33333333L);  
                getter.get(date);  
            }  
            long t2 = System.currentTimeMillis();  
            System.out.println("Invokers调用耗时：" + (t2 - t1) + "ms");  
            t2 = System.currentTimeMillis();  
            for (int i = 0; i < 100000000; i++) {  
                setTime.invoke(date, 6666666L);  
                getTime.invoke(date);  
            }  
            long t3 = System.currentTimeMillis();  
            System.out.println("JDK反射调用耗时：" + (t3 - t2) + "ms");  
            t3 = System.currentTimeMillis();  
            for (int i = 0; i < 100000000; i++) {  
                fastSetTime.invoke(date, new Object[] { 6666666L });  
                fastGetTime.invoke(date, new Object[] {});  
            }  
            long t4 = System.currentTimeMillis();  
            System.out.println("FastMethod调用耗时：" + (t4 - t3) + "ms");  
        } catch (Throwable e) {  
            e.printStackTrace();  
        }  
    }  
    
    
    @Test  
    public void testInvoker() {  
        try {  
            Date date = new Date();  
            Method getMethod = Date.class.getMethod("getTime");  
            getMethod.setAccessible(true);  
            Method setMethod = Date.class.getMethod("setTime", Long.TYPE);  
            setMethod.setAccessible(true);  
            Invokers.Invoker get = Invokers.newInvoker(getMethod);  
            Invokers.Invoker set = Invokers.newInvoker(setMethod);  
            FastClass fastClass = FastClass.create(Date.class);  
            FastMethod fastGetMethod = fastClass.getMethod(getMethod);  
            FastMethod fastSetMethod = fastClass.getMethod(setMethod);  
      
            System.out.println(get.invoke(date, new Object[] {}));  
            set.invoke(date, new Object[] { 333333L });  
            System.out.println(get.invoke(date, new Object[] {}));  
            long t0 = System.currentTimeMillis();  
            for (int i = 0; i < 100000000; i++) {  
                get.invoke(date, new Object[] {});  
                set.invoke(date, new Object[] { 333333L });  
            }  
            long t1 = System.currentTimeMillis();  
            System.out.println("Invoker调用耗时：" + (t1 - t0) + "ms");  
            t1 = System.currentTimeMillis();  
            for (int i = 0; i < 100000000; i++) {  
                getMethod.invoke(date, new Object[] {});  
                setMethod.invoke(date, new Object[] { 333333L });  
            }  
            long t2 = System.currentTimeMillis();  
            System.out.println("JDK反射调用耗时：" + (t2 - t1) + "ms");  
            t2 = System.currentTimeMillis();  
            for (int i = 0; i < 100000000; i++) {  
                fastGetMethod.invoke(date, new Object[] {});  
                fastSetMethod.invoke(date, new Object[] { 333333L });  
            }  
            long t3 = System.currentTimeMillis();  
            System.out.println("CGLIB反射调用耗时：" + (t3 - t2) + "ms");  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
      
    }  

}
