package com.quickstart.xml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quickstart.xml.bean.SchemaElement;

import net.sf.json.JSON;
import net.sf.json.JSONObject;

/**
 * xml转map，map转xml 带属性 http://happyqing.iteye.com/blog/2316275
 * 
 * @author happyqing
 * @since 2016.8.8
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class XmlUtil3 {

    public static byte[] jsonToXML(String json) throws Exception {
        Map<String, Object> map = jsonToMap(json);
        return mapToXML(map);
    }

    public static String jsonToXMLFile(String json, String path) throws Exception {
        byte[] b = jsonToXML(json);
        return writeXml(path, new String(b));
    }

    public static byte[] mapToXML(Map map) throws Exception {
        if (1 != map.size()) {
            throw new Exception("数据格式不正确，无法转为xml格式，xml文档中根元素后面的标记必须格式正确");
        }

        Document doc = map2xml(map);
        try {
            return formatXml(doc).getBytes("UTF-8");
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public static String mapToXMLFile(Map map, String path) throws Exception {
        byte[] b = mapToXML(map);
        return writeXml(path, new String(b));
    }

    public static String writeXml(String path, String json) throws Exception {
        if (StringUtils.isBlank(path)) {
            path = "/Users/yangzl/test.xml";// 默认路径
        } else if (!path.endsWith(".xml")) {
            path += "/msgframe-config.xml";
        }

        Document document = DocumentHelper.parseText(json);
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
        XMLWriter out = null;
        try {
            file.createNewFile();
            OutputFormat format = OutputFormat.createPrettyPrint();// 缩减型格式
            out = new XMLWriter(new FileWriter(file), format);
            out.write(document);
            out.flush();
            out.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (null != out) {
                out.close();
            }
        }
        return path;
    }

    public static void main(String[] args) throws Exception {
        String schemaXml = FileUtils.readFileToString(new File("config/config-schema.xml"), "UTF-8");
        SchemaElement schemaElement = schemaXml2mapWithAttr(schemaXml);

        String configXml = FileUtils.readFileToString(new File("config/msgframe-config.xml"), "UTF-8");

        Map<String, Object> map = xml2map(configXml, false);
        Map<String, Object> map2 = xml2mapWithAttr(configXml, false);
        // long begin = System.currentTimeMillis();
        // for(int i=0; i<1000; i++){
        // map = (Map<String, Object>) xml2mapWithAttr(doc.getRootElement());
        // }
        // System.out.println("耗时:"+(System.currentTimeMillis()-begin));
        System.out.println(map2);
        System.out.println("=====================");
        JSON json = JSONObject.fromObject(map2);
        System.out.println(json.toString(1)); // 格式化输出

        Document doc = map2xml(map2, "root");
        Document doc2 = map2xml(map2);
        Document doc3 = map2xmlWithSchema(map2, schemaElement);
        // Document doc = map2xml(map); //map中含有根节点的键
        System.out.println(formatXml(doc));
        System.out.println("=====================");
        System.out.println(formatXml(doc2));
        System.out.println("=====================");
    }

    /**
     * json string convert to map
     */
    @SuppressWarnings("unchecked")
    private static <T> Map<String, Object> jsonToMap(String jsonStr) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonStr, Map.class);
    }

    /**
     * xml转map 不带属性
     * 
     * @param xmlStr
     * @param needRootKey 是否需要在返回的map里加根节点键
     * @return
     * @throws DocumentException
     */
    public static Map xml2map(String xmlStr, boolean needRootKey) throws DocumentException {
        Document doc = DocumentHelper.parseText(xmlStr);
        Element root = doc.getRootElement();
        Map<String, Object> map = (Map<String, Object>) xml2map(root);
        if (root.elements().size() == 0 && root.attributes().size() == 0) {
            return map;
        }
        if (needRootKey) {
            // 在返回的map里加根节点键（如果需要）
            Map<String, Object> rootMap = new HashMap<String, Object>();
            rootMap.put(root.getName(), map);
            return rootMap;
        }
        return map;
    }

    /**
     * xml转map 带属性
     * 
     * @param xmlStr
     * @param needRootKey 是否需要在返回的map里加根节点键
     * @return
     * @throws DocumentException
     */
    public static Map xml2mapWithAttr(String xmlStr, boolean needRootKey) throws DocumentException {
        Document doc = DocumentHelper.parseText(xmlStr);
        Element root = doc.getRootElement();
        Map<String, Object> map = (Map<String, Object>) xml2mapWithAttr(root);
        /*if(root.elements().size()==0 && root.attributes().size()==0){
        	return map; //根节点只有一个文本内容
        }
        if(needRootKey){
        	//在返回的map里加根节点键（如果需要）
        	Map<String, Object> rootMap = new HashMap<String, Object>();
        	rootMap.put(root.getName(), map);
        	return rootMap;
        }*/
        return map;
    }

    /**
     * xml转map 不带属性
     * 
     * @param e
     * @return
     */
    private static Map xml2map(Element e) {
        Map map = new LinkedHashMap();
        List list = e.elements();
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Element iter = (Element) list.get(i);
                List mapList = new ArrayList();

                if (iter.elements().size() > 0) {
                    Map m = xml2map(iter);
                    if (map.get(iter.getName()) != null) {
                        Object obj = map.get(iter.getName());
                        if (!(obj instanceof List)) {
                            mapList = new ArrayList();
                            mapList.add(obj);
                            mapList.add(m);
                        }
                        if (obj instanceof List) {
                            mapList = (List) obj;
                            mapList.add(m);
                        }
                        map.put(iter.getName(), mapList);
                    } else
                        map.put(iter.getName(), m);
                } else {
                    if (map.get(iter.getName()) != null) {
                        Object obj = map.get(iter.getName());
                        if (!(obj instanceof List)) {
                            mapList = new ArrayList();
                            mapList.add(obj);
                            mapList.add(iter.getText());
                        }
                        if (obj instanceof List) {
                            mapList = (List) obj;
                            mapList.add(iter.getText());
                        }
                        map.put(iter.getName(), mapList);
                    } else
                        map.put(iter.getName(), iter.getText());
                }
            }
        } else
            map.put(e.getName(), e.getText());
        return map;
    }

    /**
     * xml转map 带属性
     * 
     * @param e
     * @return
     */
    private static Map xml2mapWithAttr(Element element) {
        // 每个element使用attribute，element，text描述，且element，text只会存在其一
        Map<String, Object> rootMap = new LinkedHashMap<String, Object>();

        // 保存当前element的属性信息
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        rootMap.put(element.getName(), map);

        List<Attribute> listAttr = element.attributes(); // 当前节点的所有属性
        if (listAttr.size() > 0) {
            List<Map<String, Object>> attrList = new ArrayList<Map<String, Object>>();
            map.put("attribute", attrList);
            for (Attribute attr : listAttr) {
                Map<String, Object> attrMap = new LinkedHashMap<String, Object>();
                attrMap.put(attr.getName(), attr.getValue());
                attrList.add(attrMap);
            }
        }

        List<Element> listElement = element.elements();
        // 包含子元素
        if (listElement.size() > 0) {
            List<Map<String, Object>> eleList = new ArrayList<Map<String, Object>>();
            map.put("element", eleList);

            for (Element iter : listElement) {
                eleList.add(xml2mapWithAttr(iter));
            }
        } else {
            // 没有子元素的标签
            map.put("text", element.getText());
        }
        return rootMap;
    }

    private static SchemaElement schemaXml2mapWithAttr(String xmlStr) throws Exception {
        Document doc = DocumentHelper.parseText(xmlStr);
        Element rootElement = doc.getRootElement();

        return schemaXml2mapWithAttr(rootElement);
    }

    private static SchemaElement schemaXml2mapWithAttr(Element rootElement) throws Exception {
        SchemaElement schemaElement = parseSchemaElementAttr(rootElement);

        List<SchemaElement> attributes = new ArrayList<SchemaElement>();
        schemaElement.setAttributes(attributes);
        List<Element> attrList = rootElement.elements("attribute");
        for (Element element : attrList) {
            attributes.add(parseSchemaElementAttr(element));
        }

        List<SchemaElement> elements = new ArrayList<SchemaElement>();
        schemaElement.setElements(elements);
        List<Element> eleList = rootElement.elements("element");
        for (Element element : eleList) {
            elements.add(schemaXml2mapWithAttr(element));
        }

        return schemaElement;
    }

    private static SchemaElement parseSchemaElementAttr(Element element) throws Exception {
        SchemaElement schemaElement = new SchemaElement();
        List<Attribute> listAttr = element.attributes(); // 当前节点的所有属性
        if (listAttr.size() > 0) {
            for (Attribute attr : listAttr) {
                if ("required".equals(attr.getName()) && "false".equals(attr.getValue())) {
                    schemaElement.setRequired(false);
                }

                if ("name".equals(attr.getName())) {
                    schemaElement.setName(attr.getValue());
                }
            }

            if (StringUtils.isBlank(schemaElement.getName())) {
                throw new Exception("config-schema.xml文件错误，标签" + element.getName() + "的name属性是必须的");
            }

        } else {
            throw new Exception("config-schema.xml文件错误，标签" + element.getName() + "的name属性是必须的");
        }
        return schemaElement;
    }

    /**
     * map转xml map中没有根节点的键
     * 
     * @param map
     * @param rootName
     * @throws DocumentException
     * @throws IOException
     */
    private static Document map2xml(Map<String, Object> map, String rootName) throws DocumentException, IOException {
        Document doc = DocumentHelper.createDocument();
        Element root = DocumentHelper.createElement(rootName);
        doc.add(root);
        map2xml(map, root);
        return doc;
    }

    private static Document map2xmlWithSchema(Map<String, Object> map, SchemaElement schemaElement) throws Exception {
        if (null != schemaElement && schemaElement.isRequired()) {
            Iterator<Map.Entry<String, Object>> entries = map.entrySet().iterator();
            if (entries.hasNext()) { // 获取第一个键创建根节点
                Map.Entry<String, Object> entry = entries.next();
                String key = entry.getKey();
                Object value = entry.getValue();

                String rootElementName = schemaElement.getName();
                if (!rootElementName.equals(key)) {
                    throw new Exception("参数" + key + "不符合config-schema.xml中的元素" + rootElementName + "的限制");
                }

                Map<String, Object> rootElementInfo = (Map<String, Object>) value;

                Set<String> attributes = new HashSet<String>();
                // 元素属性的处理
                if (rootElementInfo.containsKey("attribute")) {
                    List<Map<String, Object>> attrList = (List<Map<String, Object>>) rootElementInfo.get("attribute");
                    for (Map<String, Object> attr : attrList) {
                        attributes.addAll(attr.keySet());
                    }
                }
                // 校验属性和元素是否符合规则并保存需要校验的规则对应关系
                for (SchemaElement rootSchemaAttribute : schemaElement.getAttributes()) {
                    if (rootSchemaAttribute.isRequired() && !attributes.contains(rootSchemaAttribute.getName())) {
                        throw new Exception("参数" + key + "不符合config-schema.xml中的元素的限制" + rootSchemaAttribute.getName() + "属性必须存在");
                    }
                }
                // 子元素的校验
                Set<String> elements = new HashSet<String>();
                if (rootElementInfo.containsKey("element")) {
                    List<Map<String, Object>> eleList = (List<Map<String, Object>>) rootElementInfo.get("element");
                    for (Map<String, Object> iter : eleList) {
                        elements.addAll(iter.keySet());
                    }
                }
                List<SchemaElement> subSchemaElements = new ArrayList<SchemaElement>();
                for (SchemaElement rootSchemaElement : schemaElement.getElements()) {
                    if (rootSchemaElement.isRequired() && !elements.contains(rootSchemaElement.getName())) {
                        throw new Exception("参数" + key + "不符合config-schema.xml中的元素的限制" + rootSchemaElement.getName() + "子元素必须存在");
                    }
                }

                Element root = DocumentHelper.createElement(key);
                // 元素属性的处理
                if (rootElementInfo.containsKey("attribute")) {
                    List<Map<String, Object>> attrList = (List<Map<String, Object>>) rootElementInfo.get("attribute");
                    for (Map<String, Object> attr : attrList) {
                        // 只会循环一次
                        for (String attrKey : attr.keySet()) {
                            root.addAttribute(attrKey, attr.get(attrKey).toString());
                        }
                    }
                }

                // 包含子元素
                if (rootElementInfo.containsKey("element")) {
                    List<Map<String, Object>> eleList = (List<Map<String, Object>>) rootElementInfo.get("element");
                    for (Map<String, Object> iter : eleList) {

                        SchemaElement checkSchemaElement = null;
                        if (null != schemaElement && schemaElement.isRequired()) {
                            // 只会循环一次
                            for (String iterKey : iter.keySet()) {
                                for (SchemaElement rootSchemaElement : schemaElement.getElements()) {
                                    if (rootSchemaElement.isRequired() && iterKey.contains(rootSchemaElement.getName())) {
                                        checkSchemaElement = rootSchemaElement;
                                    }
                                }
                            }
                        }

                        map2xmlWithSchema(iter, root, checkSchemaElement);
                    }
                } else {// 不包含子元素
                    root.setText(rootElementInfo.get("text").toString());
                }

                Document doc = DocumentHelper.createDocument();
                doc.add(root);
                return doc;
            }
        } else {
            return map2xml(map);
        }
        return null;
    }

    private static Element map2xmlWithSchema(Map<String, Object> map, Element rootElement, SchemaElement schemaElement) throws Exception {

        Iterator<Map.Entry<String, Object>> entries = map.entrySet().iterator();

        while (entries.hasNext()) {
            Map.Entry<String, Object> entry = entries.next();
            String key = entry.getKey();
            Object value = entry.getValue();

            Element subElement = rootElement.addElement(key);
            Map<String, Object> subElementInfo = (Map<String, Object>) value;

            // 校验
            if (null != schemaElement && schemaElement.isRequired()) {

                String schemaElementName = schemaElement.getName();
                if (!schemaElementName.equals(key)) {
                    throw new Exception("参数" + key + "不符合config-schema.xml中的元素的限制");
                }

                Set<String> attributes = new HashSet<String>();
                // 元素属性的处理
                if (subElementInfo.containsKey("attribute")) {
                    List<Map<String, Object>> attrList = (List<Map<String, Object>>) subElementInfo.get("attribute");
                    for (Map<String, Object> attr : attrList) {
                        attributes.addAll(attr.keySet());
                    }
                }
                // 校验属性和元素是否符合规则并保存需要校验的规则对应关系
                for (SchemaElement rootSchemaAttribute : schemaElement.getAttributes()) {
                    if (rootSchemaAttribute.isRequired() && !attributes.contains(rootSchemaAttribute.getName())) {
                        throw new Exception("参数" + key + "不符合config-schema.xml中的元素的限制" + rootSchemaAttribute.getName() + "属性必须存在");
                    }
                }
                // 子元素的校验
                Set<String> elements = new HashSet<String>();
                if (subElementInfo.containsKey("element")) {
                    List<Map<String, Object>> eleList = (List<Map<String, Object>>) subElementInfo.get("element");
                    for (Map<String, Object> iter : eleList) {
                        elements.addAll(iter.keySet());
                    }
                }
                for (SchemaElement rootSchemaElement : schemaElement.getElements()) {
                    if (rootSchemaElement.isRequired() && !elements.contains(rootSchemaElement.getName())) {
                        throw new Exception("参数" + key + "不符合config-schema.xml中的元素的限制" + rootSchemaElement.getName() + "子元素必须存在");
                    }
                }

            }
            // 元素属性的处理
            if (subElementInfo.containsKey("attribute")) {
                List<Map<String, Object>> attrList = (List<Map<String, Object>>) subElementInfo.get("attribute");
                for (Map<String, Object> attr : attrList) {
                    // 只会循环一次
                    for (String attrKey : attr.keySet()) {
                        subElement.addAttribute(attrKey, attr.get(attrKey).toString());
                    }
                }
            }

            // 包含子元素
            if (subElementInfo.containsKey("element")) {
                List<Map<String, Object>> eleList = (List<Map<String, Object>>) subElementInfo.get("element");
                for (Map<String, Object> iter : eleList) {

                    SchemaElement checkSchemaElement = null;
                    if (null != schemaElement && schemaElement.isRequired()) {
                        // 只会循环一次
                        for (String iterKey : iter.keySet()) {
                            for (SchemaElement rootSchemaElement : schemaElement.getElements()) {
                                if (rootSchemaElement.isRequired() && iterKey.contains(rootSchemaElement.getName())) {
                                    checkSchemaElement = rootSchemaElement;
                                }
                            }
                        }
                    }
                    map2xmlWithSchema(iter, subElement, checkSchemaElement);
                }
            } else {// 不包含子元素
                subElement.setText(subElementInfo.get("text").toString());
            }

        }
        return rootElement;
    }

    /**
     * map转xml map中含有根节点的键
     * 
     * @param map
     * @throws DocumentException
     * @throws IOException
     */
    private static Document map2xml(Map<String, Object> map) throws DocumentException, IOException {
        Iterator<Map.Entry<String, Object>> entries = map.entrySet().iterator();
        if (entries.hasNext()) { // 获取第一个键创建根节点
            Map.Entry<String, Object> entry = entries.next();
            String key = entry.getKey();
            Object value = entry.getValue();
            Element root = DocumentHelper.createElement(key);
            Map<String, Object> rootElementInfo = (Map<String, Object>) value;

            // 元素属性的处理
            if (rootElementInfo.containsKey("attribute")) {
                List<Map<String, Object>> attrList = (List<Map<String, Object>>) rootElementInfo.get("attribute");
                for (Map<String, Object> attr : attrList) {
                    // 只会循环一次
                    for (String attrKey : attr.keySet()) {
                        root.addAttribute(attrKey, attr.get(attrKey).toString());
                    }
                }
            }

            // 包含子元素
            if (rootElementInfo.containsKey("element")) {
                List<Map<String, Object>> eleList = (List<Map<String, Object>>) rootElementInfo.get("element");
                for (Map<String, Object> iter : eleList) {
                    map2xml(iter, root);
                }
            } else {// 不包含子元素
                root.setText(rootElementInfo.get("text").toString());
            }

            Document doc = DocumentHelper.createDocument();
            doc.add(root);
            return doc;
        }
        return null;
    }

    /**
     * map转xml
     * 
     * @param map
     * @param rootElement xml元素
     * @return
     */
    private static Element map2xml(Map<String, Object> map, Element rootElement) {
        Iterator<Map.Entry<String, Object>> entries = map.entrySet().iterator();

        while (entries.hasNext()) {
            Map.Entry<String, Object> entry = entries.next();
            String key = entry.getKey();
            Object value = entry.getValue();

            Element subElement = rootElement.addElement(key);
            Map<String, Object> subElementInfo = (Map<String, Object>) value;

            // 元素属性的处理
            if (subElementInfo.containsKey("attribute")) {
                List<Map<String, Object>> attrList = (List<Map<String, Object>>) subElementInfo.get("attribute");
                for (Map<String, Object> attr : attrList) {
                    // 只会循环一次
                    for (String attrKey : attr.keySet()) {
                        subElement.addAttribute(attrKey, attr.get(attrKey).toString());
                    }
                }
            }

            // 包含子元素
            if (subElementInfo.containsKey("element")) {
                List<Map<String, Object>> eleList = (List<Map<String, Object>>) subElementInfo.get("element");
                for (Map<String, Object> iter : eleList) {
                    map2xml(iter, subElement);
                }
            } else {// 不包含子元素
                subElement.setText(subElementInfo.get("text").toString());
            }

        }
        return rootElement;
    }

    /**
     * 格式化输出xml
     * 
     * @param xmlStr
     * @return
     * @throws DocumentException
     * @throws IOException
     */
    public static String formatXml(String xmlStr) throws DocumentException, IOException {
        Document document = DocumentHelper.parseText(xmlStr);
        return formatXml(document);
    }

    /**
     * 格式化输出xml
     * 
     * @param document
     * @return
     * @throws DocumentException
     * @throws IOException
     */
    private static String formatXml(Document document) throws DocumentException, IOException {
        // 格式化输出格式
        OutputFormat format = OutputFormat.createPrettyPrint();
        // format.setEncoding("UTF-8");
        StringWriter writer = new StringWriter();
        // 格式化输出流
        XMLWriter xmlWriter = new XMLWriter(writer, format);
        // 将document写入到输出流
        xmlWriter.write(document);
        xmlWriter.close();
        return writer.toString();
    }

}
