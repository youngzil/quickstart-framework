
package org.quickstart.webservice.jws.server;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>minusResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="minusResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="minusResult" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "minusResponse", propOrder = {
    "minusResult"
})
public class MinusResponse {

    protected int minusResult;

    /**
     * 获取minusResult属性的值。
     * 
     */
    public int getMinusResult() {
        return minusResult;
    }

    /**
     * 设置minusResult属性的值。
     * 
     */
    public void setMinusResult(int value) {
        this.minusResult = value;
    }

}
