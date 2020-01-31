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
    @Column(name = "STADIUM_ID")
    private Integer id;

    @Size(max = 100)
    @Column(name = "STADIUM_NAME", unique = true, nullable = false)
    private String stadiumName;

    // @ElementCollection
    @ManyToMany
    @JoinTable(name = "STADIUMS_EVENTTYPES",
    joinColumns = @JoinColumn(name = "STADIUM_ID"),
    inverseJoinColumns = @JoinColumn(name = "EVENTTYPE_ID"))
    private List<EventType> eventTypes;

    public Stadium() {
    }

    @Autowired
    public Stadium(String stadiumName, List<EventType> eventTypes) {
        this.stadiumName = stadiumName;
        this.eventTypes = eventTypes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStadiumName() {
        return stadiumName;
    }

    public void setStadiumName(String stadiumName) {
        this.stadiumName = stadiumName;
    }

    public List<EventType> getEventTypes() {
        return eventTypes;
    }

    public void setEventTypes(List<EventType> eventTypes) {
        this.eventTypes = eventTypes;
    }
}