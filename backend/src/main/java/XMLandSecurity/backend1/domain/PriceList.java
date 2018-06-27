package XMLandSecurity.backend1.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="priceList")
public class PriceList implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlElement(name="id", required=true)
    private Long id;

    @Column(name = "year", length = 4, nullable = false)
    @XmlElement(name="year", required=true)
    private String year;

    @Column(name = "date_created", nullable = false)
    @Temporal(TemporalType.DATE)
    @XmlElement(name="dateCreated", required=true)
    private Date dateCreated;


    @DecimalMax(value="1000000", inclusive=false)
    @DecimalMin(value ="1")
    @Column(name = "january", columnDefinition = "Decimal(8,2)")
    @XmlElement(name="january", required=true)
    private Double january;

    @DecimalMax(value="1000000", inclusive=false)
    @DecimalMin(value ="1")
    @Column(name = "february", columnDefinition = "Decimal(8,2)")
    @XmlElement(name="february", required=true)
    private Double february;

    @DecimalMax(value="1000000", inclusive=false)
    @DecimalMin(value ="1")
    @Column(name = "mart", columnDefinition = "Decimal(8,2)")
    @XmlElement(name="mart", required=true)
    private Double mart;

    @DecimalMax(value="1000000", inclusive=false)
    @DecimalMin(value ="1")
    @Column(name = "april", columnDefinition = "Decimal(8,2)")
    @XmlElement(name="april", required=true)
    private Double april;

    @DecimalMax(value="1000000", inclusive=false)
    @DecimalMin(value ="1")
    @Column(name = "may", columnDefinition = "Decimal(8,2)")
    @XmlElement(name="may", required=true)
    private Double may;

    @DecimalMax(value="1000000", inclusive=false)
    @DecimalMin(value ="1")
    @Column(name = "june", columnDefinition = "Decimal(8,2)")
    @XmlElement(name="june", required=true)
    private Double june;

    @DecimalMax(value="1000000", inclusive=false)
    @DecimalMin(value ="1")
    @Column(name = "july", columnDefinition = "Decimal(8,2)")
    @XmlElement(name="july", required=true)
    private Double july;

    @DecimalMax(value="1000000", inclusive=false)
    @DecimalMin(value ="1")
    @Column(name = "august", columnDefinition = "Decimal(8,2)")
    @XmlElement(name="august", required=true)
    private Double august;

    @DecimalMax(value="1000000", inclusive=false)
    @DecimalMin(value ="1")
    @Column(name = "september", columnDefinition = "Decimal(8,2)")
    @XmlElement(name="september", required=true)
    private Double september;

    @DecimalMax(value="1000000", inclusive=false)
    @DecimalMin(value ="1")
    @Column(name = "october", columnDefinition = "Decimal(8,2)")
    @XmlElement(name="october", required=true)
    private Double october;

    @DecimalMax(value="1000000", inclusive=false)
    @DecimalMin(value ="1")
    @Column(name = "november", columnDefinition = "Decimal(8,2)")
    @XmlElement(name="november", required=true)
    private Double november;

    @DecimalMax(value="1000000", inclusive=false)
    @DecimalMin(value ="1")
    @Column(name = "december", columnDefinition = "Decimal(8,2)")
    @XmlElement(name="december", required=true)
    private Double december;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    @JoinColumn(name = "lodging_id")
    @XmlElement(name="lodging", required=true)
    private Lodging lodging;

    public PriceList() {
    }

    public PriceList(Long id, String year, Date dateCreated, Double january, Double february, Double mart, Double april, Double may, Double june, Double july, Double august, Double september, Double october, Double november, Double december, Lodging lodging) {
        this.id = id;
        this.year = year;
        this.dateCreated = dateCreated;
        this.january = january;
        this.february = february;
        this.mart = mart;
        this.april = april;
        this.may = may;
        this.june = june;
        this.july = july;
        this.august = august;
        this.september = september;
        this.october = october;
        this.november = november;
        this.december = december;
        this.lodging = lodging;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Double getJanuary() {
        return january;
    }

    public void setJanuary(Double january) {
        this.january = january;
    }

    public Double getFebruary() {
        return february;
    }

    public void setFebruary(Double february) {
        this.february = february;
    }

    public Double getMart() {
        return mart;
    }

    public void setMart(Double mart) {
        this.mart = mart;
    }

    public Double getApril() {
        return april;
    }

    public void setApril(Double april) {
        this.april = april;
    }

    public Double getMay() {
        return may;
    }

    public void setMay(Double may) {
        this.may = may;
    }

    public Double getJune() {
        return june;
    }

    public void setJune(Double june) {
        this.june = june;
    }

    public Double getJuly() {
        return july;
    }

    public void setJuly(Double july) {
        this.july = july;
    }

    public Double getAugust() {
        return august;
    }

    public void setAugust(Double august) {
        this.august = august;
    }

    public Double getSeptember() {
        return september;
    }

    public void setSeptember(Double september) {
        this.september = september;
    }

    public Double getOctober() {
        return october;
    }

    public void setOctober(Double october) {
        this.october = october;
    }

    public Double getNovember() {
        return november;
    }

    public void setNovember(Double november) {
        this.november = november;
    }

    public Double getDecember() {
        return december;
    }

    public void setDecember(Double december) {
        this.december = december;
    }

    public Lodging getLodging() {
        return lodging;
    }

    public void setLodging(Lodging lodging) {
        this.lodging = lodging;
    }
}
