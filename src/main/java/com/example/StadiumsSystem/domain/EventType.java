package com.example.StadiumsSystem.domain;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
@Table(name = "eventTypes")
public class EventType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "EVENTTYPE_ID", unique = true, nullable = false)
    private Integer id;

    @Column(name = "EVENTTYPE_NAME", unique = true, nullable = false)
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