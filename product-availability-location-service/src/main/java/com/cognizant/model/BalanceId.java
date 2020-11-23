package com.cognizant.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BalanceId implements Serializable {

    @Column(name = "product_id")
    private int productId;

    @Column(name = "location_id")
    private int locationId;

    public BalanceId() {
    }

    public BalanceId(int productId, int locationId) {
        this.productId = productId;
        this.locationId = locationId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BalanceId balanceId = (BalanceId) o;
        return productId == balanceId.productId &&
                locationId == balanceId.locationId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, locationId);
    }
}
