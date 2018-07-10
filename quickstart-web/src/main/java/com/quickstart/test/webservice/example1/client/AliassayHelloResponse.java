
package com.quickstart.test.webservice.example1.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for AliassayHelloResponse complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AliassayHelloResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="myReturn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AliassayHelloResponse", propOrder = {"myReturn"})
public class AliassayHelloResponse {

    protected String myReturn;

    /**
     * Gets the value of the myReturn property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getMyReturn() {
        return myReturn;
    }

    /**
     * Sets the value of the myReturn property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setMyReturn(String value) {
        this.myReturn = value;
    }

}
