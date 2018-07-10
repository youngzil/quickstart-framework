package com.quickstart.test.webservice.cxf;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

/***
 * 服务端发布的实体类
 * 
 * @author huawei
 * 
 */
@WebService
@SOAPBinding(style = Style.RPC)
public class HelloWorldService implements IHelloWorldService {
    public String sayHello(@WebParam(name = "name") String name) {// 不起作用的@WebParam
        return name + " say: Hello World";
    }

    // 非接口的方法
    public String testHayHello(@WebParam(name = "用dddddd名") String name) {// 不会被发布
        return name + " sayHello;";
    }
}
