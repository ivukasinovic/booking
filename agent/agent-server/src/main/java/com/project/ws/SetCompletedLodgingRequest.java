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
 *         &lt;element name="reservation" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "reservation"
})
@XmlRootElement(name = "setCompletedLodgingRequest")
public class SetCompletedLodgingRequest {

    @XmlElement(required = true)
    protected String reservation;

    /**
     * Gets the value of the reservation property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getReservation() {
        return reservation;
    }

    /**
     * Sets the value of the reservation property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setReservation(String value) {
        this.reservation = value;
    }

}
