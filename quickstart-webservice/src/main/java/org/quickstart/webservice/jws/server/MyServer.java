/**
 * 项目名称：quickstart-webservice 
 * 文件名：MyServer.java
 * 版本信息：
 * 日期：2018年11月2日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.webservice.jws.server;

import javax.xml.ws.Endpoint;

/**
 * MyServer 
 *  
 * @author：yangzl@asiainfo.com
 * @2018年11月2日 下午5:17:03 
 * @since 1.0
 */
public class MyServer {
    
    public static void main(String[] args) {
        
//        在浏览器输入; http://localhost:8888/ns?wsdl
        
        String address = "http://localhost:8888/ns";
        Endpoint.publish(address, new MyServiceImpl());
    }
 
}
