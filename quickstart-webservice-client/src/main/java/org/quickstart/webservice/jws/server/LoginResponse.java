
package org.quickstart.webservice.jws.server;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>loginResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="loginResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="loginUser" type="{http://server.jws.webservice.quickstart.org/}user" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "loginResponse", propOrder = {
    "loginUser"
})
public class LoginResponse {

    protected User loginUser;

    /**
     * 获取loginUser属性的值。
     * 
     * @return
     *     possible object is
     *     {@link User }
     *     
     */
    public User getLoginUser() {
        return loginUser;
    }

    /**
     * 设置loginUser属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link User }
     *     
     */
    public void setLoginUser(User value) {
        this.loginUser = value;
    }

}
