package com.cognizant.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Location {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Column(name = "location_name")
    private String locName;

    @NotNull
    @Column(name = "zip_code")
    private String zipCode;

    @OneToMany(mappedBy = "location")
    private List<Balance> balance;

    public Location() {
    }

    public Location(int id, String locName, String zipCode) {
        this.id = id;
        this.locName = locName;
        this.zipCode = zipCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocName() {
        return locName;
    }

    public void setLocName(String locName) {
        this.locName = locName;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", locName='" + locName + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }
}
