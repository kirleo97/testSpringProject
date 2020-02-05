package com.example.StadiumsSystem.domain;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "managers")
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MANAGER_ID")
    private Integer id;

    @Pattern(message = "\"Значение должно начинаться с заглавной буквы, после которой должны следовать только строчные. Неправильная форма: ${validatedValue}",
            regexp = "^[A-Z][a-z]*(\\s(([a-z]{1,3})|(([a-z]+\\')?[A-Z][a-z]*)))*$")
    @Length(min = 2, max = 100)
    @NotBlank
    @Column(name = "MANAGER_NAME", nullable = false)
    private String managerName;

    @NotBlank(message = "Поле телефонного номера не может быть пустым!")
    @Digits(integer = 11, fraction = 0, message = "Введенный номер не соответствует установленной форме")
    @Column(name = "MANAGER_TELEPHONE_NUMBER", unique = true, nullable = false)
    private String managerTelephoneNumber;

    public Manager() {
    }

    @Autowired
    public Manager(String managerName, String managerTelephoneNumber) {
        this.managerName = managerName;
        this.managerTelephoneNumber = managerTelephoneNumber;
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

    public String getManagerTelephoneNumber() {
        return managerTelephoneNumber;
    }

    public void setManagerTelephoneNumber(String managerTelephoneNumber) {
        this.managerTelephoneNumber = managerTelephoneNumber;
    }
}