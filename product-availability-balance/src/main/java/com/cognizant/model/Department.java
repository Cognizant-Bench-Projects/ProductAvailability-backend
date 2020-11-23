package com.cognizant.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Department {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Column(unique = true, name = "department_name")
    private String deptName;

    public Department() {
    }

    public Department(int id, String deptName) {
        this.id = id;
        this.deptName = deptName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", deptName='" + deptName + '\'' +
                '}';
    }
}
