package com.quickstart.xml.dom4j;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class XmlUtils {

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

    public static String mapToXMLFile(Map map, String path) throws Exception {
        byte[] b = mapToXML(map);
        return writeXml(path, new String(b));
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
    private static <T> Map<String, Object> jsonToMap(String jsonStr) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonStr, Map.class);
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
        Map<String, Object> dataMap = new LinkedHashMap<String, Object>(); // 默认
        dataMap.put("rtnCode", "02");
        dataMap.put("rtnMsg", "查询失败");
        dataMap.put("idWltCloudDistrict", "专区id");

        Map<String, Object> bizMap = new LinkedHashMap<String, Object>();
        bizMap.put("data", dataMap);// data节点是一个map
        bizMap.put("data2", dataMap);// data节点是一个map

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

        System.out.println(bizMap.size());
        System.out.println(mapToXMLFile(bizMap, null));

    }

}
