/**
 * 项目名称：quickstart-json 
 * 文件名：JsonlibTest.java
 * 版本信息：
 * 日期：2017年12月13日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.json.jsonlib;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.quickstart.json.jsonlib.entity.Birthday;
import org.quickstart.json.jsonlib.entity.Student;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONFunction;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
import net.sf.json.util.PropertyFilter;
import net.sf.json.xml.XMLSerializer;

/**
 * http://blog.csdn.net/tanga842428/article/details/54864485 <b>function:</b> 用json-lib转换java对象到JSON字符串 读取json字符串到java对象，序列化jsonObject到xml json-lib-version: json-lib-2.3-jdk15.jar 依赖包:
 * commons-beanutils.jar commons-collections-3.2.jar ezmorph-1.0.3.jar commons-lang.jar commons-logging.jar JsonlibTest
 * 
 * @author：youngzil@163.com
 * @2017年12月13日 下午8:26:17
 * @since 1.0
 */
public class JsonlibTest {

    private JSONArray jsonArray = null;
    private JSONObject jsonObject = null;

    private Student bean = null;

    /*=========================Java Object >>>> JSON String ===========================*/
    /**
     * <b>function:</b>转Java Bean对象到JSON
     * 
     * @author hoojo
     * @createDate Nov 28, 2010 2:35:54 PM
     */
    @Test
    public void writeEntity2JSON() {

        // 除了JSONArray、JSONObject可以将Java对象转换成JSON或是相反，将JSON字符串转换成Java对象，还有一个对象也可以完成上面的功能，它就是JSONSerializer；
        // fromObject将Java对象转换成json字符串，toBean将json对象转换成Java对象；
        fail("==============Java Bean >>> JSON Object==================");
        fail(JSONObject.fromObject(bean).toString());
        fail("==============Java Bean >>> JSON Array==================");
        fail(JSONArray.fromObject(bean).toString());// array会在最外层套上[]
        fail("==============Java Bean >>> JSON Object ==================");
        fail(JSONSerializer.toJSON(bean).toString());

        // 方法值得注意的是使用了JsonConfig这个对象，这个对象可以在序列化的时候对JavaObject的数据进行处理、过滤等
        // 上面的jsonConfig的registerJsonValueProcessor方法可以完成对象值的处理和修改，比如处理生日为null时，给一个特定的值。同样setJsonPropertyFilter和setJavaPropertyFilter都是完成对转换后的值的处理。

        fail("========================JsonConfig========================");
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Birthday.class, new JsonValueProcessor() {
            public Object processArrayValue(Object value, JsonConfig jsonConfig) {
                if (value == null) {
                    return new Date();
                }
                return value;
            }

            public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
                fail("key:" + key);
                return value + "##修改过的日期";
            }

        });
        jsonObject = JSONObject.fromObject(bean, jsonConfig);

        fail(jsonObject.toString());
        Student student = (Student) JSONObject.toBean(jsonObject, Student.class);
        fail(jsonObject.getString("birthday"));
        fail(student.toString());

        fail("#####################JsonPropertyFilter############################");
        jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
            public boolean apply(Object source, String name, Object value) {
                fail(source + "%%%" + name + "--" + value);
                // 忽略birthday属性
                if (value != null && Birthday.class.isAssignableFrom(value.getClass())) {
                    return true;
                }
                return false;
            }
        });
        fail(JSONObject.fromObject(bean, jsonConfig).toString());
        fail("#################JavaPropertyFilter##################");
        jsonConfig.setRootClass(Student.class);
        jsonConfig.setJavaPropertyFilter(new PropertyFilter() {
            public boolean apply(Object source, String name, Object value) {
                fail(name + "@" + value + "#" + source);
                if ("id".equals(name) || "email".equals(name)) {
                    value = name + "@@";
                    return true;
                }
                return false;
            }
        });
        // jsonObject = JSONObject.fromObject(bean, jsonConfig);
        // student = (Student) JSONObject.toBean(jsonObject, Student.class);
        // fail(student.toString());
        student = (Student) JSONObject.toBean(jsonObject, jsonConfig);
        fail("Student:" + student.toString());
    }

    /**
     * <b>function:</b>转换Java List集合到JSON
     * 
     * @author hoojo
     * @createDate Nov 28, 2010 2:36:15 PM
     */
    @Test
    public void writeList2JSON() {
        // 如果你是转换List集合，一定得用JSONArray或是JSONSrializer提供的序列化方法。如果你用JSONObject.fromObject方法转换List会出现异常，通常使用JSONSrializer这个JSON序列化的方法，它会自动识别你传递的对象的类型，然后转换成相应的JSON字符串。
        fail("==============Java List >>> JSON Array==================");
        List<Student> stu = new ArrayList<Student>();
        stu.add(bean);
        bean.setName("jack");
        stu.add(bean);
        fail(JSONArray.fromObject(stu).toString());
        fail(JSONSerializer.toJSON(stu).toString());
    }

    /**
     * <b>function:</b>转Java Map对象到JSON
     * 
     * @author hoojo
     * @createDate Nov 28, 2010 2:37:35 PM
     */
    @Test
    public void writeMap2JSON() {
        // 上面的Map集合有JavaBean、String、Boolean、Integer、以及Array和js的function函数的字符串。
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("A", bean);

        bean.setName("jack");
        map.put("B", bean);
        map.put("name", "json");
        map.put("bool", Boolean.TRUE);
        map.put("int", new Integer(1));
        map.put("arr", new String[] {"a", "b"});
        map.put("func", "function(i){ return this.arr[i]; }");
        fail("==============Java Map >>> JSON Object==================");
        fail(JSONObject.fromObject(map).toString());
        fail("==============Java Map >>> JSON Array ==================");
        fail(JSONArray.fromObject(map).toString());
        fail("==============Java Map >>> JSON Object==================");
        fail(JSONSerializer.toJSON(map).toString());
    }

    /**
     * <b>function:</b> 转换更多数组类型到JSON
     * 
     * @author hoojo
     * @createDate Nov 28, 2010 2:39:19 PM
     */
    @Test
    public void writeObject2JSON() {
        String[] sa = {"a", "b", "c"};
        fail("==============Java StringArray >>> JSON Array ==================");
        fail(JSONArray.fromObject(sa).toString());
        fail(JSONSerializer.toJSON(sa).toString());
        fail("==============Java boolean Array >>> JSON Array ==================");
        boolean[] bo = {true, false, true};
        fail(JSONArray.fromObject(bo).toString());
        fail(JSONSerializer.toJSON(bo).toString());
        Object[] o = {1, "a", true, 'A', sa, bo};
        fail("==============Java Object Array >>> JSON Array ==================");
        fail(JSONArray.fromObject(o).toString());
        fail(JSONSerializer.toJSON(o).toString());
        fail("==============Java String >>> JSON ==================");
        fail(JSONArray.fromObject("['json','is','easy']").toString());
        fail(JSONObject.fromObject("{'json':'is easy'}").toString());
        fail(JSONSerializer.toJSON("['json','is','easy']").toString());
        fail("==============Java JSONObject >>> JSON ==================");
        jsonObject = new JSONObject().element("string", "JSON").element("integer", "1").element("double", "2.0").element("boolean", "true");
        fail(JSONSerializer.toJSON(jsonObject).toString());

        fail("==============Java JSONArray >>> JSON ==================");
        jsonArray = new JSONArray().element("JSON").element("1").element("2.0").element("true");
        fail(JSONSerializer.toJSON(jsonArray).toString());

        fail("==============Java JSONArray JsonConfig#setArrayMode >>> JSON ==================");
        List input = new ArrayList();
        input.add("JSON");
        input.add("1");
        input.add("2.0");
        input.add("true");
        JSONArray jsonArray = (JSONArray) JSONSerializer.toJSON(input);
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setArrayMode(JsonConfig.MODE_OBJECT_ARRAY);
        Object[] output = (Object[]) JSONSerializer.toJava(jsonArray, jsonConfig);
        System.out.println(output[0]);

        // JSONFunction的对象，可以转换JavaScript的function。可以获取方法参数和方法体。同时，还可以用JSONObject、JSONArray构建Java对象，完成Java对象到JSON字符串的转换。
        fail("==============Java JSONFunction >>> JSON ==================");
        String str = "{'func': function( param ){ doSomethingWithParam(param); }}";
        JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON(str);
        JSONFunction func = (JSONFunction) jsonObject.get("func");
        fail(func.getParams()[0]);
        fail(func.getText());
    }

    /**
     * <b>function:</b>将json字符串转化为java对象
     * 
     * @author hoojo
     * @createDate Nov 28, 2010 3:01:16 PM
     */
    @Test
    public void readJSON2Bean() {
        String json = "{\"address\":\"chian\",\"birthday\":{\"birthday\":\"2010-11-22\"}," + "\"email\":\"email@123.com\",\"id\":22,\"name\":\"tom\"}";
        fail("==============JSON Object String >>> Java Bean ==================");
        jsonObject = JSONObject.fromObject(json);
        Student stu = (Student) JSONObject.toBean(jsonObject, Student.class);
        fail(stu.toString());
    }

    @Test
    public void readJSON2DynaBean() {
        // 转换后的对象Object是一个MorphDynaBean的动态JavaBean，通过PropertyUtils可以获得指定的属性的值。
        try {
            String json = "{\"address\":\"chian\",\"birthday\":{\"birthday\":\"2010-11-22\"}," + "\"email\":\"email@123.com\",\"id\":22,\"name\":\"tom\"}";
            fail("==============JSON Object String >>> Java MorphDynaBean ==================");
            JSON jo = JSONSerializer.toJSON(json);
            Object o = JSONSerializer.toJava(jo);// MorphDynaBean
            fail(PropertyUtils.getProperty(o, "address").toString());
            jsonObject = JSONObject.fromObject(json);
            fail(jsonObject.getString("email"));
            o = JSONSerializer.toJava(jsonObject);// MorphDynaBean
            fail(PropertyUtils.getProperty(o, "name").toString());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void readJSON2Array() {
        try {
            String json = "{\"address\":\"chian\",\"birthday\":{\"birthday\":\"2010-11-22\"}," + "\"email\":\"email@123.com\",\"id\":22,\"name\":\"tom\"}";
            fail("==============JSON Arry String >>> Java Array ==================");
            json = "[" + json + "]";
            jsonArray = JSONArray.fromObject(json);
            fail("#%%%" + jsonArray.get(0).toString());
            Object[] os = jsonArray.toArray();
            System.out.println(os.length);

            fail(JSONArray.fromObject(json).join(""));
            fail(os[0].toString());
            Student[] stus = (Student[]) JSONArray.toArray(jsonArray, Student.class);
            System.out.println(stus.length);
            System.out.println(stus[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void readJSON2List() {
        try {
            String json = "{\"address\":\"chian\",\"birthday\":{\"birthday\":\"2010-11-22\"}," + "\"email\":\"email@123.com\",\"id\":22,\"name\":\"tom\"}";
            fail("==============JSON Arry String >>> Java List ==================");
            json = "[" + json + "]";
            jsonArray = JSONArray.fromObject(json);
            List<Student> list = JSONArray.toList(jsonArray, Student.class);
            System.out.println(list.size());
            System.out.println(list.get(0));

            list = JSONArray.toList(jsonArray);
            System.out.println(list.size());
            System.out.println(list.get(0));// MorphDynaBean
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void readJSON2Collection() {
        // 刚才上面的将json转换成list提示该方法过时，这里有toCollection，可以用此方法代替toList方法；运行后结果如下：
        String json = "{\"address\":\"chian\",\"birthday\":{\"birthday\":\"2010-11-22\"}," + "\"email\":\"email@123.com\",\"id\":22,\"name\":\"tom\"}";
        try {
            fail("==============JSON Arry String >>> Java Collection ==================");
            json = "[" + json + "]";
            jsonArray = JSONArray.fromObject(json);
            Collection<Student> con = JSONArray.toCollection(jsonArray, Student.class);
            System.out.println(con.size());
            Object[] stt = con.toArray();
            System.out.println(stt.length);
            fail(stt[0].toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void readJSON2Map() {
        try {
            fail("==============JSON Arry String >>> Java Map ==================");
            String json = "{\"arr\":[\"a\",\"b\"],\"A\":{\"address\":\"address\",\"birthday\":{\"birthday\":\"2010-11-22\"}," + "\"email\":\"email\",\"id\":1,\"name\":\"jack\"},\"int\":1,"
                    + "\"B\":{\"address\":\"address\",\"birthday\":{\"birthday\":\"2010-11-22\"}," + "\"email\":\"email\",\"id\":1,\"name\":\"jack\"},\"name\":\"json\",\"bool\":true}";
            jsonObject = JSONObject.fromObject(json);
            Map<String, Class<?>> clazzMap = new HashMap<String, Class<?>>();
            clazzMap.put("arr", String[].class);
            clazzMap.put("A", Student.class);
            clazzMap.put("B", Student.class);
            Map<String, ?> mapBean = (Map) JSONObject.toBean(jsonObject, Map.class, clazzMap);
            System.out.println(mapBean);

            Set<String> set = mapBean.keySet();
            Iterator<String> iter = set.iterator();
            while (iter.hasNext()) {
                String key = iter.next();
                fail(key + ":" + mapBean.get(key).toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*============================Java Object >>>>> XML ==========================*/
    /**
     * <b>function:</b> 转换Java对象到XML 需要额外的jar包：xom.jar
     * 
     * @author hoojo
     * @createDate Nov 28, 2010 2:39:55 PM
     */
    @Test
    public void writeObject2XML() {
        // 主要运用的是XMLSerializer的write方法，这个方法可以完成java对象到xml的转换，不过你很容易就可以看到这个xml序列化对象，需要先将java对象转成json对象，然后再将json转换吃xml文档。
        XMLSerializer xmlSerializer = new XMLSerializer();
        fail("==============Java String Array >>> XML ==================");
        // xmlSerializer.setElementName("bean");
        fail(xmlSerializer.write(JSONArray.fromObject(bean)));
        String[] sa = {"a", "b", "c"};
        fail("==============Java String Array >>> XML ==================");
        fail(xmlSerializer.write(JSONArray.fromObject(sa)));
        fail("==============Java boolean Array >>> XML ==================");
        boolean[] bo = {true, false, true};
        fail(xmlSerializer.write(JSONArray.fromObject(bo)));
        fail(xmlSerializer.write(JSONSerializer.toJSON(bo)));
        Object[] o = {1, "a", true, 'A', sa, bo};
        fail("==============Java Object Array >>> JSON Array ==================");
        fail(xmlSerializer.write(JSONArray.fromObject(o)));
        fail(xmlSerializer.write(JSONSerializer.toJSON(o)));
        fail("==============Java String >>> JSON ==================");
        fail(xmlSerializer.write(JSONArray.fromObject("['json','is','easy']")).toString());
        fail(xmlSerializer.write(JSONObject.fromObject("{'json':'is easy'}")).toString());
        fail(xmlSerializer.write(JSONSerializer.toJSON("['json','is','easy']")).toString());
        // 上面的节点名称有点乱，你可以通过setElementName设置节点名称
    }

    /*============================XML String >>>>> Java Object ==========================*/
    /**
     * <b>function:</b>转换xml文档到java对象
     * 
     * @author hoojo
     * @createDate Nov 28, 2010 3:00:27 PM
     */
    @Test
    public void readXML2Object() {
        // 主要运用到XMLSerializer的read方法，将xml内容读取后，转换成Java对象。
        XMLSerializer xmlSerializer = new XMLSerializer();
        fail("============== XML >>>> Java String Array ==================");
        String[] sa = {"a", "b", "c"};
        jsonArray = (JSONArray) xmlSerializer.read(xmlSerializer.write(JSONArray.fromObject(sa)));
        fail(jsonArray.toString());

        String[] s = (String[]) JSONArray.toArray(jsonArray, String.class);
        fail(s[0].toString());

        fail("============== XML >>>> Java boolean Array ==================");
        boolean[] bo = {true, false, true};
        jsonArray = (JSONArray) xmlSerializer.read(xmlSerializer.write(JSONArray.fromObject(bo)));
        bo = (boolean[]) JSONArray.toArray(jsonArray, boolean.class);
        fail(bo.toString());
        System.out.println(bo[0]);

        jsonArray = (JSONArray) xmlSerializer.read(xmlSerializer.write(JSONSerializer.toJSON(bo)));
        bo = (boolean[]) JSONArray.toArray(jsonArray, boolean.class);
        fail(bo.toString());
        System.out.println(bo[0]);

        fail("==============Java Object Array >>> JSON Array ==================");
        Object[] o = {1, "a", true, 'A', sa, bo};
        jsonArray = (JSONArray) xmlSerializer.read(xmlSerializer.write(JSONArray.fromObject(o)));
        System.out.println(jsonArray.getInt(0));
        System.out.println(jsonArray.get(1));
        System.out.println(jsonArray.getBoolean(2));
        jsonArray = (JSONArray) xmlSerializer.read(xmlSerializer.write(JSONSerializer.toJSON(o)));
        System.out.println(jsonArray.get(4));
        System.out.println(jsonArray.getJSONArray(5).get(0));
        System.out.println(jsonArray.get(5));

        fail("==============Java String >>> JSON ==================");
        jsonArray = (JSONArray) xmlSerializer.read(xmlSerializer.write(JSONArray.fromObject("['json','is','easy']")).toString());
        s = (String[]) JSONArray.toArray(jsonArray, String.class);
        fail(s[0].toString());
        jsonObject = (JSONObject) xmlSerializer.read(xmlSerializer.write(JSONObject.fromObject("{'json':'is easy'}")).toString());
        Object obj = JSONObject.toBean(jsonObject);
        System.out.println(obj);
        jsonArray = (JSONArray) xmlSerializer.read(xmlSerializer.write(JSONSerializer.toJSON("['json','is','easy']")).toString());
        s = (String[]) JSONArray.toArray(jsonArray, String.class);
        fail(s[1].toString());
    }

    /**
     * 将xml的字符串内容，转换成Java的Array对象 testReadXml2Array
     * 
     * @Description: void
     * @Exception
     * @author：youngzil@163.com
     * @2017年12月13日 下午8:47:02
     * @since 1.0
     */
    @Test
    public void testReadXml2Array() {
        // 一段xml字符串格式的文档，将其转换为JSONArray对象。
        String str = "<a class=\"array\">" + "<e type=\"function\" params=\"i,j\">" + "return matrix[i][j];" + "</e>" + "</a>";
        JSONArray json = (JSONArray) new XMLSerializer().read(str);
        fail(json.toString());
    }

    @Before
    public void init() {

        // JSONObject是将Java对象转换成一个json的Object形式，JSONArray是将一个Java对象转换成json的Array格式。
        jsonArray = new JSONArray();
        jsonObject = new JSONObject();

        bean = new Student();
        bean.setAddress("address");
        bean.setEmail("email");
        bean.setId(1);
        bean.setName("haha");
        Birthday day = new Birthday();
        day.setBirthday("2010-11-22");
        bean.setBirthday(day);
    }

    @After
    public void destory() {
        jsonArray = null;
        jsonObject = null;
        bean = null;
        System.gc();
    }

    public final void fail(String string) {
        System.out.println(string);
    }

    public final void failRed(String string) {
        System.err.println(string);
    }

}
