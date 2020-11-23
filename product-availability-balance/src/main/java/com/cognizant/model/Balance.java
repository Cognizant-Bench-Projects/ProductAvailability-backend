package com.cognizant.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class Balance {

    @EmbeddedId
    private BalanceId id;

    @ManyToOne
    @MapsId("product_id")
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @ManyToOne
    @MapsId("location_id")
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private Location location;

    @NotNull
    @Min(value = 0)
    @Column
    private int amount;

    public Balance() {
    }

    public Balance(BalanceId id, Product product, Location location, int amount) {
        this.id = id;
        this.product = product;
        this.location = location;
        this.amount = amount;
    }

    public BalanceId getId() {
        return id;
    }

    public void setId(BalanceId id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Balance balance = (Balance) o;
        return amount == balance.amount &&
                id.equals(balance.id) &&
                product.equals(balance.product) &&
                location.equals(balance.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, product, location, amount);
    }
}
