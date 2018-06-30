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
 *         &lt;element name="cities" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "cities"
})
@XmlRootElement(name = "getCitiesRequest")
public class GetCitiesRequest {

    @XmlElement(required = true)
    protected String cities;

    /**
     * Gets the value of the cities property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCities() {
        return cities;
    }

    /**
     * Sets the value of the cities property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCities(String value) {
        this.cities = value;
    }

}
