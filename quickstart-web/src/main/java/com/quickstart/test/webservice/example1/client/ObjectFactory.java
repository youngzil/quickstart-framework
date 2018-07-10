
package com.quickstart.test.webservice.example1.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java element interface generated in the com.yang.test.webservice.example1.client package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the Java representation for XML content. The Java representation of XML content can consist of schema derived interfaces
 * and classes representing the binding of schema type definitions, element declarations and model groups. Factory methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _SayGoodbyeResponse_QNAME = new QName("http://www.baidu.com", "sayGoodbyeResponse");
    private final static QName _AliassayHelloResponse_QNAME = new QName("http://www.baidu.com", "AliassayHelloResponse");
    private final static QName _AliassayHello_QNAME = new QName("http://www.baidu.com", "AliassayHello");
    private final static QName _SayGoodbye_QNAME = new QName("http://www.baidu.com", "sayGoodbye");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.yang.test.webservice.example1.client
     * 
     */
    public ObjectFactory() {}

    /**
     * Create an instance of {@link AliassayHello }
     * 
     */
    public AliassayHello createAliassayHello() {
        return new AliassayHello();
    }

    /**
     * Create an instance of {@link SayGoodbye }
     * 
     */
    public SayGoodbye createSayGoodbye() {
        return new SayGoodbye();
    }

    /**
     * Create an instance of {@link AliassayHelloResponse }
     * 
     */
    public AliassayHelloResponse createAliassayHelloResponse() {
        return new AliassayHelloResponse();
    }

    /**
     * Create an instance of {@link SayGoodbyeResponse }
     * 
     */
    public SayGoodbyeResponse createSayGoodbyeResponse() {
        return new SayGoodbyeResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SayGoodbyeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.baidu.com", name = "sayGoodbyeResponse")
    public JAXBElement<SayGoodbyeResponse> createSayGoodbyeResponse(SayGoodbyeResponse value) {
        return new JAXBElement<SayGoodbyeResponse>(_SayGoodbyeResponse_QNAME, SayGoodbyeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AliassayHelloResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.baidu.com", name = "AliassayHelloResponse")
    public JAXBElement<AliassayHelloResponse> createAliassayHelloResponse(AliassayHelloResponse value) {
        return new JAXBElement<AliassayHelloResponse>(_AliassayHelloResponse_QNAME, AliassayHelloResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AliassayHello }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.baidu.com", name = "AliassayHello")
    public JAXBElement<AliassayHello> createAliassayHello(AliassayHello value) {
        return new JAXBElement<AliassayHello>(_AliassayHello_QNAME, AliassayHello.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SayGoodbye }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.baidu.com", name = "sayGoodbye")
    public JAXBElement<SayGoodbye> createSayGoodbye(SayGoodbye value) {
        return new JAXBElement<SayGoodbye>(_SayGoodbye_QNAME, SayGoodbye.class, null, value);
    }

}
