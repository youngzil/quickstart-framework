package org.quickstart.kotlin;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2020/4/11 12:36
 */
public class GroovyJavaTest {

    public static void execute(String script) throws IllegalAccessException, InstantiationException {
        GroovyClassLoader groovyClassLoader = new GroovyClassLoader(GroovyTest.class.getClassLoader());
        Class scriptClass = groovyClassLoader.parseClass(script);
        GroovyObject go = (GroovyObject)scriptClass.newInstance();

        Map aMap = new HashMap();
        Map bMap = new HashMap();
        aMap.put("User",bMap);
        bMap.put("Name","wusir");

        System.out.println("Before groovy:"+aMap.toString());

        Object ret = go.invokeMethod("execute",new Object[]{aMap});
        System.out.println("Return from groovy: "+ret);

        System.out.println("After groovy:"+aMap.toString());
    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        String script2 = "def execute(map){" +
            "println 'hello '+ map['User']['Name'];" +
            "map['User']['Dad'] = 'groovy';" +
            "return 'Groovy';" +
            "}";

        String script = "def execute(map) {\n"
            + "        map.yangzl = \"sssss\";\n"
            + "    }";
        execute(script);


    }
}
