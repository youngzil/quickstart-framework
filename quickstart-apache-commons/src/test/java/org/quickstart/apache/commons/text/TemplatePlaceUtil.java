package org.quickstart.apache.commons.text;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;

import java.io.StringWriter;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by cxq on 2018-01-07.
 */
public class TemplatePlaceUtil {

    public static Configuration cfg;

    static {
        cfg = new Configuration(new Version("2.3.23"));
    }

    public static void main(String[] args) {
        Object[] obj = new Object[]{"张三", String.format("%.2f", 10.155), 10};
        System.out.println(processFormat("您好%s，晚上好！您目前余额：%s元，积分：%d", obj));
        System.out.println(processMessage("您好{0}，晚上好！您目前余额：{1}元，积分：{2}", obj));

        Map map = new HashMap();
        map.put("name", "张三");
        map.put("money", String.format("%.2f", 10.155));
        map.put("point", 10);
        System.out.println(processTemplate("您好${name}，晚上好！您目前余额：${money}元，积分：${point}", map));
        System.out.println(processFreemarker("您好${name}，晚上好！您目前余额：${money}元，积分：${point}", map));
    }

    /**
     * String.format渲染模板
     * @param template 模版
     * @param params   参数
     * @return
     */
    public static String processFormat(String template, Object... params) {
        if (template == null || params == null)
            return null;
        return String.format(template, params);
    }

    /**
     * MessageFormat渲染模板
     * @param template 模版
     * @param params   参数
     * @return
     */
    public static String processMessage(String template, Object... params) {
        if (template == null || params == null)
            return null;
        return MessageFormat.format(template, params);
    }

    /**
     * 自定义渲染模板
     * @param template 模版
     * @param params   参数
     * @return
     */
    public static String processTemplate(String template, Map<String, Object> params) {
        if (template == null || params == null)
            return null;
        StringBuffer sb = new StringBuffer();
        Matcher m = Pattern.compile("\\$\\{\\w+\\}").matcher(template);
        while (m.find()) {
            String param = m.group();
            Object value = params.get(param.substring(2, param.length() - 1));
            m.appendReplacement(sb, value == null ? "" : value.toString());
        }
        m.appendTail(sb);
        return sb.toString();
    }

    /**
     * Freemarker渲染模板
     * @param template 模版
     * @param params   参数
     * @return
     */
    public static String processFreemarker(String template, Map<String, Object> params) {
        if (template == null || params == null)
            return null;
        try {
            StringWriter result = new StringWriter();
            Template tpl = new Template("strTpl", template, cfg);
            tpl.process(params, result);
            return result.toString();
        } catch (Exception e) {
            return null;
        }
    }

}
