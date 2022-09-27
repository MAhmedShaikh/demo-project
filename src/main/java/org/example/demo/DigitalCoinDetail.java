package org.example.demo;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

import org.springframework.stereotype.Component;

/**
 * This class implements coin details.
 * @author M Ahmed Shaikh
 *
 */
@Component
public class DigitalCoinDetail {

    private String id;
    private String name;
    private BigDecimal basePrice;
    private String baseCurrency;
    private BigDecimal localprice;
    private String localCurrency;
    private Timestamp timeStamp;


    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getBaseCurrency() {
        return baseCurrency;
    }
    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }
    public String getLocalCurrency() {
        return localCurrency;
    }
    public void setLocalCurrency(String localCurrency) {
        this.localCurrency = localCurrency;
    }
    public Timestamp getTimeStamp() {
        return timeStamp;
    }
    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }
    public BigDecimal getBasePrice() {
        return basePrice;
    }
    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }
    public BigDecimal getLocalprice() {
        return localprice;
    }
    public void setLocalprice(BigDecimal localprice) {
        this.localprice = localprice;
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseCurrency, basePrice, id, localCurrency, localprice, name, timeStamp);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DigitalCoinDetail)) {
            return false;
        }
        DigitalCoinDetail other = (DigitalCoinDetail) obj;
        return Objects.equals(baseCurrency, other.baseCurrency) && Objects.equals(basePrice, other.basePrice) &&
            Objects.equals(id, other.id) && Objects.equals(localCurrency, other.localCurrency) &&
            Objects.equals(localprice, other.localprice) && Objects.equals(name, other.name) &&
            Objects.equals(timeStamp, other.timeStamp);
    }

    @Override
    public String toString() {
        return "DigitalCoinDetail [id=" + id + ", name=" + name + ", basePrice=" + basePrice + ", baseCurrency=" +
            baseCurrency + ", localprice=" + localprice + ", localCurrency=" + localCurrency + ", timeStamp=" +
            timeStamp + "]";
    }


}