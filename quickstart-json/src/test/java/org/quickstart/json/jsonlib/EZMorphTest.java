/**
 * 项目名称：quickstart-json 
 * 文件名：EZMorphTest.java
 * 版本信息：
 * 日期：2017年12月13日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.json.jsonlib;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Test;
import org.quickstart.json.jsonlib.entity.Student;
import org.quickstart.json.jsonlib.entity.Teacher;

import net.sf.ezmorph.MorphUtils;
import net.sf.ezmorph.MorpherRegistry;
import net.sf.ezmorph.array.ObjectArrayMorpher;
import net.sf.ezmorph.bean.BeanMorpher;
import net.sf.ezmorph.object.BooleanObjectMorpher;
import net.sf.ezmorph.object.StringMorpher;
import net.sf.ezmorph.primitive.IntMorpher;

/**
 * EZMorphTest
 * 
 * @author：youngzil@163.com
 * @2017年12月13日 下午9:30:21
 * @since 1.0
 */
public class EZMorphTest {

    /*众所周知，在java中对象之间的赋值是地址引用关系，如：
    A a = new A();
    A b = a;
    则修改b的属性，a的属性也会跟着修改。
           在很多场合下，我们希望克隆出一个新的对象出来，新对象的修改不会影响原有对象，这时我们一般常用的有如下两种方式：
    使对象实现Cloneable接口，这个只适用于我们自己的java对象
    使用jakatar commons的BeanUtils实现bean copy
         昨晚看到一个新的组件:EZMorph，可以实现同样的功能，这里简单介绍一下。
          EZMorph据说起源于json，后来独立出来了，有兴趣的可以到官网（http://ezmorph.sourceforge.net/）上浏览一下。
    EZMorph的主要原理
        若要将A的属性赋给B，则经过如下步骤：
    new一个B的实例
    遍历A的属性
    若A有某个属性P1，B也有一个属性P1，则将A的P1的属性值赋给B的P1*/

    @org.junit.Test
    public void test1() {
        int i = new IntMorpher().morph("123");
        Assert.assertEquals(123, i); // true!
        String str = (String) StringMorpher.getInstance().morph(new Integer(123));
        Assert.assertEquals("123", str); // true!
        Boolean[] bools = (Boolean[]) new ObjectArrayMorpher(new BooleanObjectMorpher()).morph(new String[] {"true", "false"});
        Assert.assertEquals(Boolean.TRUE, bools[0]); // true!
        Assert.assertEquals(Boolean.FALSE, bools[1]); // true!
        MorpherRegistry morperRegistry = new MorpherRegistry();
        MorphUtils.registerStandardMorphers(morperRegistry);
        Integer x = (Integer) morperRegistry.morph(Integer.class, "2");
        Assert.assertEquals(new Integer(2), x); // true!
    }

    @Test
    public void test2() {
        // 注意： morperRegistry.registerMorpher( new BeanMorpher( GregorianCalendar.class, morperRegistry ) );
        // 这里必须填写GregorianCalendar类型，如果用Calendar.class就会报出异常，因为EZMorph只能处理public类型的构造方法，而Calendar的构造方法是protected的。这种情况下，最好还是使用Calendar的clone功能。
        MorpherRegistry morperRegistry = new MorpherRegistry();
        MorphUtils.registerStandardMorphers(morperRegistry);
        Calendar dynaBean = Calendar.getInstance(); // initialized elsewhere
        System.out.println(dynaBean.getTime());
        morperRegistry.registerMorpher(new BeanMorpher(GregorianCalendar.class, morperRegistry));
        Calendar myBean = (Calendar) morperRegistry.morph(GregorianCalendar.class, dynaBean);
    }

    @Test
    public void test3() {
        // 将Student对象的name属性赋给Teacher对象的name中
        // 可以看出Student和Teacher都有name属性，因此将属性值拷贝过来了，而Student没有id属性，因此Teacher对象的id属性值为空。
        MorpherRegistry morperRegistry = new MorpherRegistry();
        Student student = new Student();
        student.setName("chb");
        morperRegistry.registerMorpher(new BeanMorpher(Teacher.class, morperRegistry));
        Teacher teacher = (Teacher) morperRegistry.morph(Teacher.class, student);
        System.out.println(teacher.getId());
        System.out.println(teacher.getName());
    }

}
