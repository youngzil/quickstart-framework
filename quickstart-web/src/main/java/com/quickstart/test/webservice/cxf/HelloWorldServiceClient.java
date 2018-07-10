package com.quickstart.test.webservice.cxf;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

/***
 * 客户端调用代码 总结： 调用方式：IHelloWorldService service = (IHelloWorldService)factory.create(); 调用类：通过调用服务端接口类型,factory.create()返回该接口类
 * 
 * @author huawei
 * 
 */
public class HelloWorldServiceClient {
    public static void main(String[] args) {
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        // 必须是一个接口
        factory.setServiceClass(IHelloWorldService.class);
        factory.setAddress("http://localhost:8080/webservice?wsdl");
        // 创建接口对象
        IHelloWorldService service = (IHelloWorldService) factory.create();
        System.out.println("[result]" + service.sayHello("hoojo"));
    }
}
