/**
 * 项目名称：quickstart-lombok 
 * 文件名：DataObjectTest.java
 * 版本信息：
 * 日期：2018年1月21日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.lombok;

import static org.junit.Assert.*;

import org.junit.Test;

import lombok.extern.java.Log;

/**
 * DataObjectTest
 * 
 * @author：yangzl@asiainfo.com
 * @2018年1月21日 下午10:35:57
 * @since 1.0
 */
@Log
public class DataObjectTest {

    @Test
    public void test() {

        DataObject dataObject = new DataObject();
        dataObject.setId("211");
        dataObject.setName("yangziliang");
        dataObject.setUserId("yangzl");
        dataObject.setPassword("nicaia");

        System.out.println("dataObject=" + dataObject);
        
        DataObject dataObject2 = new DataObject();
        dataObject2.setId("211");
        dataObject2.setName("yangziliang");
        dataObject2.setUserId("yangzl");
        dataObject2.setPassword("nicaia");
  
        System.out.println(dataObject.equals(dataObject2));  
  
        dataObject2.setId("2018");  
  
        System.out.println(dataObject.equals(dataObject2));  
  
        log.info("lombok test");  
  
    }

}
