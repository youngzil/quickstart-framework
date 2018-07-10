package com.quickstart.test.webservice.cxf;

/*import javax.xml.ws.Endpoint; */

import javax.xml.ws.Endpoint;

/**
 * <b>function:</b> 发布CXF WebService 总结： 发布方式：JaxWsServerFactoryBean.create()方法 或 Endpoint.publish(address, service); 发布类：发布的类是实体类 注意：通过这种方式发布的WebService不符合wsdl规范,留此作为参考，具体参见类DeployInterceptorService
 **/
public class DeployHelloWorldService {
    public static void deployService() {
        // 发布方式一
        System.out.println("Server start ......");
        HelloWorldService service = new HelloWorldService();
        String address = "http://localhost:8080/webservice?wsdl"; // 发布
        Endpoint.publish(address, service);

        // 发布方式二(推荐)
        /*JaxWsServerFactoryBean factory = new JaxWsServerFactoryBean();
        String address = "http://localhost:8080/webservice?wsdl";
        factory.setServiceClass(IHelloWorldService.class);// serviceClass：必须是一个接口
        factory.setServiceBean(new HelloWorldService());// serviceBean：是一个实现接口的实现类
        factory.setAddress(address);
        factory.create();*/
    }

    public static void main(String[] args) throws InterruptedException {
        // 发布Webservice
        deployService();
        System.out.println("server ready ......");
        Thread.sleep(1000 * 60);
        System.out.println("server exiting");
        // 休眠60秒后退出
        System.exit(0);
    }
}
