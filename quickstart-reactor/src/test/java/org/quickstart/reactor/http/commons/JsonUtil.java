package org.quickstart.reactor.http.commons;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;

/**
 * JSON的转换工具
 *
 * @author zhujh
 */
public class JsonUtil {
    private static ConcurrentHashMap<String,Map> jsonMap = new ConcurrentHashMap<String,Map>();
    public static void main(String[] args) throws Exception {

        // CenterLoadResult result = new CenterLoadResult();
        // result.setCodeType("AAAAA");
        // result.setEndTime(123234234);
        // result.setFailCount(89);
        // result.setSuccess(true);
        // String aa = fromBeanToString(result);
        // System.out.println("fromBeanToString===="+aa);
        // CenterLoadResult b = fromStringToObj(aa,CenterLoadResult.class);
        // System.out.println("fromStringToObj===="+b.getCodeType());

        // IBOBusiParamInfoValue value = new BOBusiParamInfoBean();
        // value.setCode("BBBBB");
        // value.setDefalutVal("yhniuk");
        // value.setExpireDate(new Timestamp(new Date().getTime()));
        // value.setIsNull(10);
        // System.out.println(getJsonFromBO(value));
        // System.out.println(getJsonFromBO(value).toJSONString());
        //
        // JSONObject json = new JSONObject(); //返回前台的json对象
        // JSONArray array = new JSONArray(); //结果集
        // array.add(getJsonFromBO(value));
        // json.put("total", 13);
        // json.put("rows", array);
        // System.out.println("结果："+json.toJSONString());

        // net.sf.json.JSONObject json1 = new net.sf.json.JSONObject();
        // net.sf.json.JSONArray array1 = new net.sf.json.JSONArray();
        // array1.put(getJsonFromBO(value));
        // json1.put("total", 13);
        // json1.put("rows", array);
        // System.out.println("结果2："+json1.toString());
        //
        // Map pMap = new HashMap();
        // pMap.put("AAAA", "5678");
        // pMap.put("usamd", true);
        // pMap.put("12", 123);
        // System.out.println("getJsonFromMap===="+getJsonFromMap(pMap));
    }

    /**
     * 将pojo对象装换为json字符创
     *
     * @param obj
     * @return
     */
    public static String fromBeanToString(Object obj) {
        return JSONObject.toJSONString(obj);
    }

    /**
     * 将字符串转换为指定的对象
     *
     * @param <T>
     * @param json
     *            字符串
     * @param t
     *            需要返回的类型
     * @return
     */
    public static <T> T fromStringToObj(String json, Class<T> t) {
        return JSONObject.parseObject(json, t);
    }

    /**
     * 将字符串转换为指定的对象
     * @param json
     * @param t
     * @param features 可以指定fastjson的特性
     * @return
     */
    public static <T> T fromStringToObj(String json, Class<T> t,Feature[] features) {
        return JSONObject.parseObject(json, t, features);
    }



    public static Map parseMapWithCache(String jsonString){
        if(jsonString ==null ){
            return null;
        }
        Map json = jsonMap.get(jsonString);
        if(json==null){
            Map js = null;
            js =  JSONObject.parseObject(jsonString, Map.class);
            json = jsonMap.putIfAbsent(jsonString, js);
            if(json==null){
                json = js;
            }
        }
        return json;
    }

    /**
     * 将BO对象中属性转换为json
     *
     * @param dsi
     * @return
     * @throws Exception
     *//*
    public static JSONObject getJsonFromBO(DataStructInterface dsi)
            throws Exception {
        String[] propertyNames = dsi.getPropertyNames();
        // Map map = new HashMap();
        JSONObject json = new JSONObject();
        for (int i = 0; i < propertyNames.length; i++) {
            Object obj = dsi.get(propertyNames[i]);
            if (obj instanceof java.sql.Date) {
                try {
                    obj = new java.util.Date(((java.sql.Date) obj).getTime());
                } catch (Exception e) {
                    throw new Exception("时间类型转换出错！" + e);
                }
            }
            // map.put(propertyNames[i], obj);
            json.put(propertyNames[i], obj);
        }
        return json;
    }
*/
    /**
     * 将map对象转换为Json对象
     *
     * @param pMap
     * @return
     * @throws Exception
     */
    public static String getJsonFromMap(Map pMap) {
        if (pMap == null) { return ""; }
        return JSON.toJSONString(pMap, false);
    }

   /* *//**
     * 将BOList对象中属性转换为json
     *
     * @param dsi
     * @return
     * @throws Exception
     *//*
    public static String getJsonFromBOListtoArray(DataStructInterface[] dsi)
            throws Exception {
        StringBuffer sb = new StringBuffer("[");
        for (DataStructInterface aValue : dsi) {
            sb.append(getJsonFromBO(aValue).toJSONString() + ",");
        }
        if (sb.length() > 1) {
            sb.deleteCharAt(sb.length() - 1);
            sb.append("]");
            return sb.toString();
        }
        return null;
    }*/




}
