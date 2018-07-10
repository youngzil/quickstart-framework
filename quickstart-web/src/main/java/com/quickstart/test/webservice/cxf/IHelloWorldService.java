package com.quickstart.test.webservice.cxf;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

/***
 * 客户端调用WebService所需要的接口
 * 
 * @author huawei 备注： 1、接口在webservice的访问中，具有相当重要的作用，客户端的访问就是通过访问服务端的接口，调用起方法。 2、webservice的注释，用于在wsdl中显示，作用是便于访问者阅读理解和调用接口内的方法。 3、实体类应该继承WebService的接口，但是实体类中的注解不起作用。 3、WebService常用的元注释：
 * @WebService：将接口发布成为服务，暴露方法，在实现类中使用endpointInterface会起作用
 * @SOAPBinding：服务端接受和发送消息的编码样式，在实现类中无效果
 * @WebMethod：对接口中方法是否需要暴露和暴露后的一些参数指定，在实现类中无效果
 * @WebResult：对调用该暴露方法的返回类型进行注释，在实现类中无效果
 * @WebParam：对暴露方法的参数进行注释，在实现类中无效果
 */

@WebService
@SOAPBinding(style = Style.DOCUMENT, use = Use.LITERAL)
// 定义发送到 Web Service 的消息和从 Web Service 发送的消息的编码样式
public interface IHelloWorldService {
    @WebMethod(operationName = "sayHello", exclude = false)
    @WebResult(name = "String")
    // 指定调用该方法返回的名字
    public String sayHello(@WebParam(name = "用户名") String name);
}
