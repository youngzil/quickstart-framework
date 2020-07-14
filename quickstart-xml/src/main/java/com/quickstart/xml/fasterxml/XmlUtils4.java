package com.quickstart.xml.fasterxml;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.xml.XMLSerializer;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class XmlUtils4 {

    private static ObjectMapper objectMapper = new ObjectMapper();
    private static XmlMapper xmlMapper = new XmlMapper();

    public static byte[] mapToXML(Map map) throws Exception {
        StringBuffer sb = new StringBuffer();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        mapToXML(map, sb);
        try {
            return sb.toString().getBytes("UTF-8");
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public static byte[] jsonToXML(String json) throws Exception {
        Map<String, Object> map = json2map(json);
        return mapToXML(map);
    }

    public static String jsontoXml(String json) {
        try {
            XMLSerializer serializer = new XMLSerializer();
            JSON jsonObject = JSONSerializer.toJSON(json);
            return serializer.write(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws Exception {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", "horizon");
        JSONArray jsonArray = new JSONArray();
        JSONObject dataJson = new JSONObject();
        jsonArray.add(jsonObject);
        // jsonArray.add(jsonObject);
        dataJson.put("data", jsonArray);
        System.out.println(dataJson.toString());

        String xml = json2xml2(dataJson.toString());
        String xml2 = jsontoXml(dataJson.toString());
        System.out.println("xml:" + xml);
        System.out.println("xml2:" + xml2);
        xml = "<data><username>horizon</username></data>";
        System.out.println(xmlToJson(xml));

        Document document = DocumentHelper.parseText(xml);
        File file = new File("test.xml");
        if (file.exists()) {
            file.delete();
        }
        try {
            file.createNewFile();
            OutputFormat format = OutputFormat.createPrettyPrint();// 缩减型格式
            XMLWriter out = new XMLWriter(new FileWriter(file), format);
            out.write(document);
            out.flush();
            out.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * XML转JSON
     * 
     * @param xml
     * @return
     */
    public static String xmlToJson(String xml) {

        StringWriter stringWriter = new StringWriter();
        JsonParser jsonParser;
        try {
            jsonParser = xmlMapper.getFactory().createParser(xml);
            JsonGenerator jsonGenerator = objectMapper.getFactory().createGenerator(stringWriter);
            while (jsonParser.nextToken() != null) {
                jsonGenerator.copyCurrentEvent(jsonParser);
            }
            jsonParser.close();
            jsonGenerator.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return stringWriter.toString();
    }

    /**
     * json string convert to xml string
     */
    public static String json2xml2(String jsonStr) throws Exception {
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        JsonNode root = objectMapper.readTree(jsonStr);
        System.out.println(root);
        String xml = xmlMapper.writeValueAsString(root);

        return xml;
    }

    @SuppressWarnings("rawtypes")
    private static void mapToXML(Map map, StringBuffer sb) {
        Set set = map.keySet();
        for (Iterator it = set.iterator(); it.hasNext();) {
            String key = (String) it.next();
            Object value = map.get(key);
            if (null == value)
                value = "";
            if (value.getClass().getName().equals("java.util.ArrayList")) {
                ArrayList list = (ArrayList) map.get(key);
                sb.append("<" + key + ">");
                for (int i = 0; i < list.size(); i++) {
                    HashMap hm = (HashMap) list.get(i);
                    mapToXML(hm, sb);
                }
                sb.append("</" + key + ">");

            } else {
                if (value instanceof HashMap) {
                    sb.append("<" + key + ">");
                    mapToXML((HashMap) value, sb);
                    sb.append("</" + key + ">");
                } else {
                    sb.append("<" + key + ">" + value + "</" + key + ">");
                }

            }
        }
    }

    /**
     * json string convert to map
     */
    @SuppressWarnings("unchecked")
    private static <T> Map<String, Object> json2map(String jsonStr) throws Exception {
        return objectMapper.readValue(jsonStr, Map.class);
    }

    public static void main22(String[] args) throws Exception {
        Map<String, Object> dataMap = new LinkedHashMap<String, Object>(); // 默认
        dataMap.put("rtnCode", "02");
        dataMap.put("rtnMsg", "查询失败");
        dataMap.put("idWltCloudDistrict", "专区id");

        Map<String, Object> bizMap = new LinkedHashMap<String, Object>();
        bizMap.put("data", dataMap);// data节点是一个map

        Map<String, Object> objMap = new LinkedHashMap<String, Object>();
        objMap.put("idWltCloudDistrict", "专区id");
        objMap.put("districtName", "专区名称");
        objMap.put("validateDateStart", "专区有效期开始");
        objMap.put("validateDateEnd", "专区有效期结束");
        objMap.put("status", "专区状态(00-待发布;01-销售中; 02-已下架;)");
        objMap.put("partnerId", "合作伙伴id");
        objMap.put("channelId", "渠道id");
        objMap.put("areaId", "商圈专区id");
        dataMap.putAll(objMap); //

        List<Map<String, Object>> prodKindList1 = new ArrayList<Map<String, Object>>();
        for (int i = 1; i <= 2; i++) {
            // 第二层
            List<Map<String, Object>> prodKindList2 = new ArrayList<Map<String, Object>>();
            for (int j = 1; j <= 2; j++) {
                Map<String, Object> prodKindObj2 = new LinkedHashMap<String, Object>();
                prodKindObj2.put("idWltCloudProdKind", "类目ID");
                prodKindObj2.put("prodKindName", "类目名称");
                prodKindObj2.put("prodKindIdx", "类目序号");
                Map<String, Object> prodKind2 = new LinkedHashMap<String, Object>();
                prodKind2.put("prodKind", prodKindObj2);
                prodKindList2.add(prodKind2);
            }

            // 第一层
            Map<String, Object> prodKindOjb1 = new LinkedHashMap<String, Object>();
            prodKindOjb1.put("idWltCloudProdKind", "类目ID");
            prodKindOjb1.put("prodKindName", "类目名称");
            prodKindOjb1.put("prodKindIdx", "类目序号");
            prodKindOjb1.put("prodKindList", prodKindList2);
            Map<String, Object> prodKind1 = new LinkedHashMap<String, Object>();
            prodKind1.put("prodKind", prodKindOjb1);
            prodKindList1.add(prodKind1);
        }

        dataMap.put("prodKindList", prodKindList1);

        byte[] b = XmlUtils4.mapToXML(bizMap);

        Document document = DocumentHelper.parseText(new String(b));
        File file = new File("/Users/yangzl/test.xml");
        if (file.exists()) {
            file.delete();
        }
        try {
            file.createNewFile();
            OutputFormat format = OutputFormat.createPrettyPrint();// 缩减型格式
            XMLWriter out = new XMLWriter(new FileWriter(file), format);
            out.write(document);
            out.flush();
            out.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
