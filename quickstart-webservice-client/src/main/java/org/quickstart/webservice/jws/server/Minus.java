
package org.quickstart.webservice.jws.server;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>minus complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="minus">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="a" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="b" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "minus", propOrder = {
    "a",
    "b"
})
public class Minus {

    protected int a;
    protected int b;

    /**
     * 获取a属性的值。
     * 
     */
    public int getA() {
        return a;
    }

    /**
     * 设置a属性的值。
     * 
     */
    public void setA(int value) {
        this.a = value;
    }

    /**
     * 获取b属性的值。
     * 
     */
    public int getB() {
        return b;
    }

    /**
     * 设置b属性的值。
     * 
     */
    public void setB(int value) {
        this.b = value;
    }

}
