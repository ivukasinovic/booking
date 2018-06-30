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
 *         &lt;element name="messageRes" type="{http://bookingxml.com/soap-example}messageRes"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "messageRes"
})
@XmlRootElement(name = "setMessagesRequest")
public class SetMessagesRequest {

    @XmlElement(required = true)
    protected MessageRes messageRes;

    /**
     * Gets the value of the messageRes property.
     *
     * @return possible object is
     * {@link MessageRes }
     */
    public MessageRes getMessageRes() {
        return messageRes;
    }

    /**
     * Sets the value of the messageRes property.
     *
     * @param value allowed object is
     *              {@link MessageRes }
     */
    public void setMessageRes(MessageRes value) {
        this.messageRes = value;
    }

}
