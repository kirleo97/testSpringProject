package com.example.StadiumsSystem.domain;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
@Table(name = "managers")
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MANAGER_ID", unique = true, nullable = false)
    private Integer id;

    @Column(name = "MANAGER_NAME", nullable = false)
    private String managerName;

    public Manager() {
    }

    @Autowired
    public Manager(String managerName) {
        this.managerName = managerName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }
}