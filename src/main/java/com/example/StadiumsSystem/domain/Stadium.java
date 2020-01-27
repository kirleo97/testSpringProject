package com.example.StadiumsSystem.domain;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "stadiums")
public class Stadium {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Size(max = 100)
    @Column(name = "stadium_name", unique = true)
    private String name;

    @ElementCollection
    @ManyToMany(fetch = FetchType.EAGER)
    private List<EventType> eventTypes;

    public Stadium() {
    }

    @Autowired
    public Stadium(String name, List<EventType> eventTypes) {
        this.name = name;
        this.eventTypes = eventTypes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<EventType> getEventTypes() {
        return eventTypes;
    }

    public void setEventTypes(List<EventType> eventTypes) {
        this.eventTypes = eventTypes;
    }
}