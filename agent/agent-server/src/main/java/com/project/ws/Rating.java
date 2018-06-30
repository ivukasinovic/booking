package com.project.ws;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for rating complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="rating">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="dateCreated" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="star" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element ref="{http://bookingxml.com/soap-example}user"/>
 *         &lt;element ref="{http://bookingxml.com/soap-example}lodging"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "rating", propOrder = {
        "id",
        "dateCreated",
        "star",
        "user",
        "lodging"
})
public class Rating {

    protected long id;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateCreated;
    protected long star;
    @XmlElement(required = true)
    protected User user;
    @XmlElement(required = true)
    protected Lodging lodging;

    /**
     * Gets the value of the id property.
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     */
    public void setId(long value) {
        this.id = value;
    }

    /**
     * Gets the value of the dateCreated property.
     *
     * @return possible object is
     * {@link XMLGregorianCalendar }
     */
    public XMLGregorianCalendar getDateCreated() {
        return dateCreated;
    }

    /**
     * Sets the value of the dateCreated property.
     *
     * @param value allowed object is
     *              {@link XMLGregorianCalendar }
     */
    public void setDateCreated(XMLGregorianCalendar value) {
        this.dateCreated = value;
    }

    /**
     * Gets the value of the star property.
     */
    public long getStar() {
        return star;
    }

    /**
     * Sets the value of the star property.
     */
    public void setStar(long value) {
        this.star = value;
    }

    /**
     * Gets the value of the user property.
     *
     * @return possible object is
     * {@link User }
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the value of the user property.
     *
     * @param value allowed object is
     *              {@link User }
     */
    public void setUser(User value) {
        this.user = value;
    }

    /**
     * Gets the value of the lodging property.
     *
     * @return possible object is
     * {@link Lodging }
     */
    public Lodging getLodging() {
        return lodging;
    }

    /**
     * Sets the value of the lodging property.
     *
     * @param value allowed object is
     *              {@link Lodging }
     */
    public void setLodging(Lodging value) {
        this.lodging = value;
    }

}
