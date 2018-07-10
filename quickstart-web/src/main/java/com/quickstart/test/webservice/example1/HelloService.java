package com.quickstart.test.webservice.example1;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

/**
 * WebService 将 Java 类标记为实现 Web Service，或者将 Java 接口标记为定义 Web Service 接口
 */
@WebService(serviceName = "MyService", targetNamespace = "http://www.baidu.com")
public class HelloService {

    @WebMethod(operationName = "AliassayHello")
    @WebResult(name = "myReturn")
    public String sayHello(@WebParam(name = "name") String name) {
        return "hello: " + name;
    }

    public String sayGoodbye(String name, int i) {

        return "goodbye: " + name + i;
    }

    @WebMethod(exclude = true) // 当前方法不被发布出去
    public String sayHello2(String name) {
        return "hello " + name;
    }

    public static void main(String[] args) {
        /**
         * 参数1：服务的发布地址 参数2：服务的实现者 Endpoint 会重新启动一个线程
         */
        Endpoint.publish("http://localhost:9001/Service/HelloService", new HelloService());
        System.out.println("Server ready...");
    }
}
