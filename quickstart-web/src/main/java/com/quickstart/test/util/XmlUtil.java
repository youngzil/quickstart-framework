/**
 * 项目名称：quickstart-web 
 * 文件名：XmlUtil.java
 * 版本信息：
 * 日期：2017年2月18日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package com.quickstart.test.util;

/**
 * XmlUtil 
 *  
 * @author：youngzil@163.com
 * @2017年2月18日 上午11:48:52 
 * @version 1.0
 */
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * @author 蔡政滦
 * @version 2015年7月23日
 */
public class XmlUtil {

    /**
     * 根据Map组装xml消息体，值对象仅支持基本数据类型、String、BigInteger、BigDecimal，以及包含元素为上述支持数据类型的Map
     * 
     * @param vo
     * @param rootElement
     * @return
     * @author 蔡政滦 modify by 2015-6-5
     */
    public static String map2xmlBody(Map<String, Object> vo, String rootElement) {
        org.dom4j.Document doc = DocumentHelper.createDocument();
        Element body = DocumentHelper.createElement(rootElement);
        doc.add(body);
        __buildMap2xmlBody(body, vo);
        return doc.asXML();
    }

    private static void __buildMap2xmlBody(Element body, Map<String, Object> vo) {
        if (vo != null) {
            Iterator<String> it = vo.keySet().iterator();
            while (it.hasNext()) {
                String key = (String) it.next();
                if (StringUtils.isNotEmpty(key)) {
                    Object obj = vo.get(key);
                    Element element = DocumentHelper.createElement(key);
                    if (obj != null) {
                        if (obj instanceof java.lang.String) {
                            element.setText((String) obj);
                        } else {
                            if (obj instanceof java.lang.Character || obj instanceof java.lang.Boolean || obj instanceof java.lang.Number || obj instanceof java.math.BigInteger
                                    || obj instanceof java.math.BigDecimal) {
                                org.dom4j.Attribute attr = DocumentHelper.createAttribute(element, "type", obj.getClass().getCanonicalName());
                                element.add(attr);
                                element.setText(String.valueOf(obj));
                            } else if (obj instanceof java.util.Map) {
                                org.dom4j.Attribute attr = DocumentHelper.createAttribute(element, "type", java.util.Map.class.getCanonicalName());
                                element.add(attr);
                                __buildMap2xmlBody(element, (Map<String, Object>) obj);
                            } else {
                            }
                        }
                    }
                    body.add(element);
                }
            }
        }
    }

    /**
     * 根据xml消息体转化为Map
     * 
     * @param xml
     * @param rootElement
     * @return
     * @throws DocumentException
     * @author 蔡政滦 modify by 2015-6-5
     */
    public static Map xmlBody2map(String xml, String rootElement) throws DocumentException {
        org.dom4j.Document doc = DocumentHelper.parseText(xml);
        Element body = (Element) doc.selectSingleNode("/" + rootElement);
        Map vo = __buildXmlBody2map(body);
        return vo;
    }

    private static Map __buildXmlBody2map(Element body) {
        Map vo = new HashMap();
        if (body != null) {
            List<Element> elements = body.elements();
            for (Element element : elements) {
                String key = element.getName();
                if (StringUtils.isNotEmpty(key)) {
                    String type = element.attributeValue("type", "java.lang.String");
                    String text = element.getText().trim();
                    Object value = null;
                    if (java.lang.String.class.getCanonicalName().equals(type)) {
                        value = text;
                    } else if (java.lang.Character.class.getCanonicalName().equals(type)) {
                        value = new java.lang.Character(text.charAt(0));
                    } else if (java.lang.Boolean.class.getCanonicalName().equals(type)) {
                        value = new java.lang.Boolean(text);
                    } else if (java.lang.Short.class.getCanonicalName().equals(type)) {
                        value = java.lang.Short.parseShort(text);
                    } else if (java.lang.Integer.class.getCanonicalName().equals(type)) {
                        value = java.lang.Integer.parseInt(text);
                    } else if (java.lang.Long.class.getCanonicalName().equals(type)) {
                        value = java.lang.Long.parseLong(text);
                    } else if (java.lang.Float.class.getCanonicalName().equals(type)) {
                        value = java.lang.Float.parseFloat(text);
                    } else if (java.lang.Double.class.getCanonicalName().equals(type)) {
                        value = java.lang.Double.parseDouble(text);
                    } else if (java.math.BigInteger.class.getCanonicalName().equals(type)) {
                        value = new java.math.BigInteger(text);
                    } else if (java.math.BigDecimal.class.getCanonicalName().equals(type)) {
                        value = new java.math.BigDecimal(text);
                    } else if (java.util.Map.class.getCanonicalName().equals(type)) {
                        value = __buildXmlBody2map(element);
                    } else {
                    }
                    vo.put(key, value);
                }
            }
        }
        return vo;
    }

}
