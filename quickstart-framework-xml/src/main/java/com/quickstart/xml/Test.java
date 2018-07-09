package com.quickstart.xml;

import java.io.StringWriter;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * jsonson utils
 * 
 * @see http://jackson.codehaus.org/
 * @see https://github.com/FasterXML/jackson
 * @see http://wiki.fasterxml.com/JacksonHome
 * @author magic_yy
 *
 */
public class Test {

    private static ObjectMapper objectMapper = new ObjectMapper();
    private static XmlMapper xmlMapper = new XmlMapper();

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
     * xml string convert to json string
     */
    public static String xml2json(String xml) throws Exception {
        StringWriter w = new StringWriter();
        JsonParser jp = xmlMapper.getFactory().createParser(xml);
        JsonGenerator jg = objectMapper.getFactory().createGenerator(w);
        while (jp.nextToken() != null) {
            jg.copyCurrentEvent(jp);
        }
        jp.close();
        jg.close();
        return w.toString();
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

}
