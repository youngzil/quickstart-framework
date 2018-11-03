/**
 * 项目名称：quickstart-webservice 
 * 文件名：ClientTest.java
 * 版本信息：
 * 日期：2018年11月2日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.webservice.jws.client;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;

import org.quickstart.webservice.jws.server.IMyService;
import org.quickstart.webservice.jws.server.MyServiceImplService;

/**
 * ClientTest
 * 
 * @author：yangzl@asiainfo.com
 * @2018年11月2日 下午5:40:50
 * @since 1.0
 */
public class ClientTest {

    /**
     * @param args
     * @throws MalformedURLException
     */
    public static void main(String[] args) throws MalformedURLException {
        // 创建访问wsdl服务地址的url
        URL url = new URL("http://localhost:8888/ns?wsdl");
        // 通过Qname指明服务的具体信息
        QName sname = new QName("http://server.jws.webservice.quickstart.org/", "MyServiceImplService");
        MyServiceImplService msis = new MyServiceImplService(url, sname);
        IMyService ms = msis.getMyServiceImplPort();
        System.out.println(ms.minus(29, 11));
    }

}
