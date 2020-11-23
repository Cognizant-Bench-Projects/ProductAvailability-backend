package com.cognizant.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Product {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Column(unique = true, name = "product_name")
    private String productName;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "dept_id")
    private Department dept;

    @OneToMany(mappedBy = "product")
    private List<Balance> balance;

    public Product() {
    }

    public Product(int productId, String productName, Department dept) {
        this.id = productId;
        this.productName = productName;
        this.dept = dept;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Department getDept() {
        return dept;
    }

    public void setDept(Department dept) {
        this.dept = dept;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", dept=" + dept +
                '}';
    }
}
