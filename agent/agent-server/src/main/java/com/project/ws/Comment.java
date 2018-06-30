package com.project.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for comment complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="comment">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="body" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="accepted" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element ref="{http://bookingxml.com/soap-example}lodging"/>
 *         &lt;element ref="{http://bookingxml.com/soap-example}user"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "comment", propOrder = {
        "id",
        "body",
        "accepted",
        "lodging",
        "user"
})
public class Comment {

    protected long id;
    @XmlElement(required = true)
    protected String body;
    protected boolean accepted;
    @XmlElement(required = true)
    protected Lodging lodging;
    @XmlElement(required = true)
    protected User user;

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
     * Gets the value of the body property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getBody() {
        return body;
    }

    /**
     * Sets the value of the body property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setBody(String value) {
        this.body = value;
    }

    /**
     * Gets the value of the accepted property.
     */
    public boolean isAccepted() {
        return accepted;
    }

    /**
     * Sets the value of the accepted property.
     */
    public void setAccepted(boolean value) {
        this.accepted = value;
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

}
