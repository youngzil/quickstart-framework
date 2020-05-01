/**
 * 项目名称：quickstart-javase 
 * 文件名：CacheOperationTest.java
 * 版本信息：
 * 日期：2017年8月1日
 * Copyright yangzl Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.cache;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * CacheOperationTest
 * 
 * @author：youngzil@163.com
 * @2017年8月1日 下午10:35:47
 * @version 2.0
 */
public class CacheOperationTest {
    private static final Logger logger = LoggerFactory.getLogger(CacheOperationTest.class);
    private static CacheOperationTest singleton = null;

    private Hashtable cacheMap;// 存放缓存数据

    private ArrayList threadKeys;// 处于线程更新中的key值列表

    public static CacheOperationTest getInstance() {
        if (singleton == null) {
            singleton = new CacheOperationTest();
        }
        return singleton;
    }

    private CacheOperationTest() {
        cacheMap = new Hashtable();
        threadKeys = new ArrayList();
    }

    /**
     * 添加数据缓存 与方法getCacheData(String key, long intervalTime, int maxVisitCount)配合使用
     * 
     * @param key
     * @param data
     */
    public void addCacheData(String key, Object data) {
        addCacheData(key, data, true);
    }

    private void addCacheData(String key, Object data, boolean check) {
        if (Runtime.getRuntime().freeMemory() < 5L * 1024L * 1024L) {// 虚拟机内存小于10兆，则清除缓存
            logger.warn("WEB缓存：内存不足，开始清空缓存！");
            removeAllCacheData();
            return;
        } else if (check && cacheMap.containsKey(key)) {
            logger.warn("WEB缓存：key值= " + key + " 在缓存中重复, 本次不缓存！");
            return;
        }
        cacheMap.put(key, new CacheData(data));
    }

    /**
     * 取得缓存中的数据 与方法addCacheData(String key, Object data)配合使用
     * 
     * @param key
     * @param intervalTime 缓存的时间周期，小于等于0时不限制
     * @param maxVisitCount 访问累积次数，小于等于0时不限制
     * @return
     */
    public Object getCacheData(String key, long intervalTime, int maxVisitCount) {
        CacheData cacheData = (CacheData) cacheMap.get(key);
        if (cacheData == null) {
            return null;
        }
        if (intervalTime > 0 && (System.currentTimeMillis() - cacheData.getTime()) > intervalTime) {
            removeCacheData(key);
            return null;
        }
        if (maxVisitCount > 0 && (maxVisitCount - cacheData.getCount()) <= 0) {
            removeCacheData(key);
            return null;
        } else {
            cacheData.addCount();
        }
        return cacheData.getData();
    }

    /**
     * 当缓存中数据失效时，用不给定的方法线程更新数据
     * 
     * @param o 取得数据的对像(该方法是静态方法是不用实例，则传Class实列)
     * @param methodName 该对像中的方法
     * @param parameters 该方法的参数列表(参数列表中对像都要实现toString方法,若列表中某一参数为空则传它所属类的Class)
     * @param intervalTime 缓存的时间周期，小于等于0时不限制
     * @param maxVisitCount 访问累积次数，小于等于0时不限制
     * @return
     */
    public Object getCacheData(Object o, String methodName, Object[] parameters, long intervalTime, int maxVisitCount) {
        Class oc = o instanceof Class ? (Class) o : o.getClass();
        StringBuffer key = new StringBuffer(oc.getName());// 生成缓存key值
        key.append("-").append(methodName);
        if (parameters != null) {
            for (int i = 0; i < parameters.length; i++) {
                if (parameters[i] instanceof Object[]) {
                    key.append("-").append(Arrays.toString((Object[]) parameters[i]));
                } else {
                    key.append("-").append(parameters[i]);
                }
            }
        }

        CacheData cacheData = (CacheData) cacheMap.get(key.toString());
        if (cacheData == null) {// 等待加载并返回
            Object returnValue = invoke(o, methodName, parameters, key.toString());
            return returnValue instanceof Class ? null : returnValue;
        }
        if (intervalTime > 0 && (System.currentTimeMillis() - cacheData.getTime()) > intervalTime) {
            daemonInvoke(o, methodName, parameters, key.toString());// 缓存时间超时,启动线程更新数据
        } else if (maxVisitCount > 0 && (maxVisitCount - cacheData.getCount()) <= 0) {// 访问次数超出,启动线程更新数据
            daemonInvoke(o, methodName, parameters, key.toString());
        } else {
            cacheData.addCount();
        }
        return cacheData.getData();
    }

    /**
     * 递归调用给定方法更新缓存中数据据
     * 
     * @param o
     * @param methodName
     * @param parameters
     * @param key
     * @return 若反射调用方法返回值为空则返回该值的类型
     */
    private Object invoke(Object o, String methodName, Object[] parameters, String key) {
        Object returnValue = null;
        try {
            Class[] pcs = null;
            if (parameters != null) {
                pcs = new Class[parameters.length];
                for (int i = 0; i < parameters.length; i++) {
                    if (parameters[i] instanceof MethodInfo) {// 参数类型是MethodInfo则调用该方法的返回值做这参数
                        MethodInfo pmi = (MethodInfo) parameters[i];
                        Object pre = invoke(pmi.getO(), pmi.getMethodName(), pmi.getParameters(), null);
                        parameters[i] = pre;
                    }
                    if (parameters[i] instanceof Class) {
                        pcs[i] = (Class) parameters[i];
                        parameters[i] = null;
                    } else {
                        pcs[i] = parameters[i].getClass();
                    }
                }
            }
            Class oc = o instanceof Class ? (Class) o : o.getClass();
            // Method m = oc.getDeclaredMethod(methodName, pcs);
            Method m = matchMethod(oc, methodName, pcs);
            returnValue = m.invoke(o, parameters);
            if (key != null && returnValue != null) {
                addCacheData(key, returnValue, false);
            }
            if (returnValue == null) {
                returnValue = m.getReturnType();
            }
        } catch (Exception e) {
            logger.error("调用方法失败,methodName=" + methodName);
            if (key != null) {
                removeCacheData(key);
                logger.error("更新缓存失败，缓存key=" + key);
            }
            e.printStackTrace();
        }
        return returnValue;
    }

    /**
     * 找不到完全匹配的方法时,对参数进行向父类匹配 因为方法aa(java.util.List) 与 aa(java.util.ArrayList)不能自动匹配到
     * 
     * @param oc
     * @param methodName
     * @param pcs
     * @return
     * @throws NoSuchMethodException
     * @throws NoSuchMethodException
     */
    private Method matchMethod(Class oc, String methodName, Class[] pcs) throws NoSuchMethodException, SecurityException {
        try {
            Method method = oc.getDeclaredMethod(methodName, pcs);
            return method;
        } catch (NoSuchMethodException e) {
            Method[] ms = oc.getDeclaredMethods();
            aa: for (int i = 0; i < ms.length; i++) {
                if (ms[i].getName().equals(methodName)) {
                    Class[] pts = ms[i].getParameterTypes();
                    if (pts.length == pcs.length) {
                        for (int j = 0; j < pts.length; j++) {
                            if (!pts[j].isAssignableFrom(pcs[j])) {
                                break aa;
                            }
                        }
                        return ms[i];
                    }
                }
            }
            throw new NoSuchMethodException();
        }
    }

    /**
     * 新启线程后台调用给定方法更新缓存中数据据
     * 
     * @param o
     * @param methodName
     * @param parameters
     * @param key
     */
    private void daemonInvoke(Object o, String methodName, Object[] parameters, String key) {
        if (!threadKeys.contains(key)) {
            InvokeThread t = new InvokeThread(o, methodName, parameters, key);
            t.start();
        }
    }

    /**
     * 些类存放方法的主调对像,名称及参数数组
     * 
     * @author zsy
     * 
     */
    public class MethodInfo {
        private Object o;
        private String methodName;
        private Object[] parameters;

        public MethodInfo(Object o, String methodName, Object[] parameters) {
            this.o = o;
            this.methodName = methodName;
            this.parameters = parameters;
        }

        public String getMethodName() {
            return methodName;
        }

        public void setMethodName(String methodName) {
            this.methodName = methodName;
        }

        public Object getO() {
            return o;
        }

        public void setO(Object o) {
            this.o = o;
        }

        public Object[] getParameters() {
            return parameters;
        }

        public void setParameters(Object[] parameters) {
            this.parameters = parameters;
        }

        public String toString() {
            StringBuffer str = new StringBuffer(methodName);
            if (parameters != null) {
                str.append("(");
                for (int i = 0; i < parameters.length; i++) {
                    if (parameters[i] instanceof Object[]) {
                        str.append(Arrays.toString((Object[]) parameters[i])).append(",");
                    } else {
                        str.append(parameters[i]).append(",");
                    }
                }
                str.append(")");
            }
            return str.toString();
        }
    }

    /**
     * 线程调用方法
     * 
     * @author zsy
     * 
     */
    private class InvokeThread extends Thread {
        private Object o;
        private String methodName;
        private Object[] parameters;
        private String key;

        public InvokeThread(Object o, String methodName, Object[] parameters, String key) {
            this.o = o;
            this.methodName = methodName;
            this.parameters = parameters;
            this.key = key;
        }

        public void run() {
            threadKeys.add(key);
            invoke(o, methodName, parameters, key);
            threadKeys.remove(key);
        }
    }

    /**
     * 移除缓存中的数据
     * 
     * @param key
     */
    public void removeCacheData(String key) {
        cacheMap.remove(key);
    }

    /**
     * 移除所有缓存中的数据
     * 
     */
    public void removeAllCacheData() {
        cacheMap.clear();
    }

    public String toString() {
        StringBuffer sb = new StringBuffer("************************ ");
        sb.append("正在更新的缓存数据： ");
        for (int i = 0; i < threadKeys.size(); i++) {
            sb.append(threadKeys.get(i)).append(" ");
        }
        sb.append("当前缓存大小：").append(cacheMap.size()).append(" ");
        sb.append("************************");
        return sb.toString();
    }
}
