/**
 * 项目名称：quickstart-javase 
 * 文件名：Evaluate.java
 * 版本信息：
 * 日期：2017年4月29日
 * Copyright yangzl Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase;

import java.beans.BeanInfo;
import java.beans.Expression;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * Evaluate 类说明：通过反射为bean赋值。
 * 
 * @author：youngzil@163.com
 * @2017年4月29日 下午8:00:40
 * @version 1.0
 */
public class Evaluate {
    /** * 参数说明：*className :要赋值的bean的type**po:要赋值的bean，如果po为空则创建一个**prop:属性名->值** @author 李华 * */
    public static Object evaluatePo(String className, Object po, Properties prop) {
        try {

            Class poClass = Class.forName(className);

            if (po == null) {
                po = poClass.newInstance();
            }
            // Introspector相当beans这个架构的一个入口。类似于Hibernate的SessionFactory// 通过bean的类型获得bean的描述—》获得属性描述的集合
            BeanInfo bi = Introspector.getBeanInfo(poClass);
            PropertyDescriptor[] pd = bi.getPropertyDescriptors();

            for (int i = 0; i < pd.length; i++) {
                if (prop.getProperty(pd[i].getName()) != null) {
                    Object value = getPropValue(pd[i].getPropertyType(), prop.getProperty(pd[i].getName()));

                    executeEvaluate(po, pd[i].getWriteMethod(), value);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return po;
    }

    // 这里是PropertyEditor 的使用，规避了if条件的使用。让代码的扩展性得到了很好的保证。这里使用的是依赖查找（DL）的方式。
    // 注册PropertyEditor 的步骤放在了bean容器当中
    static Object getPropValue(Class clazz, String value) throws InstantiationException, IllegalAccessException {
        PropertyEditor pe = PropertyEditorManager.findEditor(clazz);
        pe.setAsText(value);
        return pe.getValue();
    }

    // 调用赋值方法
    static void executeEvaluate(Object dest, Method m, Object value) throws Exception {
        Expression e = new Expression(dest, m.getName(), new Object[] {value});
        e.execute();
    }

}
