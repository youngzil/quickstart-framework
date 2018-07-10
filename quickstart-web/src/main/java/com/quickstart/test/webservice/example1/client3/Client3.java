package com.quickstart.test.webservice.example1.client3;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

/**
 * 通过客户端编程的方式调用Webservice服务 第一种方式一样
 */
public class Client3 {

    public static void main(String[] args) throws Exception {
        URL wsdlUrl = new URL("http://localhost:9001/Service/HelloService?wsdl");
        Service s = Service.create(wsdlUrl, new QName("http://www.baidu.com", "HelloServiceService"));
        HelloService hs = s.getPort(new QName("http://www.baidu.com", "HelloServicePort"), HelloService.class);
        String ret = hs.sayHello("zhangsan", 4);
        System.out.println(ret);
    }
}
