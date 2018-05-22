package XMLandSecurity.backend1.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table
public class PriceList implements Serializable {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "year", length = 4, nullable = false)
    private String year;

    @Column(name = "date_created", nullable = false,columnDefinition="DATETIME")
    private LocalDateTime dateCreated;

    @Column(name = "january", columnDefinition = "Decimal(8,2)")
    private Double january;

    @Column(name = "february", columnDefinition = "Decimal(8,2)")
    private Double february;

    @Column(name = "mart", columnDefinition = "Decimal(8,2)")
    private Double mart;

    @Column(name = "april", columnDefinition = "Decimal(8,2)")
    private Double april;

    @Column(name = "may", columnDefinition = "Decimal(8,2)")
    private Double may;

    @Column(name = "june", columnDefinition = "Decimal(8,2)")
    private Double june;

    @Column(name = "july", columnDefinition = "Decimal(8,2)")
    private Double july;

    @Column(name = "august", columnDefinition = "Decimal(8,2)")
    private Double august;

    @Column(name = "september", columnDefinition = "Decimal(8,2)")
    private Double september;

    @Column(name = "october", columnDefinition = "Decimal(8,2)")
    private Double october;

    @Column(name = "november", columnDefinition = "Decimal(8,2)")
    private Double november;

    @Column(name = "december", columnDefinition = "Decimal(8,2)")
    private Double december;

    @ManyToOne
    @JoinColumn(name = "lodging_id")
    private Lodging lodging;

    public PriceList() {
    }

    public PriceList(Long id, String year, LocalDateTime dateCreated, Double january, Double february, Double mart, Double april, Double may, Double june, Double july, Double august, Double september, Double october, Double november, Double december, Lodging lodging) {
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

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
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
