/**
 * 项目名称：quickstart-json 
 * 文件名：JsonObjectUtil.java
 * 版本信息：
 * 日期：2017年12月13日
 * Copyright yangzl Corporation 2017
 * 版权所有 *
 */
package org.quickstart.json.test.util;

import net.sf.json.JSONObject;

/**
 * JsonObjectUtil
 * 
 * @author：youngzil@163.com
 * @2017年12月13日 下午10:39:30
 * @since 1.0
 */
public class JsonlibUtil {

    public static String bean2Json(Object obj) {
        JSONObject jsonObject = JSONObject.fromObject(obj);
        return jsonObject.toString();
    }

    @SuppressWarnings("unchecked")
    public static <T> T json2Bean(String jsonStr, Class<T> objClass) {
        return (T) JSONObject.toBean(JSONObject.fromObject(jsonStr), objClass);
    }

}
