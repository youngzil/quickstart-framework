/**
 * 项目名称：quickstart-json 
 * 文件名：Test.java
 * 版本信息：
 * 日期：2017年12月13日
 * Copyright yangzl Corporation 2017
 * 版权所有 *
 */
package org.quickstart.json.jsonlib;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Assert;
import org.quickstart.json.jsonlib.entity.BeanA;
import org.quickstart.json.jsonlib.entity.MyBean;
import org.quickstart.json.jsonlib.entity.Person;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

/**
 * Test
 * 
 * @author：youngzil@163.com
 * @2017年12月13日 下午8:09:17
 * @since 1.0
 */
public class Test {
    public static void main(String[] args) {

        // arrays and collections集合到json
        boolean[] boolArray = new boolean[] {true, false, true};
        JSONArray jsonArray = JSONArray.fromObject(boolArray);
        System.out.println(jsonArray);

        List list = new ArrayList();
        list.add("first");
        list.add("second");
        JSONArray jsonArray2 = JSONArray.fromObject(list);
        System.out.println(jsonArray2);

        JSONArray jsonArray3 = JSONArray.fromObject("['json','is','easy']");
        System.out.println(jsonArray3);

        // From Beans & Maps to JSON
        Map map = new HashMap();
        map.put("name", "json");
        map.put("bool", Boolean.TRUE);
        map.put("int", new Integer(1));
        map.put("arr", new String[] {"a", "b"});
        map.put("func", "function(i){ return this.arr[i]; }");

        JSONObject jsonObject = JSONObject.fromObject(map);
        System.out.println(jsonObject);

        // 要想直接创建内部类的对象,不能按照想象的方式,去引用外部类的名字DotNew,而必须使用外部类的对象来创建内部类对象,就像上面的程序中所看到的那样.
        JSONObject jsonObject2 = JSONObject.fromObject(new MyBean());
        System.out.println(jsonObject2);

    }

    @org.junit.Test
    public void JSON2DynaBean() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {

        // From JSON to Beans
        String json = "{name=\"json\",bool:true,int:1,double:2.2,func:function(a){ return a; },array:[1,2]}";
        JSONObject jsonObject = JSONObject.fromObject(json);
        Object bean = JSONObject.toBean(jsonObject);
        assertEquals(jsonObject.get("name"), PropertyUtils.getProperty(bean, "name"));
        assertEquals(jsonObject.get("bool"), PropertyUtils.getProperty(bean, "bool"));
        assertEquals(jsonObject.get("int"), PropertyUtils.getProperty(bean, "int"));
        assertEquals(jsonObject.get("double"), PropertyUtils.getProperty(bean, "double"));
        assertEquals(jsonObject.get("func"), PropertyUtils.getProperty(bean, "func"));
        List expected = JSONArray.toList(jsonObject.getJSONArray("array"));
        Assert.assertEquals(expected, (List) PropertyUtils.getProperty(bean, "array"));

    }

    @org.junit.Test
    public void JSON2Bean() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {

        // From JSON to Beans
        String json = "{bool:true,integer:1,string:\"json\"}";
        JSONObject jsonObject = JSONObject.fromObject(json);
        BeanA bean = (BeanA) JSONObject.toBean(jsonObject, BeanA.class);
        assertEquals(jsonObject.get("bool"), Boolean.valueOf(bean.isBool()));
        assertEquals(jsonObject.get("integer"), new Integer(bean.getInteger()));
        assertEquals(jsonObject.get("string"), bean.getString());

    }

    @org.junit.Test
    public void JSON2Bean2() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {

        // From JSON to Beans
        String json = "{'data':[{'name':'Wallace'},{'name':'Grommit'}]}";
        Map classMap = new HashMap();
        classMap.put("data", Person.class);
        MyBean bean = (MyBean) JSONObject.toBean(JSONObject.fromObject(json), MyBean.class, classMap);

        System.out.println(bean.getData().size());
        for (int i = 0; i < bean.getData().size(); i++) {
            Person person = (Person) bean.getData().get(i);
            System.out.println("name=" + person.getName());
        }

        /* Morpher dynaMorpher = new BeanMorpher( Person.class, JSONUtils.getMorpherRegistry() );  
        morpherRegistry.registerMorpher( dynaMorpher );  
        List output = new ArrayList();  
        for( Iterator i = bean.getData().iterator(); i.hasNext(); ){  
           output.add( morpherRegistry.morph( Person.class, i.next() ) );  
        }  
        bean.setData( output );  */

    }

    @org.junit.Test
    public void JSON2XML() {

        JSONObject json = new JSONObject(true);
        String xml = new XMLSerializer().write(json);
        System.out.println(xml);

        JSONObject json2 = JSONObject.fromObject("{\"name\":\"json\",\"bool\":true,\"int\":1}");
        String xml2 = new XMLSerializer().write(json2);
        System.out.println(xml2);

        JSONArray json3 = JSONArray.fromObject("[1,2,3]");
        String xml3 = new XMLSerializer().write(json3);
        System.out.println(xml3);

    }

    @org.junit.Test
    public void XML2JSON() {

        String xml = "<a class=\"array\">  \n" + "  <e type=\"function\" params=\"i,j\">  \n" + "      return matrix[i][j];  \n" + "  </e>  \n" + "</a>  ";
        JSONArray json = (JSONArray) new XMLSerializer().read(xml);
        System.out.println(json);

    }

}
