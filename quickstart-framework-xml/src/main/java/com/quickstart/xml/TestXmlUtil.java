package com.quickstart.xml;

import java.io.File;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import net.sf.json.JSON;
import net.sf.json.JSONObject;

public class TestXmlUtil {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {

        String configXml = FileUtils.readFileToString(new File("config/msgframe-config.xml"), "UTF-8");

        // Map<String, Object> map = XmlUtil.xml2map(configXml, false);
        Map<String, Object> map = XmlUtil.xml2mapWithAttr(configXml, false);
        // long begin = System.currentTimeMillis();
        // for(int i=0; i<1000; i++){
        // map = (Map<String, Object>) xml2mapWithAttr(doc.getRootElement());
        // }
        // System.out.println("耗时:"+(System.currentTimeMillis()-begin));
        System.out.println(map);
        System.out.println("=====================");
        JSON json = JSONObject.fromObject(map);
        // System.out.println(json.toString(1)); // 格式化输出

        byte[] xml = XmlUtil.mapToXML(map);
        String path = XmlUtil.mapToXMLFile(map, "/Users/yangzl/testmap.xml");
        byte[] xml2 = XmlUtil.jsonToXML(json.toString());
        String path2 = XmlUtil.jsonToXMLFile(json.toString(), "/Users/yangzl/testjson.xml");

        System.out.println("xml===" + new String(xml));
        System.out.println("path===" + path);
        System.out.println("xml2===" + new String(xml2));
        System.out.println("path2===" + path2);

        byte[] xml3 = XmlUtil.mapToXMLWithSchema(map);
        String path3 = XmlUtil.mapToXMLFileWithSchema(map, "/Users/yangzl/testjson.xml");

        byte[] xml4 = XmlUtil.jsonToXMLWithSchema(json.toString());
        String path4 = XmlUtil.jsonToXMLFileWithSchema(json.toString(), "/Users/yangzl/testjson.xml");

        System.out.println("xml3===" + new String(xml3));
        System.out.println("path3===" + path3);

        System.out.println("xml4===" + new String(xml4));
        System.out.println("path4===" + path4);

    }

}
