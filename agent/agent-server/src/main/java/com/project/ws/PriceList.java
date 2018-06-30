package com.project.ws;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for priceList complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="priceList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="year" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dateCreated" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="january" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="february" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="mart" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="april" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="may" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="june" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="july" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="august" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="september" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="october" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="november" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="december" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element ref="{http://bookingxml.com/soap-example}lodging"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "priceList", propOrder = {
        "id",
        "year",
        "dateCreated",
        "january",
        "february",
        "mart",
        "april",
        "may",
        "june",
        "july",
        "august",
        "september",
        "october",
        "november",
        "december",
        "lodging"
})
public class PriceList {

    protected long id;
    @XmlElement(required = true)
    protected String year;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateCreated;
    protected double january;
    protected double february;
    protected double mart;
    protected double april;
    protected double may;
    protected double june;
    protected double july;
    protected double august;
    protected double september;
    protected double october;
    protected double november;
    protected double december;
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
     * Gets the value of the year property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getYear() {
        return year;
    }

    /**
     * Sets the value of the year property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setYear(String value) {
        this.year = value;
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
     * Gets the value of the january property.
     */
    public double getJanuary() {
        return january;
    }

    /**
     * Sets the value of the january property.
     */
    public void setJanuary(double value) {
        this.january = value;
    }

    /**
     * Gets the value of the february property.
     */
    public double getFebruary() {
        return february;
    }

    /**
     * Sets the value of the february property.
     */
    public void setFebruary(double value) {
        this.february = value;
    }

    /**
     * Gets the value of the mart property.
     */
    public double getMart() {
        return mart;
    }

    /**
     * Sets the value of the mart property.
     */
    public void setMart(double value) {
        this.mart = value;
    }

    /**
     * Gets the value of the april property.
     */
    public double getApril() {
        return april;
    }

    /**
     * Sets the value of the april property.
     */
    public void setApril(double value) {
        this.april = value;
    }

    /**
     * Gets the value of the may property.
     */
    public double getMay() {
        return may;
    }

    /**
     * Sets the value of the may property.
     */
    public void setMay(double value) {
        this.may = value;
    }

    /**
     * Gets the value of the june property.
     */
    public double getJune() {
        return june;
    }

    /**
     * Sets the value of the june property.
     */
    public void setJune(double value) {
        this.june = value;
    }

    /**
     * Gets the value of the july property.
     */
    public double getJuly() {
        return july;
    }

    /**
     * Sets the value of the july property.
     */
    public void setJuly(double value) {
        this.july = value;
    }

    /**
     * Gets the value of the august property.
     */
    public double getAugust() {
        return august;
    }

    /**
     * Sets the value of the august property.
     */
    public void setAugust(double value) {
        this.august = value;
    }

    /**
     * Gets the value of the september property.
     */
    public double getSeptember() {
        return september;
    }

    /**
     * Sets the value of the september property.
     */
    public void setSeptember(double value) {
        this.september = value;
    }

    /**
     * Gets the value of the october property.
     */
    public double getOctober() {
        return october;
    }

    /**
     * Sets the value of the october property.
     */
    public void setOctober(double value) {
        this.october = value;
    }

    /**
     * Gets the value of the november property.
     */
    public double getNovember() {
        return november;
    }

    /**
     * Sets the value of the november property.
     */
    public void setNovember(double value) {
        this.november = value;
    }

    /**
     * Gets the value of the december property.
     */
    public double getDecember() {
        return december;
    }

    /**
     * Sets the value of the december property.
     */
    public void setDecember(double value) {
        this.december = value;
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
