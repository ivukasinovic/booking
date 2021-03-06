package com.project.ws;

import javax.xml.bind.annotation.*;


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
 *         &lt;element name="types" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "types"
})
@XmlRootElement(name = "getLodgingCategoriesRequest")
public class GetLodgingCategoriesRequest {

    @XmlElement(required = true)
    protected String types;

    /**
     * Gets the value of the types property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getTypes() {
        return types;
    }

    /**
     * Sets the value of the types property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setTypes(String value) {
        this.types = value;
    }

}
