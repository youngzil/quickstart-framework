/**
 * 项目名称：quickstart-json 
 * 文件名：FastJsonUtil.java
 * 版本信息：
 * 日期：2017年12月13日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.json.test.util;

import com.alibaba.fastjson.JSON;

/**
 * FastJsonUtil
 * 
 * @author：youngzil@163.com
 * @2017年12月13日 下午10:40:46
 * @since 1.0
 */
public class FastJsonUtil {

    public static String bean2Json(Object obj) {
        return JSON.toJSONString(obj);
    }

    public static <T> T json2Bean(String jsonStr, Class<T> objClass) {
        return JSON.parseObject(jsonStr, objClass);
    }

}
