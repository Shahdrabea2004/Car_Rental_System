package model;

import java.math.BigDecimal;

/**
 * Represents the criteria used to search for cars in the rental system.
 *
 * Each field is optional, allowing the user to search by one criterion
 * or combine multiple criteria together.
 */
public class CarSearchCriteria {
    private String type;
    private String brand;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private CarStatus status;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    public CarStatus getStatus() {
        return status;
    }

    public void setStatus(CarStatus status) {
        this.status = status;
    }
}
