/**
 * 项目名称：quickstart-concurrentlinkedhashmap 
 * 文件名：Test.java
 * 版本信息：
 * 日期：2018年5月22日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.compactmap;

import java.util.Map;

import vlsi.utils.CompactHashMap;

/**
 * Test
 * 
 * @author：youngzil@163.com
 * @2018年5月22日 下午12:21:37
 * @since 1.0
 */
public class Test {

    public static void main(String[] args) {

        Map<String, String> metadata = new CompactHashMap<>();
        metadata.put("sss", "333");
    }

}
