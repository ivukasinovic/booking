
package com.project.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="lodging" type="{http://bookingxml.com/soap-example}lodgingRes"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "lodging"
})
@XmlRootElement(name = "setLodgingRequest")
public class SetLodgingRequest {

    @XmlElement(required = true)
    protected LodgingRes lodging;

    /**
     * Gets the value of the lodging property.
     * 
     * @return
     *     possible object is
     *     {@link LodgingRes }
     *     
     */
    public LodgingRes getLodging() {
        return lodging;
    }

    /**
     * Sets the value of the lodging property.
     * 
     * @param value
     *     allowed object is
     *     {@link LodgingRes }
     *     
     */
    public void setLodging(LodgingRes value) {
        this.lodging = value;
    }

}
