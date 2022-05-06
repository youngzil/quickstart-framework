package org.quickstart.apache.commons.text;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;
import org.apache.commons.lang3.text.StrSubstitutor;
import org.apache.commons.text.StringSubstitutor;
import org.junit.jupiter.api.Test;

import java.io.StringWriter;
import java.text.MessageFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringSubstitutorTest {

    // [java 替换字符串模板(模板渲染)](https://www.cnblogs.com/softidea/p/9140204.html)

    @Test
    public void testStringSubstitutor() {

        // 可以为变量设置默认值，格式为：${undefined.number:-1234567890}，其中 undefined.number是变量名，:-是分隔符，1234567890是默认值。
        Map valuesMap = new HashMap();
        valuesMap.put("animal", "quick brown fox");
        valuesMap.put("target", "lazy dog");
        String templateString = "The ${animal} jumped over the ${target}. ${undefined.number:-1234567890}.";
        StringSubstitutor sub = new StringSubstitutor(valuesMap);
        String resolvedString = sub.replace(templateString);
        System.out.println(resolvedString);

        // 短信模板
        String template = "${userName}您好，欢迎使用${system}，您的验证码是：${code}，若非本人操作，请忽略！";

        // 占位符对应的值
        Map<String, String> valueMap = new HashMap<>();
        valueMap.put("userName", "阿杰");
        valueMap.put("system", "查询系统");
        valueMap.put("code", "666666");

        StringSubstitutor sub0 = new StringSubstitutor(valueMap);
        String replace0 = sub0.replace(template);
        System.out.println(replace0); // 阿杰您好，欢迎使用查询系统，您的验证码是：666666，若非本人操作，请忽略！

        // 短信模板
        template = "#userName#您好，欢迎使用#system#，您的验证码是：#code#，若非本人操作，请忽略！";

        // 自定义模板占位符前后缀为#
        // 构造方法  StringSubstitutor(Map<String, V> valueMap, String prefix, String suffix)
        StringSubstitutor sub1 = new StringSubstitutor(valueMap, "#", "#");
        String replace1 = sub1.replace(template);
        System.out.println(replace1); // 阿杰您好，欢迎使用查询系统，您的验证码是：666666，若非本人操作，请忽略！

        // 使用静态方法传入自定义的占位符前后缀
        StringSubstitutor sub2 = new StringSubstitutor();
        String replace2 = sub2.replace(template, valueMap, "#", "#");
        System.out.println(replace2); // 阿杰您好，欢迎使用查询系统，您的验证码是：666666，若非本人操作，请忽略！

    }

    @Test
    public void testStrSubstitutor() {
        // StrSubstitutor在apache的commons-lang3包中

        // 1、直接替换系统属性值
        // Deprecated as of 3.6, use commons-text StringSubstitutor  instead
        StrSubstitutor.replaceSystemProperties("You are running with java.version = ${java.version} and os.name = ${os.name}.");


        // 2、使用Map替换字符串中的占位符

        Map valuesMap = new HashMap();
        valuesMap.put("animal", "quick brown fox");
        valuesMap.put("target", "lazy dog");
        String templateString = "The ${animal} jumped over the ${target}.";

        StrSubstitutor sub = new StrSubstitutor(valuesMap);
        String resolvedString = sub.replace(templateString);
        System.out.println(resolvedString);


        // 3、StrSubstitutor会递归地替换变量，比如：

        Map<String, Object> params = Maps.newHashMap();
        params.put("name", "${x}");
        params.put("x", "y");
        StrSubstitutor strSubstitutor = new StrSubstitutor(params);

        String hello2 = "${name}";
        System.out.println(strSubstitutor.replace(hello2));



        // 4、有时变量内还嵌套其它变量，这个StrSubstitutor也是支持的，不过要调用下setEnableSubstitutionInVariables才可以。

        params.put("jre-1.8", "java-version-1.8");
        params.put("java.specification.version", "1.8");
         strSubstitutor = new StrSubstitutor(params);

        strSubstitutor.setEnableSubstitutionInVariables(true);
        System.out.println(strSubstitutor.replace("${jre-${java.specification.version}}"));


    }

    @Test
    public void testStringFormat() {

        // 1、使用内置String.format

        String message = String.format("您好%s，晚上好！您目前余额：%.2f元，积分：%d", "张三", 10.155, 10);
        System.out.println(message);
        //您好张三，晚上好！您目前余额：10.16元，积分：10

    }

    @Test
    public void testMessageFormat() {

        // 2、使用内置MessageFormat

        String message = MessageFormat.format("您好{0}，晚上好！您目前余额：{1,number,#.##}元，积分：{2}", "张三", 10.155, 10);
        System.out.println(message);
        //您好张三，晚上好！您目前余额：10.16元，积分：10

        Object[] params = new Object[]{"hello", "!"};
        String msg = MessageFormat.format("{0} world {1}", params);
        System.out.println(msg);

    }

    @Test
    public void testPattern() {

        // 3、使用自定义封装

        Map map = new HashMap();
        map.put("name", "张三");
        map.put("money", String.format("%.2f", 10.155));
        map.put("point", 10);
        String message = processTemplate("您好${name}，晚上好！您目前余额：${money}元，积分：${point}", map);
        System.out.println(message);
        //您好张三，晚上好！您目前余额：10.16元，积分：10

    }

    //测试方法
    @Test
    public  void testPatternSimpleTemplate() {
        String tmpLine = "简历:\n 姓名: ${姓} ${名} \n 性别: ${性别}\n 年龄: ${年龄} \n";
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("姓", "wen");
        data.put("名", "66");
        data.put("性别", "man");
        data.put("年龄", "222");

        System.out.println(simpleTemplate(tmpLine, null, "--"));
    }

    @Test
    public  void testPattern2() {
         final String jsonStr = "{\"name\":\"11\",\"time\":\"2014-10-21\"}";
         final String template = "亲爱的用户${name},你好，上次登录时间为${time}";
        System.out.println(generateWelcome(jsonStr,template));
    }

    @Test
    public void testPattern3RenderString() {
        String content = "hello ${name}, 1 2 3 4 5 ${six} 7, again ${name}. ";
        Map<String, String> map = new HashMap<>();
        map.put("name", "java");
        map.put("six", "6");
        content = renderString(content, map);
        System.out.println(content);
    }

    @Test
    public void testFreemarker() {

        // 4、使用模板引擎freemarker

        try {
            Map map = new HashMap();
            map.put("name", "张三");
            map.put("money", 10.155);
            map.put("point", 10);
            Template template =
                new Template("strTpl", "您好${name}，晚上好！您目前余额：${money?string(\"#.##\")}元，积分：${point}", new Configuration(new Version("2.3.23")));
            StringWriter result = new StringWriter();
            template.process(map, result);
            System.out.println(result.toString());
            //您好张三，晚上好！您目前余额：10.16元，积分：10
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static String processTemplate(String template, Map<String, Object> params) {

        Matcher m = Pattern.compile("\\$\\{\\w+\\}").matcher(template);

        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            String param = m.group();
            Object value = params.get(param.substring(2, param.length() - 1));
            m.appendReplacement(sb, value == null ? "" : value.toString());
        }
        m.appendTail(sb);
        return sb.toString();
    }

    /**
     * 自定义模板的前后缀，默认是${animal}
     *
     * @param source
     * @param valueMap
     * @return
     */
    public static String stringSubstitutor(String source, Map<String, String> valueMap) {
        StringSubstitutor sub = new StringSubstitutor(valueMap, "{{", "}}");
        source = sub.replace(source);
        return source;
    }

    /**
     * 简单实现${}模板功能
     * 如${aa} cc ${bb} 其中 ${aa}, ${bb} 为占位符. 可用相关变量进行替换
     * @param templateStr 模板字符串
     * @param data     替换的变量值
     * @param defaultNullReplaceVals  默认null值替换字符, 如果不提供, 则为字符串""
     * @return 返回替换后的字符串, 如果模板字符串为null, 则返回null
     */

    @SuppressWarnings("unchecked")
    public static String simpleTemplate(String templateStr, Map<String, ?> data, String... defaultNullReplaceVals) {
        if(templateStr == null) return null;

        if(data == null) data = Collections.EMPTY_MAP;

        String nullReplaceVal = defaultNullReplaceVals.length > 0 ? defaultNullReplaceVals[0] : "";
        Pattern pattern = Pattern.compile("\\$\\{([^}]+)}");

        StringBuffer newValue = new StringBuffer(templateStr.length());

        Matcher matcher = pattern.matcher(templateStr);

        while (matcher.find()) {
            String key = matcher.group(1);
            String r = data.get(key) != null ? data.get(key).toString() : nullReplaceVal;
            matcher.appendReplacement(newValue, r.replaceAll("\\\\", "\\\\\\\\")); //这个是为了替换windows下的文件目录在java里用\\表示
        }

        matcher.appendTail(newValue);

        return newValue.toString();
    }


    static String generateWelcome(String jsonStr,String template){
        Gson gson = new Gson();
        HashMap jsonMap = gson.fromJson(jsonStr, HashMap.class);
        for (Object s : jsonMap.keySet()) {
            template = template.replaceAll("\\$\\{".concat(s.toString()).concat("\\}")
                , jsonMap.get(s.toString()).toString());
        }
        return template;
    }

    /**
     * 根据键值对填充字符串，如("hello ${name}",{name:"xiaoming"})
     * 输出：
     * @param content
     * @param map
     * @return
     */
    public static String renderString(String content, Map<String, String> map){
        Set<Map.Entry<String, String>> sets = map.entrySet();
        for(Map.Entry<String, String> entry : sets) {
            String regex = "\\$\\{" + entry.getKey() + "\\}";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(content);
            content = matcher.replaceAll(entry.getValue());
        }
        return content;
    }

}
