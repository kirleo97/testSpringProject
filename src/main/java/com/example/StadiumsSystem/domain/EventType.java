package com.example.StadiumsSystem.domain;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "eventTypes")
public class EventType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "EVENTTYPE_ID")
    private Integer id;

    @Pattern(message = "Значение должно начинаться с заглавной буквы, после которой должны следовать только строчные. Неправильная форма: ${validatedValue}",
            regexp = "^[A-Z]{1}[a-z]+$")
    @Length(min = 2, max = 100)
    @NotBlank
    @Column(name = "EVENTTYPE_NAME", nullable = false, unique = true)
    private String eventTypeName;

    public EventType() {
    }

    @Autowired
    public EventType(String eventTypeName) {
        this.eventTypeName = eventTypeName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEventTypeName() {
        return eventTypeName;
    }

    public void setEventTypeName(String eventTypeName) {
        this.eventTypeName = eventTypeName;
    }
}