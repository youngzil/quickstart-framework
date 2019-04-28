/**
 * 项目名称：quickstart-reflect 
 * 文件名：GenericEssence.java
 * 版本信息：
 * 日期：2018年11月13日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.reflect.jdk;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * GenericEssence 
 *  
 * @author：youngzil@163.com
 * @2018年11月13日 上午10:25:09 
 * @since 1.0
 */
/**
 * 集合泛型的本质
 */
public class GenericEssence {
    public static void main(String[] args) {
        List list1 = new ArrayList(); // 没有泛型
        List<String> list2 = new ArrayList<String>(); // 有泛型

        /*
         * 1.首先观察正常添加元素方式，在编译器检查泛型，
         * 这个时候如果list2添加int类型会报错
         */
        list2.add("hello");
        // list2.add(20); // 报错！list2有泛型限制，只能添加String，添加int报错
        System.out.println("list2的长度是：" + list2.size()); // 此时list2长度为1

        /*
         * 2.然后通过反射添加元素方式，在运行期动态加载类，首先得到list1和list2
         * 的类类型相同，然后再通过方法反射绕过编译器来调用add方法，看能否插入int
         * 型的元素
         */
        Class c1 = list1.getClass();
        Class c2 = list2.getClass();
        System.out.println(c1 == c2); // 结果：true，说明类类型完全相同

        // 验证：我们可以通过方法的反射来给list2添加元素，这样可以绕过编译检查
        try {
            Method m = c2.getMethod("add", Object.class); // 通过方法反射得到add方法
            m.invoke(list2, 20); // 给list2添加一个int型的，上面显示在编译器是会报错的
            System.out.println("list2的长度是：" + list2.size()); // 结果：2，说明list2长度增加了，并没有泛型检查
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*
         * 综上可以看出，在编译器的时候，泛型会限制集合内元素类型保持一致，但是编译器结束进入
         * 运行期以后，泛型就不再起作用了，即使是不同类型的元素也可以插入集合。
         */
    }
}
