
package org.quickstart.webservice.jws.server;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>addResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="addResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="addResult" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "addResponse", propOrder = {
    "addResult"
})
public class AddResponse {

    protected int addResult;

    /**
     * 获取addResult属性的值。
     * 
     */
    public int getAddResult() {
        return addResult;
    }

    /**
     * 设置addResult属性的值。
     * 
     */
    public void setAddResult(int value) {
        this.addResult = value;
    }

}
