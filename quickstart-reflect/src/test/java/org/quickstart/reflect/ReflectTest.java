/**
 * 项目名称：quickstart-reflect 
 * 文件名：ReflectTest.java
 * 版本信息：
 * 日期：2018年11月12日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.reflect;

import java.lang.reflect.Method;

import net.sf.cglib.reflect.FastClass;
import net.sf.cglib.reflect.FastMethod;

/**
 * ReflectTest 
 *  
 * @author：youngzil@163.com
 * @2018年11月12日 下午11:09:51 
 * @since 1.0
 */
public class ReflectTest {
    
    private static final int INT = 1;
    private static final String STRING = "name";
    private static final Object[] INTS = { 1 };
    private static final Object[] STRINGS = new String[] { STRING };
 
    private static final Bean BEAN = new Bean();
 
    private static final MethodBean METHOD = new MethodBean();
    private static final OptimizationMethodBean OPTIMIZATION_METHOD = new OptimizationMethodBean();
    private static final CglibMethod CGLIB_METHOD = new CglibMethod();
 
    private static final long LOOP = 1 * 10000 * 10000;
 
    // 测试main
    public static void main(String[] args) {
        // 直接调用
        test();
        // 反射调用
        testReflection();
        // 优化后反射调用
        testOptimizationReflection();
        // cglib反射调用
        testCglibReflection();
    }
 
    // 直接调用测试
    public static void test() {
        long start = System.currentTimeMillis();
        for (long i = 0; i < LOOP; i++) {
            BEAN.setId(INT);
            BEAN.setName(STRING);
        }
        long dur = System.currentTimeMillis() - start;
        System.out.println("直接调用测试:" + dur);
    }
 
    // 反射调用测试
    public static void testReflection() {
        long start = System.currentTimeMillis();
        for (long i = 0; i < LOOP; i++) {
            try {
                METHOD.setId.invoke(BEAN, INTS);
                METHOD.setName.invoke(BEAN, STRINGS);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        long dur = System.currentTimeMillis() - start;
        System.out.println("反射调用测试:" + dur);
    }
 
    // 优化后反射调用测试
    public static void testOptimizationReflection() {
        long start = System.currentTimeMillis();
        for (long i = 0; i < LOOP; i++) {
            try {
                OPTIMIZATION_METHOD.setId.invoke(BEAN, INTS);
                OPTIMIZATION_METHOD.setName.invoke(BEAN, STRINGS);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        long dur = System.currentTimeMillis() - start;
        System.out.println("优化后反射调用测试:" + dur);
    }
 
    // cglib反射调用测试
    public static void testCglibReflection() {
        long start = System.currentTimeMillis();
        for (long i = 0; i < LOOP; i++) {
            try {
                CGLIB_METHOD.cglibSetId.invoke(BEAN, INTS);
                CGLIB_METHOD.cglibSetName.invoke(BEAN, STRINGS);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        long dur = System.currentTimeMillis() - start;
        System.out.println("cglib反射调用测试:" + dur);
    }
 
    /**
     * 测试的bean, 简单的int String类型
     */
    public static class Bean {
        private int id;
        private String name;
 
        public int getId() {
            return id;
        }
        public void setId(int id) {
            this.id = id;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
 
    /**
     * 反射测试需要:Method bean
     */
    public static class MethodBean {
 
        public Method setId;
        public Method setName;
        {
            try {
                setId = Bean.class.getDeclaredMethod("setId", int.class);
                setName = Bean.class.getDeclaredMethod("setName", String.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
 
    /**
     * 反射测试需要:优化后的Method bean
     */
    public static class OptimizationMethodBean extends MethodBean {
        {
            /** 所谓的优化 */
            setId.setAccessible(true);
            setName.setAccessible(true);
        }
    }
 
    /**
     * 反射测试需要,使用cglib的fast method
     */
    public static class CglibMethod extends MethodBean {
        public FastMethod cglibSetId;
        public FastMethod cglibSetName;
        private FastClass cglibBeanClass = FastClass.create(Bean.class);
        {
            cglibSetId = cglibBeanClass.getMethod(setId);
            cglibSetName = cglibBeanClass.getMethod(setName);
        }
    }
 
}
