
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
 *         &lt;element name="imagesList" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "imagesList"
})
@XmlRootElement(name = "getImagesRequest")
public class GetImagesRequest {

    @XmlElement(required = true)
    protected String imagesList;

    /**
     * Gets the value of the imagesList property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImagesList() {
        return imagesList;
    }

    /**
     * Sets the value of the imagesList property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImagesList(String value) {
        this.imagesList = value;
    }

}
