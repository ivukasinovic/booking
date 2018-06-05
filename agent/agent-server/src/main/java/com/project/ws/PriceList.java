
package com.project.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
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
 *         &lt;element name="year">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="5"/>
 *               &lt;minLength value="3"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="dateCreated" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="january" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}double">
 *               &lt;minExclusive value="0"/>
 *               &lt;maxExclusive value="32"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="february" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}double">
 *               &lt;minExclusive value="0"/>
 *               &lt;maxExclusive value="29"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="mart" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}double">
 *               &lt;minExclusive value="0"/>
 *               &lt;maxExclusive value="32"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="april" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}double">
 *               &lt;minExclusive value="0"/>
 *               &lt;maxExclusive value="31"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="may" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}double">
 *               &lt;minExclusive value="0"/>
 *               &lt;maxExclusive value="32"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="june" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}double">
 *               &lt;minExclusive value="0"/>
 *               &lt;maxExclusive value="32"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="july" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}double">
 *               &lt;minExclusive value="0"/>
 *               &lt;maxExclusive value="32"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="august" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}double">
 *               &lt;minExclusive value="0"/>
 *               &lt;maxExclusive value="32"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="september" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}double">
 *               &lt;minExclusive value="0"/>
 *               &lt;maxExclusive value="31"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="october" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}double">
 *               &lt;minExclusive value="0"/>
 *               &lt;maxExclusive value="32"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="november" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}double">
 *               &lt;minExclusive value="0"/>
 *               &lt;maxExclusive value="31"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="december" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}double">
 *               &lt;minExclusive value="0"/>
 *               &lt;maxExclusive value="32"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element ref="{http://bookingxml.com/soap-example}lodging" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
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
    protected Double january;
    protected Double february;
    protected Double mart;
    protected Double april;
    protected Double may;
    protected Double june;
    protected Double july;
    protected Double august;
    protected Double september;
    protected Double october;
    protected Double november;
    protected Double december;
    protected Lodging lodging;

    /**
     * Gets the value of the id property.
     * 
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(long value) {
        this.id = value;
    }

    /**
     * Gets the value of the year property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getYear() {
        return year;
    }

    /**
     * Sets the value of the year property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setYear(String value) {
        this.year = value;
    }

    /**
     * Gets the value of the dateCreated property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateCreated() {
        return dateCreated;
    }

    /**
     * Sets the value of the dateCreated property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateCreated(XMLGregorianCalendar value) {
        this.dateCreated = value;
    }

    /**
     * Gets the value of the january property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getJanuary() {
        return january;
    }

    /**
     * Sets the value of the january property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setJanuary(Double value) {
        this.january = value;
    }

    /**
     * Gets the value of the february property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getFebruary() {
        return february;
    }

    /**
     * Sets the value of the february property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setFebruary(Double value) {
        this.february = value;
    }

    /**
     * Gets the value of the mart property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMart() {
        return mart;
    }

    /**
     * Sets the value of the mart property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMart(Double value) {
        this.mart = value;
    }

    /**
     * Gets the value of the april property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getApril() {
        return april;
    }

    /**
     * Sets the value of the april property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setApril(Double value) {
        this.april = value;
    }

    /**
     * Gets the value of the may property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMay() {
        return may;
    }

    /**
     * Sets the value of the may property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMay(Double value) {
        this.may = value;
    }

    /**
     * Gets the value of the june property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getJune() {
        return june;
    }

    /**
     * Sets the value of the june property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setJune(Double value) {
        this.june = value;
    }

    /**
     * Gets the value of the july property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getJuly() {
        return july;
    }

    /**
     * Sets the value of the july property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setJuly(Double value) {
        this.july = value;
    }

    /**
     * Gets the value of the august property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getAugust() {
        return august;
    }

    /**
     * Sets the value of the august property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setAugust(Double value) {
        this.august = value;
    }

    /**
     * Gets the value of the september property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getSeptember() {
        return september;
    }

    /**
     * Sets the value of the september property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setSeptember(Double value) {
        this.september = value;
    }

    /**
     * Gets the value of the october property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getOctober() {
        return october;
    }

    /**
     * Sets the value of the october property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setOctober(Double value) {
        this.october = value;
    }

    /**
     * Gets the value of the november property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getNovember() {
        return november;
    }

    /**
     * Sets the value of the november property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setNovember(Double value) {
        this.november = value;
    }

    /**
     * Gets the value of the december property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getDecember() {
        return december;
    }

    /**
     * Sets the value of the december property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setDecember(Double value) {
        this.december = value;
    }

    /**
     * Gets the value of the lodging property.
     * 
     * @return
     *     possible object is
     *     {@link Lodging }
     *     
     */
    public Lodging getLodging() {
        return lodging;
    }

    /**
     * Sets the value of the lodging property.
     * 
     * @param value
     *     allowed object is
     *     {@link Lodging }
     *     
     */
    public void setLodging(Lodging value) {
        this.lodging = value;
    }

}
