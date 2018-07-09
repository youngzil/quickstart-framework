package com.quickstart.xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class XmlUtils3 {

    private static XmlMapper xmlMapper = new XmlMapper();
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static byte[] mapToXML(Map map) {
        System.out.println("将Map转成Xml, Map：" + map.toString());

        StringBuffer sb = new StringBuffer();
        mapToXML(map, sb);

        System.out.println("将Map转成Xml, Xml：" + sb.toString());
        try {
            return sb.toString().getBytes("UTF-8");
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @SuppressWarnings("rawtypes")
    private static void mapToXML(Map map, StringBuffer sb) {
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
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
    public static <T> Map<String, Object> json2map(String jsonStr) throws Exception {
        return objectMapper.readValue(jsonStr, Map.class);
    }

    /**
     * json string convert to xml string
     */
    public static String json2xml(String jsonStr) throws Exception {
        JsonNode root = objectMapper.readTree(jsonStr);
        String xml = xmlMapper.writeValueAsString(root);
        return xml;
    }

    /**
     * 将Map准换为JSON字符串
     * 
     * @param map
     * @return JSON字符串
     */
    public static String getJsonStringFromMap(Map<?, ?> map) {
        JSONObject object = JSONObject.fromObject(map);
        return object.toString();
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

        String xml = json2xml(dataJson.toString());
        System.out.println("xml:" + xml);

    }

    public static void main22(String[] args) {
        Map<String, Object> dataMap = new LinkedHashMap<String, Object>(); // 默认
        dataMap.put("rtnCode", "02");
        dataMap.put("rtnMsg", "查询失败");
        dataMap.put("idWltCloudDistrict", "专区id");

        Map<String, Object> bizMap = new LinkedHashMap<String, Object>();
        bizMap.put("serviceId", "serviceId001");
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

        XmlUtils3.mapToXML(bizMap);
    }

}
