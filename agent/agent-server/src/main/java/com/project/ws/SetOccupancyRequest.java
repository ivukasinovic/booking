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
 *         &lt;element name="agent" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="lodging" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="start" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="end" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "agent",
        "lodging",
        "start",
        "end"
})
@XmlRootElement(name = "setOccupancyRequest")
public class SetOccupancyRequest {

    @XmlElement(required = true)
    protected String agent;
    protected long lodging;
    @XmlElement(required = true)
    protected String start;
    @XmlElement(required = true)
    protected String end;

    /**
     * Gets the value of the agent property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getAgent() {
        return agent;
    }

    /**
     * Sets the value of the agent property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setAgent(String value) {
        this.agent = value;
    }

    /**
     * Gets the value of the lodging property.
     */
    public long getLodging() {
        return lodging;
    }

    /**
     * Sets the value of the lodging property.
     */
    public void setLodging(long value) {
        this.lodging = value;
    }

    /**
     * Gets the value of the start property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getStart() {
        return start;
    }

    /**
     * Sets the value of the start property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setStart(String value) {
        this.start = value;
    }

    /**
     * Gets the value of the end property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getEnd() {
        return end;
    }

    /**
     * Sets the value of the end property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setEnd(String value) {
        this.end = value;
    }

}
