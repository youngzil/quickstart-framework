
package com.quickstart.test.webservice.example1.client;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by the JAX-WS RI. JAX-WS RI 2.1.6 in JDK 6 Generated source version: 2.1
 * 
 */
@WebService(name = "HelloService", targetNamespace = "http://www.baidu.com")
@XmlSeeAlso({ObjectFactory.class})
public interface HelloService {

    /**
     * 
     * @param name
     * @return returns java.lang.String
     */
    @WebMethod(operationName = "AliassayHello")
    @WebResult(name = "myReturn", targetNamespace = "")
    @RequestWrapper(localName = "AliassayHello", targetNamespace = "http://www.baidu.com", className = "com.yang.test.webservice.example1.client.AliassayHello")
    @ResponseWrapper(localName = "AliassayHelloResponse", targetNamespace = "http://www.baidu.com", className = "com.yang.test.webservice.example1.client.AliassayHelloResponse")
    public String aliassayHello(@WebParam(name = "name", targetNamespace = "") String name);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "sayGoodbye", targetNamespace = "http://www.baidu.com", className = "com.yang.test.webservice.example1.client.SayGoodbye")
    @ResponseWrapper(localName = "sayGoodbyeResponse", targetNamespace = "http://www.baidu.com", className = "com.yang.test.webservice.example1.client.SayGoodbyeResponse")
    public String sayGoodbye(@WebParam(name = "arg0", targetNamespace = "") String arg0, @WebParam(name = "arg1", targetNamespace = "") int arg1);

}
