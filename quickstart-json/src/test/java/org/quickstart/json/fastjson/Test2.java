/**
 * 项目名称：quickstart-json 
 * 文件名：Test2.java
 * 版本信息：
 * 日期：2017年12月13日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.json.fastjson;

import com.alibaba.fastjson.JSON;

/**
 * Test2
 * 
 * @author：yangzl@asiainfo.com
 * @2017年12月13日 下午10:35:26
 * @since 1.0
 */
public class Test2 {

    public static String bean2Json(Object obj) {
        return JSON.toJSONString(obj);
    }

    public static <T> T json2Bean(String jsonStr, Class<T> objClass) {
        return JSON.parseObject(jsonStr, objClass);
    }

}
