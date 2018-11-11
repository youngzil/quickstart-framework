/**
 * 项目名称：quickstart-xbean 
 * 文件名：ConfigFileModifiedFactory.java
 * 版本信息：
 * 日期：2017年3月19日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.xbean.hot.loading.xml;

import java.util.HashMap;
import java.util.Map;

/**
 * ConfigFileModifiedFactory 
 *  
 * @author：youngzil@163.com
 * @2017年3月19日 下午7:46:42 
 * @version 1.0
 */
/**
 * declaration： 专门用于保存资源文件path ： 修改时间的工厂类，实质上是维护着一个map author wenkangqiang date 2016年3月5日
 */
public class ConfigFileModifiedFactory {

    private static ConfigFileModifiedFactory configFactory = new ConfigFileModifiedFactory();
    private Map<String, Long> fileModifiedMap = new HashMap<String, Long>();

    public static ConfigFileModifiedFactory getInstance() {
        return configFactory;
    }

    public void put(String key, long lastModified) {
        fileModifiedMap.put(key, lastModified);
    }

    public Long get(String key) {
        return fileModifiedMap.get(key);
    }

}
